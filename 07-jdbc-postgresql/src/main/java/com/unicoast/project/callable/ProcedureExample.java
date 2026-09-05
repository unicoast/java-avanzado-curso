package com.unicoast.project.callable;

import com.unicoast.project.db.ConnectionPool;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

/*
 * EJECUCIÓN DE PROCEDIMIENTOS ALMACENADOS (STORED PROCEDURES) CON CALLABLESTATEMENT
 *
 * Diferencia entre Function y Procedure en JDBC:
 * 1. Function: Retorna un conjunto de datos (TABLE o SETOF) y se invoca con 'SELECT * FROM funcion(?)'.
 * 2. Procedure: Ejecuta lógica DML / transaccional y se invoca con 'CALL procedimiento(?, ?, ?, ?)'.
 *
 * Parámetros de salida (OUT Parameters):
 * - Se registran obligatoriamente antes de la ejecución mediante 'statement.registerOutParameter(index, Types.TIPO)'.
 * - Tras invocar 'statement.execute()', el valor de retorno se recupera con 'statement.get*(index)'.
 */
public class ProcedureExample {

    public static void main(String[] args) {

        // Sentencia SQL para invocar el procedimiento almacenado en PostgreSQL
        String sql = "CALL register_student(?, ?, ?, ?)";

        try (
                Connection connection = ConnectionPool.getConnection();
                CallableStatement statement = connection.prepareCall(sql)
        ) {
            // 1. Asignación de parámetros de entrada (IN)
            statement.setString(1, "Ana Torres");
            statement.setString(2, "ana.torres@example.com");
            statement.setDate(3, Date.valueOf("2002-11-20"));

            // 2. Registro del parámetro de salida (OUT) en la posición 4 con su tipo SQL
            statement.registerOutParameter(4, Types.INTEGER);

            // 3. Ejecución del procedimiento almacenado
            statement.execute();

            // 4. Recuperación del valor retornado por el parámetro OUT
            int generatedId = statement.getInt(4);

            System.out.println("Procedimiento ejecutado correctamente.");
            System.out.println("Nuevo estudiante registrado con ID asignado: " + generatedId);

        } catch (SQLException e) {
            System.out.println("Error al ejecutar el procedimiento almacenado: " + e.getMessage());
        }
    }
}
