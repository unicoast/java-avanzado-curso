package com.unicoast.project.category.persistence;

import com.unicoast.project.category.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * PATRÓN DATA ACCESS OBJECT (DAO): PERSISTENCIA DE CATEGORÍAS
 *
 * ¿Qué es el Patrón DAO?
 * Es un patrón estructural que abstrae y encapsula todos los accesos a la fuente de datos relacional.
 * Proporciona una interfaz limpia para ejecutar operaciones CRUD sobre la tabla 'categories',
 * aislando los detalles de JDBC y SQL de las capas de negocio y repositorio.
 *
 * Inyección Transaccional de Conexión:
 * Cada método recibe la 'Connection' como parámetro directo. Este diseño permite que múltiples DAOs
 * (CategoryDao y ProductDao) compartan una misma conexión abierta por la capa de servicio,
 * asegurando la ejecución atómica y consistente de transacciones compuestas (commit o rollback unificado).
 *
 * Recuperación de Claves Autogeneradas (RETURN_GENERATED_KEYS):
 * Mediante la constante 'PreparedStatement.RETURN_GENERATED_KEYS' y el método 'getGeneratedKeys()',
 * se obtiene el valor asignado a la columna 'id' por la secuencia de PostgreSQL, sincronizándolo con el objeto en memoria.
 *
 * Uso de Optional:
 * Los métodos de búsqueda retornan 'Optional<Category>' para ofrecer un manejo nulo seguro y evitar 'NullPointerException'.
 */
public class CategoryDao {

    /*
     * CREATE: Insertar una nueva categoría en la tabla 'categories'.
     * Retornar un Optional con la categoría actualizada con su ID generado, o Optional.empty() si ocurre un error.
     */
    public Optional<Category> save(Connection connection, Category category) {
        String sql = "INSERT INTO categories (name) " +
                "VALUES (?)";

        try (
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, category.getName());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        long id = resultSet.getLong(1);
                        category.setId(id);
                        return Optional.of(category);
                    }
                }
                System.out.println("Categoría creada correctamente");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    /*
     * UPDATE: Actualizar el nombre de una categoría existente filtrando por su 'id'.
     */
    public void update(Connection connection, Category category) throws SQLException {
        String sql = "UPDATE categories SET name = ? " +
                "WHERE id = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, category.getName());
            statement.setLong(2, category.getId());

            int rows = statement.executeUpdate();
            showMessage(rows, "Categoría actualizada correctamente", "Categoría no encontrada");
        }
    }

    /*
     * DELETE: Eliminar un registro de la tabla 'categories' a partir de su clave primaria.
     */
    public void delete(Connection connection, Long id) throws SQLException {
        String sql = "DELETE FROM categories WHERE id = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);

            int rows = statement.executeUpdate();
            showMessage(rows, "Categoría eliminada correctamente", "Categoría no encontrada");
        }
    }

    /*
     * READ (FIND ALL): Consultar todas las categorías registradas.
     * Retornar una lista con las entidades mapeadas desde el cursor ResultSet.
     */
    public List<Category> findAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM categories";
        List<Category> categories = new ArrayList<>();

        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Category category = mapResult(resultSet);
                categories.add(category);
            }
        }

        return categories;
    }

    /*
     * READ (FIND BY NAME): Buscar una categoría por su nombre exacto.
     * Comprobar si la categoría ya existe en la base de datos antes de crear una nueva.
     */
    public Optional<Category> findCategoryByName(Connection connection, String categoryName) throws SQLException {
        String sql = "SELECT * FROM categories WHERE name = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, categoryName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResult(resultSet));
                }
            }
        }

        return Optional.empty();
    }

    /*
     * READ (FIND BY ID): Buscar una categoría por su clave primaria.
     */
    public Optional<Category> findById(Connection connection, Long id) throws SQLException {
        String sql = "SELECT * FROM categories WHERE id = ?";

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
     * Método auxiliar de mapeo (RowMapper manual):
     * Extraer las columnas del ResultSet según su tipo de dato para construir el objeto Category.
     */
    private Category mapResult(ResultSet resultSet) throws SQLException {
        return new Category(
                resultSet.getLong("id"),
                resultSet.getString("name")
        );
    }

    /*
     * Utilitario interno para emitir mensajes informativos según las filas afectadas en operaciones DML.
     */
    private void showMessage(int rows, String msgOK, String msgError) {
        if (rows > 0) {
            System.out.println(msgOK);
        } else if (!msgError.isBlank()) {
            System.out.println(msgError);
        }
    }
}

