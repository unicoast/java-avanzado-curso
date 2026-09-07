# Sistema de Gestión de Productos con Arquitectura MVC, JDBC y PostgreSQL

## Descripción General

Aplicación de consola que implementa una arquitectura **Modelo-Vista-Controlador (MVC)**, persistencia relacional física en **PostgreSQL** mediante **JDBC**, pool de conexiones de alto rendimiento con **HikariCP** y control transaccional manual (**ACID**), integrando conceptos de programación funcional (`Optional`, `Streams`) y la biblioteca **Lombok**.

---

## Características Principales del Sistema

- **Arquitectura en Capas:** Separación rigurosa de responsabilidades entre Presentación (`view`), Control (`controller`), Negocio (`service`), Abstracción de Persistencia (`repository`), Acceso a Datos (`persistence`), Dominio (`model`) e Infraestructura (`db`).
- **Persistencia Física Relacional:** Almacenamiento seguro en PostgreSQL mediante tablas normalizadas (`products` y `categories`) con restricciones de clave foránea e integridad referencial.
- **Repositorio Híbrido con Caché en Memoria:** `ProductRepositoryServices` mantiene una colección en memoria precargada desde la base de datos para lecturas ultrarrápidas, sincronizando de forma atómica cada modificación (`save`, `update`, `delete`) con el motor relacional.
- **Control Transaccional ACID Manual:** `ProductService` coordina transacciones atómicas con `setAutoCommit(false)`, `commit()` y `rollback()`, asegurando que operaciones compuestas (como la creación automática de categorías antes del producto) se ejecuten con consistencia absoluta.
- **Pool de Conexiones HikariCP:** `ConnectionPool` administra conexiones reciclables, optimizando el rendimiento al evitar la sobrecarga de apertura y cierre de conexiones físicas por cada consulta.
- **Programación Defensiva:** Validaciones exhaustivas en `ProductValidator` y utilitarios genéricos en `Validates` para impedir que datos inconsistentes consuman recursos de base de datos.
- **Consultas con JOIN y Claves Autogeneradas:** Recuperación de claves primarias con `Statement.RETURN_GENERATED_KEYS` e hidratación completa de objetos compuestos mediante `INNER JOIN`.

---

## Esquema Relacional de Base de Datos (PostgreSQL)

Para inicializar la base de datos requerida por la aplicación, se incluye el script completo con datos de prueba en [src/main/resources/schema.sql](src/main/resources/schema.sql):

```sql
-- Tabla de Categorías (Entidad fuerte)
CREATE TABLE IF NOT EXISTS categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Tabla de Productos (Entidad vinculada mediante clave foránea)
CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    stock INTEGER NOT NULL,
    category_id BIGINT NOT NULL,
    CONSTRAINT fk_products_categories 
        FOREIGN KEY (category_id) REFERENCES categories (id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Índice para optimizar búsquedas y consultas con JOIN
CREATE INDEX IF NOT EXISTS idx_products_category_id ON products(category_id);
```

---

## Estructura de Paquetes y Componentes

```text
08-integracion-jdbc-mvc
+-- pom.xml                                       # Configuración del proyecto y dependencias Maven
+-- src
    +-- main
        +-- resources
        |   +-- logback.xml                       # Configuración de logging de la aplicación
        |   +-- schema.sql                        # Script DDL e inserción de datos iniciales en PostgreSQL
        +-- java
            +-- com/unicoast/project
                +-- Main.java                     # Inyección manual de dependencias y punto de entrada
                +-- db
                |   +-- ConnectionPool.java       # Configuración y administración del pool HikariCP
                +-- category
                |   +-- model
                |   |   +-- Category.java         # Entidad de dominio Category (Lombok)
                |   +-- persistence
                |       +-- CategoryDao.java      # DAO con operaciones CRUD para categorías
                +-- product
                    +-- controller
                    |   +-- ProductController.java         # Controlador MVC: valida y delega a ProductService
                    +-- exceptions
                    |   +-- InvalidProductException.java   # Checked Exception para reglas de negocio
                    |   +-- ProductNotFoundException.java  # Checked Exception para entidades ausentes
                    +-- interfaces
                    |   +-- ProductRepository.java         # Contrato abstracto de persistencia
                    +-- model
                    |   +-- Product.java                   # Entidad de dominio Product (Lombok)
                    |   +-- ProductCategory.java           # Enum para validación y filtrado en consola
                    +-- persistence
                    |   +-- ProductDao.java                # DAO con consultas JOIN y operaciones CRUD
                    +-- repository
                    |   +-- ProductRepositoryServices.java # Repositorio híbrido (caché en memoria + DAO)
                    +-- service
                    |   +-- ProductService.java            # Orquestación de transacciones ACID y negocio
                    |   +-- ProductValidator.java          # Validador de invariantes de dominio
                    +-- util
                    |   +-- Validates.java                 # Utilitarios genéricos de validación
                    +-- view
                        +-- ProductView.java               # Vista por consola interactiva y captura segura
```

---

## Flujo Transaccional ACID en `ProductService`

El servicio de productos garantiza la atomicidad mediante el siguiente protocolo JDBC:

```text
1. ConnectionPool.getConnection()          -> Obtiene conexión prestada del pool
2. connection.setAutoCommit(false)         -> Inicia la unidad transaccional
3. ProductValidator.validate(product)      -> Validación de reglas de dominio
4. CategoryDao.findCategoryByName(...)     -> Verifica existencia de categoría
   - Si no existe: CategoryDao.save(...)   -> Inserta categoría y obtiene ID generado
5. ProductRepository.save(...)             -> Inserta producto con la clave foránea
6. connection.commit()                     -> Confirma y persiste todas las operaciones
   [Catch SQLException | Exception]
   connection.rollback()                   -> Revierte cualquier cambio en caso de error
   [Finally]
   connection.setAutoCommit(true)          -> Restaura el modo por defecto
   connection.close()                      -> Devuelve la conexión al pool HikariCP
```

---

## Requisitos y Guía de Ejecución

### Prerrequisitos
- **Java Development Kit (JDK):** Versión 21 o superior.
- **Apache Maven:** Versión 3.8 o superior.
- **PostgreSQL:** Servidor activo en `localhost:5432` con credenciales configuradas en `ConnectionPool.java`.

### Pasos de Inicialización y Ejecución

1. **Crear y poblar la base de datos en PostgreSQL:**
   Ejecutar el script [src/main/resources/schema.sql](src/main/resources/schema.sql) en el gestor de base de datos preferido (pgAdmin, DBeaver o terminal `psql`):
   ```powershell
   # Creación de base de datos y ejecución del script DDL:
   psql -U postgres -c "CREATE DATABASE java_course;"
   psql -U postgres -d java_course -f src/main/resources/schema.sql
   ```

2. **Compilar el proyecto con Maven:**
   ```powershell
   mvn clean compile
   ```

3. **Ejecutar la aplicación de consola:**
   ```powershell
   mvn exec:java -Dexec.mainClass="com.unicoast.project.Main"
   ```
