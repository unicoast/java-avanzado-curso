package com.unicoast.project.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * PATRÓN DATA ACCESS OBJECT (DAO): CATEGORY DAO
 *
 * ¿Qué es el Patrón DAO?
 * Es un patrón estructural que aísla la capa de negocio de los detalles de persistencia en base de datos.
 * Centraliza las sentencias SQL (INSERT, UPDATE, DELETE, SELECT) asociadas a una tabla específica.
 *
 * Inyección de Conexión:
 * Recibir el objeto 'Connection' por constructor permite que múltiples DAOs (ej. CategoryDao y ProductDao)
 * compartan la misma transacción abierta por un servicio superior, garantizando atomicidad (ACID).
 *
 * Cláusula 'RETURNING id' en PostgreSQL:
 * A diferencia de otros motores que requieren métodos adicionales como 'getGeneratedKeys()',
 * PostgreSQL permite añadir 'RETURNING id' al final del INSERT para devolver directamente
 * el ID autoincremental asignado en un ResultSet inmediato.
 */
public class CategoryDao {
    private final Connection connection;

    public CategoryDao(Connection connection) {
        this.connection = connection;
    }

    /*
     * Inserta una nueva categoría y actualiza el objeto en memoria con su ID autogenerado.
     */
    public Category save(Category category) throws SQLException {
        String sql = "INSERT INTO categories (name) " +
                "VALUES (?) RETURNING id"; // retorna el id (válido en postgre)

        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ){
            statement.setString(1, category.getName());

            // Al usar RETURNING id, la sentencia se ejecuta con executeQuery() para leer la fila devuelta
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    long id = resultSet.getLong("id");
                    category.setId(id);
                    System.out.println("Categoría creada correctamente");
                }
            }
        }

        return category;
    }

    // Método auxiliar para imprimir retroalimentación de operaciones DML según filas afectadas
    private void showMessage(int rows, String msgOK, String msgError){
        if(rows > 0){
            System.out.println(msgOK);
        }else if(!msgError.isBlank()){
            System.out.println(msgError);
        }
    }
}
