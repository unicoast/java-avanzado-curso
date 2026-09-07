package com.unicoast.project.product.persistence;

import com.unicoast.project.category.model.Category;
import com.unicoast.project.category.persistence.CategoryDao;
import com.unicoast.project.product.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * PATRÓN DATA ACCESS OBJECT (DAO): PERSISTENCIA DE PRODUCTOS CON JDBC
 *
 * Responsabilidades y Arquitectura:
 * 1. Acceso a Datos Relacional: Centraliza y encapsula todas las consultas SQL (DML y DQL) sobre la tabla 'products'.
 * 2. Manejo de Claves Foráneas: Gestiona la columna 'category_id' vinculándola con la tabla 'categories'.
 * 3. Consultas con JOIN: Emplea 'INNER JOIN' entre 'products' y 'categories' para hidratar de forma completa
 *    la entidad compuesta 'Product' junto con su 'Category', evitando el problema de consultas N+1.
 * 4. Generación de Claves Primarias: Emplea 'Statement.RETURN_GENERATED_KEYS' para sincronizar el ID generado
 *    por la base de datos de vuelta en el objeto Java.
 * 5. Inyección Transaccional de Conexión: Cada operación recibe la 'Connection' activa gestionada por 'ProductService',
 *    permitiendo participar en transacciones ACID compuestas.
 * 6. Protección contra Inyecciones SQL: Todas las consultas utilizan 'PreparedStatement' parametrizado con '?'.
 */
public class ProductDao {
    private final CategoryDao categoryDao;

    /*
     * Constructor que recibe la referencia a CategoryDao para resolución de dependencias entre entidades.
     */
    public ProductDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    /*
     * CREATE: Insertar un nuevo registro en la tabla 'products'.
     * Recuperar el ID asignado por PostgreSQL mediante 'getGeneratedKeys()' y establecerlo en la entidad.
     */
    public Product save(Connection connection, Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, stock, category_id) " +
                "VALUES (?, ?, ?, ?)";

        try (
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.setLong(4, product.getCategory().getId());

            int rows = statement.executeUpdate();
            if (rows > 0) {
                try (ResultSet generatedKey = statement.getGeneratedKeys()) {
                    if (generatedKey.next()) {
                        product.setId(generatedKey.getLong(1));
                        System.out.println("Producto ingresado correctamente");
                    }
                }
            }
        }

        return product;
    }

    /*
     * UPDATE: Actualizar los atributos de un producto existente filtrando por su 'id'.
     */
    public void update(Connection connection, Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, price = ?, stock = ?, category_id = ? " +
                "WHERE id = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
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
     * DELETE: Eliminar un registro de la tabla 'products' a partir de su clave primaria.
     */
    public void delete(Connection connection, Long id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);

            int rows = statement.executeUpdate();
            showMessage(rows, "Producto eliminado correctamente", "Producto no encontrado");
        }
    }

    /*
     * READ (EXISTS BY ID): Comprobar la existencia de un producto en la base de datos de manera eficiente
     * mediante una consulta 'SELECT count(*)', evitando transferir columnas innecesarias por la red.
     */
    public boolean existsById(Connection connection, Long id) throws SQLException {
        if (id == null) return false;

        String sql = "SELECT count(*) FROM products WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }

        return false;
    }

    /*
     * READ (FIND ALL): Consultar todos los productos realizando un JOIN con la tabla 'categories'.
     * Retornar la lista de productos con sus categorías correspondientes instanciadas.
     */
    public List<Product> findAll(Connection connection) throws SQLException {
        String sql = "SELECT p.id, p.name, p.price, p.stock, p.category_id,\n" +
                "    c.name as category_name\n" +
                "FROM products p JOIN categories c ON p.category_id = c.id";

        List<Product> products = new ArrayList<>();

        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Product product = mapResult(resultSet);
                products.add(product);
            }
        }

        return products;
    }

    /*
     * READ (FIND BY ID): Buscar un producto por su clave primaria uniendo la información de su categoría.
     * Retornar un Optional con la entidad o Optional.empty() si no existe en la base de datos.
     */
    public Optional<Product> findById(Connection connection, Long id) throws SQLException {
        String sql = "SELECT p.id, p.name, p.price, p.stock, p.category_id,\n" +
                "    c.name as category_name\n" +
                "FROM products p JOIN categories c ON p.category_id = c.id " +
                "WHERE p.id = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResult(resultSet));
                }
            }
        }

        return Optional.empty();
    }

    /*
     * READ (FIND BY CATEGORY ID): Consulta los productos pertenecientes a una categoría específica
     * mediante el filtro relacional sobre la columna 'category_id'.
     */
    public List<Product> findByCategoryId(Connection connection, Long categoryId) throws SQLException {
        String sql = "SELECT p.id, p.name, p.price, p.stock, p.category_id,\n" +
                "    c.name as category_name\n" +
                "FROM products p JOIN categories c ON p.category_id = c.id " +
                "WHERE p.category_id = ?";

        List<Product> products = new ArrayList<>();

        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, categoryId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(mapResult(resultSet));
                }
            }
        }

        return products;
    }

    /*
     * Mapeo relacional manual (RowMapper):
     * Extraer las columnas del ResultSet combinando datos de 'products' y 'categories'
     * para construir el grafo de objetos Java en memoria.
     */
    private Product mapResult(ResultSet resultSet) throws SQLException {
        Long categoryId = resultSet.getLong("category_id");
        String categoryName = resultSet.getString("category_name");

        Category category = new Category(categoryId, categoryName);

        return new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getInt("stock"),
                category
        );
    }

    /*
     * Emitir mensajes informativos de acuerdo a las filas afectadas por sentencias DML.
     */
    private void showMessage(int rows, String msgOK, String msgError) {
        if (rows > 0) {
            System.out.println(msgOK);
        } else if (!msgError.isBlank()) {
            System.out.println(msgError);
        }
    }
}
