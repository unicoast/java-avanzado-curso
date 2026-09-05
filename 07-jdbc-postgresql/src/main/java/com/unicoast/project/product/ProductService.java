package com.unicoast.project.product;

import com.unicoast.project.category.Category;
import com.unicoast.project.category.CategoryDao;
import com.unicoast.project.db.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/*
 * CAPA DE SERVICIO: GESTIÓN DE TRANSACCIONES ACID EN JDBC
 *
 * ¿Qué es una Transacción ACID?
 * Es un conjunto de operaciones sobre la base de datos que deben ejecutarse como una sola unidad atómica:
 * - Atomicidad: Todo se ejecuta con éxito (commit) o nada se guarda (rollback).
 * - Consistencia: La base de datos pasa de un estado válido a otro estado válido.
 * - Aislamiento (Isolation): Las transacciones concurrentes no interfieren entre sí.
 * - Durabilidad: Una vez confirmado el commit, los cambios son permanentes.
 *
 * Flujo Transaccional en JDBC:
 * 1. connection.setAutoCommit(false): Inicia la transacción manual.
 * 2. Ejecución de DAOs: Se pasa la misma conexión a todos los DAOs involucrados.
 * 3. connection.commit(): Confirma y persiste todos los cambios juntos.
 * 4. connection.rollback(): Deshace todas las operaciones si ocurre una excepción.
 * 5. finally: Restaura 'setAutoCommit(true)' y cierra la conexión para regresarla al pool.
 */
public class ProductService {

    /*
     * Operación Transaccional Compleja: Guarda la categoría y el producto en una única transacción atómica.
     * Si la categoría se guarda pero el producto falla, el rollback cancela la categoría creada.
     */
    public void saveProductWithCategory(Product product, Category category) throws SQLException {
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false); // Desactiva el guardado automático para agrupar las operaciones en una sola transacción

            CategoryDao categoryDao = new CategoryDao(connection);
            ProductDao productDao = new ProductDao(connection);

            // Paso 1: Inserción de categoría y obtención del ID generado mediante RETURNING id
            Category newCategory = categoryDao.save(category);
            product.setCategory(newCategory);

            // Paso 2: Inserción del producto asociando la clave foránea de la categoría
            productDao.save(product);

            // Confirmación atómica exitosa de ambas operaciones
            connection.commit();

        } catch (SQLException e) {
            // Reversión total de cambios (rollback) ante cualquier excepción en el proceso
            if(connection != null){
                connection.rollback();
            }

            System.out.println(e.getMessage());

            throw e; // Relanzar el error
        } finally {
            if(connection != null){
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e){  // Evita propagar una excepción de cierre si la transacción principal fue exitosa
                    System.out.println("Error al cerrar la conexión");
                }
            }
        }
    }

    /*
     * Actualización transaccional de producto.
     */
    public void updateProduct(Product product) throws SQLException {
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);

            ProductDao productDao = new ProductDao(connection);

            productDao.update(product);

            connection.commit();

        } catch (SQLException e) {
            if(connection != null){
                connection.rollback();
            }

            System.out.println(e.getMessage());

            throw e;
        } finally {
            if(connection != null){
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e){
                    System.out.println("Error al cerrar la conexión");
                }
            }
        }
    }

    /*
     * Eliminación transaccional de producto por ID.
     */
    public void deleteProduct(Long id) throws SQLException {
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);

            ProductDao productDao = new ProductDao(connection);

            productDao.delete(id);

            connection.commit();

        } catch (SQLException e) {
            if(connection != null){
                connection.rollback();
            }

            System.out.println(e.getMessage());

            throw e;
        } finally {
            if(connection != null){
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e){
                    System.out.println("Error al cerrar la conexión");
                }
            }
        }
    }

    /*
     * Consulta de productos: Operación de solo lectura que aprovecha try-with-resources.
     */
    public List<Product> findProducts() throws SQLException {

        try (Connection connection = ConnectionPool.getConnection()) {

            ProductDao productDao = new ProductDao(connection);

            return productDao.findAll();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
