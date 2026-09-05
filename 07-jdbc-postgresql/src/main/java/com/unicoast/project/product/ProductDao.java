package com.unicoast.project.product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * PATRÓN DATA ACCESS OBJECT (DAO): PRODUCT DAO (CONEXIÓN EXTERNA INYECTADA)
 *
 * Responsabilidades:
 * 1. Implementa las operaciones CRUD completas (Create, Read, Update, Delete) para la tabla 'products'.
 * 2. Recibe la 'Connection' por inyección en el constructor.
 *    - Ventaja arquitectónica: Permite participar en transacciones compuestas (ACID) gestionadas por un servicio.
 * 3. Utiliza 'PreparedStatement' para evitar inyecciones SQL y precompilar consultas.
 * 4. Mapeo relacional manual (ORM manual): Transforma filas de 'ResultSet' en objetos Java de tipo 'Product'.
 */
public class ProductDao {
    private final Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    /*
     * CREATE: Inserta un nuevo registro en la tabla 'products'.
     * 'executeUpdate()' ejecuta sentencias DML (INSERT, UPDATE, DELETE) y retorna el número de filas afectadas.
     */
    public void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, stock, category_id) " +
                "VALUES (?, ?, ?, ?)";

        try (
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
     * UPDATE: Actualiza los campos de un producto existente filtrando por su 'id'.
     */
    public void update(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, price = ?, stock = ?, category_id = ? " +
                " WHERE id = ?";

        try (
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
     * DELETE: Elimina un registro de la tabla 'products' a partir de su clave primaria.
     */
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ){
            statement.setLong(1, id);

            int rows = statement.executeUpdate();
            showMessage(rows, "Producto eliminado correctamente", "Producto no encontrado");
        }
    }

    /*
     * READ (FIND ALL): Consulta todos los productos registrados.
     * 'executeQuery()' ejecuta sentencias SELECT y retorna un cursor de datos 'ResultSet'.
     */
    public List<Product> findAll() throws SQLException {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
        ){
            // resultSet.next() avanza el cursor fila por fila; retorna false al llegar al final
            while(resultSet.next()){
                Product product = mapResult(resultSet);
                products.add(product);
            }
        }

        return products;
    }

    /*
     * Método auxiliar de mapeo (RowMapper manual):
     * Extrae las columnas del ResultSet según su nombre y tipo de dato para instanciar el objeto Product.
     */
    private Product mapResult(ResultSet resultSet) throws SQLException {
        Product product = new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getInt("stock")
        );

        return product;
    }

    // Método utilitario para emitir mensajes informativos según las filas afectadas
    private void showMessage(int rows, String msgOK, String msgError){
        if(rows > 0){
            System.out.println(msgOK);
        }else if(!msgError.isBlank()){
            System.out.println(msgError);
        }
    }
}
