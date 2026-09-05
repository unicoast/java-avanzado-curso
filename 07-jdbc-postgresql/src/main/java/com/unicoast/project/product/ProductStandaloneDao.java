package com.unicoast.project.product;

import com.unicoast.project.db.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * VARIANTE DIDÁCTICA: DAO AUTÓNOMO / STANDALONE (AUTO-GESTIÓN DE CONEXIÓN CON POOL)
 *
 * Propósito didáctico y comparativa arquitectónica:
 *
 * 1. ProductStandaloneDao (Auto-gestión con ConnectionPool y try-with-resources):
 *    - Cada método solicita su propia conexión fresca al pool (HikariCP) y la libera inmediatamente al finalizar.
 *    - Ventaja: Ideal para operaciones CRUD atómicas, independientes y sencillas (garantiza el cierre automático).
 *    - Limitación: No puede participar en transacciones compuestas (multi-tabla / ACID) orquestadas por una capa de servicio.
 *
 * 2. ProductDao (Inyección de conexión por constructor - Patrón Transaccional):
 *    - La conexión es abierta y controlada externamente por 'ProductService'.
 *    - Permite coordinar múltiples DAOs (ej. CategoryDao + ProductDao) bajo una misma transacción (commit / rollback).
 */
public class ProductStandaloneDao {

    // Método de apoyo para solicitar una conexión fresca al pool HikariCP
    private Connection getConnection() throws SQLException {
        return ConnectionPool.getConnection();
    }

    /*
     * CREATE: Solicita conexión del pool, ejecuta la inserción y devuelve la conexión al pool en el try-with-resources.
     */
    public void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, stock, category_id) " +
                "VALUES (?, ?, ?, ?)";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ){
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.setLong(4, product.getCategory().getId());

            int rows = statement.executeUpdate();
            showMessage(rows, "Producto ingresado correctamente", "");

        }
    }

    /*
     * UPDATE: Modifica el producto y asegura la devolución de la conexión al finalizar.
     */
    public void update(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, price = ?, stock = ?, category_id = ? " +
                "WHERE id = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ){
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.setLong(4, product.getCategory().getId());
            statement.setLong(5, product.getId());

            int rows = statement.executeUpdate();
            showMessage(rows, "Producto actualizado correctamente", "Producto no encontrado");

        }
    }

    /*
     * DELETE: Elimina por clave primaria con gestión autónoma de conexión.
     */
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ){
            statement.setLong(1, id);

            int rows = statement.executeUpdate();
            showMessage(rows, "Producto eliminado correctamente", "Producto no encontrado");

        }
    }

    /*
     * READ (FIND ALL): Consulta todos los productos y libera los recursos en orden inverso automáticamente.
     */
    public List<Product> findAll() throws SQLException {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()){
                Product product = mapResult(resultSet);
                products.add(product);
            }
        }
        return products;
    }

    // Mapeo manual de ResultSet a entidad Product
    private Product mapResult(ResultSet resultSet) throws SQLException {
        Product product = new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getInt("stock")
        );

        return product;
    }

    // Impresión informativa según las filas afectadas por el comando DML
    private void showMessage(int rows, String messageOK, String messageError){
        if(rows>0){
            System.out.println(messageOK);
        }else if(!messageError.isBlank()){
            System.out.println(messageError);
        }
    }

}
