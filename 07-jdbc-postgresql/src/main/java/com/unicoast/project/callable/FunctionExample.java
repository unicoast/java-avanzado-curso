package com.unicoast.project.callable;

import com.unicoast.project.db.ConnectionPool;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * EJECUCIÓN DE FUNCIONES ALMACENADAS (STORED FUNCTIONS) CON CALLABLESTATEMENT
 *
 * ¿Qué es CallableStatement?
 * Es la interfaz de JDBC diseñada específicamente para ejecutar procedimientos
 * y funciones almacenadas (Stored Procedures / Functions) en el motor de base de datos.
 *
 * Funciones en PostgreSQL (PL/pgSQL):
 * En PostgreSQL, las funciones que retornan conjuntos de resultados (SETOF o TABLE)
 * se ejecutan utilizando la sintaxis 'SELECT * FROM nombre_funcion(?)'.
 *
 * Flujo de Trabajo:
 * 1. connection.prepareCall(sql): Prepara la llamada a la función almacenada.
 * 2. statement.setInt(1, 10): Asigna el valor del parámetro de búsqueda por posición (1-based index).
 * 3. statement.executeQuery(): Ejecuta la función en el servidor PostgreSQL y recupera el cursor 'ResultSet'.
 * 4. Iteración y extracción tipada: Lee las columnas devueltas ('id', 'name', 'email', 'birth_date').
 */
public class FunctionExample {

    public static void main(String[] args) {
        try (Connection connection = ConnectionPool.getConnection()) {

            // Sentencia SQL para llamar a la función almacenada 'find_by_id_students' en PostgreSQL
            String sql = "SELECT * FROM find_by_id_students(?)";
            try (CallableStatement statement = connection.prepareCall(sql)) {
                // Asignar el ID 10 como parámetro de entrada
                statement.setInt(1, 10);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    Date birthDate = resultSet.getDate("birth_date");

                    System.out.println("ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Email: " + email);
                    System.out.println("Nacimiento: " + birthDate);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al ejecutar la función almacenada: " + e.getMessage());
        }
    }
}
