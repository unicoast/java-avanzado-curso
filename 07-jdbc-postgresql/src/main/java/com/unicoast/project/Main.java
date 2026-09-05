package com.unicoast.project;

import com.unicoast.project.category.Category;
import com.unicoast.project.db.ConnectionDB;
import com.unicoast.project.db.DatabaseConnection;
import com.unicoast.project.product.Product;
import com.unicoast.project.product.ProductDao;
import com.unicoast.project.product.ProductService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/*
 * PERSISTENCIA RELACIONAL CON JDBC Y POSTGRESQL EN JAVA
 *
 * ¿Qué es JDBC (Java Database Connectivity)?
 * Es la API estándar de Java (java.sql) que define cómo conectar y ejecutar sentencias SQL
 * en bases de datos relacionales de manera agnóstica al motor (PostgreSQL, MySQL, Oracle, etc.).
 *
 * Evolución de los Componentes en esta Sección:
 * 1. Conexión Directa (ConnectionDB): Conexiones físicas individuales con DriverManager.
 * 2. Patrón Singleton (DatabaseConnection): Reutilización de una única instancia de conexión.
 * 3. Pool de Conexiones (ConnectionPool con HikariCP): Gestión empresarial de conexiones reciclables.
 * 4. Patrón DAO (Data Access Object): Separación entre lógica de acceso a datos y objetos de dominio.
 * 5. Capa de Servicio (ProductService): Orquestación de transacciones ACID (commit / rollback) multi-tabla.
 */
public class Main {
    public static void main(String[] args) throws SQLException {

        // Servicio de negocio y control transaccional:
        ProductService service = new ProductService();

        // Creación de entidades de dominio para inserción:
        Product product = new Product("Java Avanzado", 1200.0, 20);
        Category category = new Category("LIBROS".trim().toUpperCase());

        try {
            // 1. Inserción transaccional (categoría + producto)
            System.out.println("=== 1. INSERCIÓN TRANSACCIONAL (CATEGORÍA + PRODUCTO) ===");
            service.saveProductWithCategory(product, category);

            // 2. Consulta y listado inicial de productos
            System.out.println("\n=== 2. LISTADO INICIAL DE PRODUCTOS ===");
            List<Product> products = service.findProducts();
            products.forEach(System.out::println);

            // 3. Actualización transaccional del producto ingresado
            System.out.println("\n=== 3. ACTUALIZACIÓN TRANSACCIONAL ===");
            // Obtener el último producto de la lista con su ID asignado por la base de datos:
            Product productToUpdate = products.get(products.size() - 1);
            productToUpdate.setName("Java Avanzado - 2da Edición");
            productToUpdate.setPrice(1300.0);
            productToUpdate.setStock(25);
            productToUpdate.setCategory(category);
            service.updateProduct(productToUpdate);

            // 4. Listado del catálogo con los cambios persistidos
            System.out.println("\n=== 4. CATÁLOGO FINAL PERSISTIDO EN BASE DE DATOS ===");
            service.findProducts().forEach(System.out::println);

            // Eliminación transaccional (descomentar para probar borrado por ID):
            // service.deleteProduct(productToUpdate.getId());

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        /*
         * PRUEBAS ANTERIORES CON DAO DIRECTO:
         * Acceso directo al DAO sin pasar por la capa de servicio ni gestión transaccional.
         */
        // ProductDao dao = new ProductDao();
        // Product product = new Product("Monitor LG 2025", 1210.00, 19);
        // dao.save(product);

        // List<Product> productList = dao.findAll();
        // productList.forEach(System.out::println);

        /*
         * PRUEBAS ANTERIORES DE COMPARACIÓN DE CONEXIONES:
         * 1. Patrón Singleton (DatabaseConnection): Las 3 referencias c1, c2 y c3 apuntan a la misma conexión física.
         */
        // Connection c1 = DatabaseConnection.getInstance().getConnection();
        // Connection c2 = DatabaseConnection.getInstance().getConnection();
        // Connection c3 = DatabaseConnection.getInstance().getConnection();

        /*
         * 2. Conexión Directa (ConnectionDB): Cada llamada crea una conexión física nueva e independiente.
         */
        // Connection c1 = ConnectionDB.connection();
        // Connection c2 = ConnectionDB.connection();
        // Connection c3 = ConnectionDB.connection();

        // System.out.println("C1: " + c1);
        // System.out.println("C2: " + c2);
        // System.out.println("C3: " + c3);
    }
}
