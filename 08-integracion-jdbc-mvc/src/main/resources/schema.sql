-- =============================================================================
-- ESQUEMA DE BASE DE DATOS: GESTIÓN DE PRODUCTOS CON MVC Y JDBC
-- Motor de Base de Datos: PostgreSQL
-- Base de datos objetivo: java_course
-- Tablas: categories, products
-- =============================================================================

-- Creación de la base de datos (ejecutar conectado a la base 'postgres'):
-- CREATE DATABASE java_course;

-- Conexión a la base de datos:
-- \c java_course;

-- -----------------------------------------------------------------------------
-- 1. LIMPIEZA PREVIA (Opcional para reiniciar el entorno de pruebas)
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS categories CASCADE;

-- -----------------------------------------------------------------------------
-- 2. TABLA: CATEGORIES
-- Entidad fuerte que almacena las categorías de los productos.
-- Se crea en primer término ya que 'products' depende de su clave primaria.
-- -----------------------------------------------------------------------------
CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- -----------------------------------------------------------------------------
-- 3. TABLA: PRODUCTS
-- Entidad que almacena el inventario de productos.
-- Contiene una clave foránea (category_id) vinculada a la tabla 'categories'.
-- -----------------------------------------------------------------------------
CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    stock INTEGER NOT NULL,
    category_id BIGINT NOT NULL,
    CONSTRAINT fk_products_categories 
        FOREIGN KEY (category_id) REFERENCES categories (id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- -----------------------------------------------------------------------------
-- 4. ÍNDICES DE RENDIMIENTO
-- Optimiza las consultas con INNER JOIN y búsquedas filtradas por categoría.
-- -----------------------------------------------------------------------------
CREATE INDEX idx_products_category ON products(category_id);

-- -----------------------------------------------------------------------------
-- 5. DATOS DE PRUEBA INICIALES (SEEDING)
-- Inserción de categorías estándar y productos para pruebas funcionales.
-- -----------------------------------------------------------------------------
INSERT INTO categories (name) VALUES
    ('ELECTRONICOS'),
    ('COMIDAS'),
    ('LIBROS'),
    ('OTROS');

INSERT INTO products (name, price, stock, category_id) VALUES
    ('Notebook Lenovo ThinkPad', 1250.00, 15, 1),
    ('Monitor LG 27 Pulgadas', 320.00, 20, 1),
    ('Teclado Mecánico RGB', 85.50, 40, 1),
    ('Pack Café de Especialidad 1kg', 24.50, 50, 2),
    ('Aceite de Oliva Extra Virgen 500ml', 14.00, 30, 2),
    ('Clean Code - Robert C. Martin', 48.00, 25, 3),
    ('Effective Java - Joshua Bloch', 56.00, 18, 3),
    ('Mochila Ejecutiva Ergonómica', 65.00, 35, 4);

-- Sincronización explícita de las secuencias autoincrementales BIGSERIAL
SELECT setval('categories_id_seq', (SELECT COALESCE(MAX(id), 1) FROM categories));
SELECT setval('products_id_seq', (SELECT COALESCE(MAX(id), 1) FROM products));

-- -----------------------------------------------------------------------------
-- 6. CONSULTA DE COMPROBACIÓN
-- Sentencia SQL equivalente a la ejecutada por ProductDao.findAll()
-- -----------------------------------------------------------------------------
SELECT p.id, p.name, p.price, p.stock, p.category_id, c.name AS category_name
FROM products p
JOIN categories c ON p.category_id = c.id;
