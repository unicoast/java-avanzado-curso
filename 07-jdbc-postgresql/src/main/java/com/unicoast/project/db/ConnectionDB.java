package com.unicoast.project.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * GESTIÓN BÁSICA DE CONEXIÓN CON DRIVERMANAGER
 *
 * ¿Cómo funciona DriverManager?
 * Es la clase fundamental de JDBC encargada de registrar los controladores (drivers) y
 * abrir conexiones físicas directas hacia la base de datos mediante una cadena de conexión (URL).
 *
 * Estructura del JDBC URL:
 * - 'jdbc:'       -> Protocolo principal estándar de Java.
 * - 'postgresql:' -> Subprotocolo que identifica el motor de base de datos.
 * - '//localhost:5432/' -> Host (servidor local) y puerto predeterminado de PostgreSQL.
 * - 'java_course' -> Nombre de la base de datos de destino.
 *
 * Limitación del enfoque directo:
 * Cada llamada a 'DriverManager.getConnection()' abre una nueva conexión física (TCP/IP)
 * y autentica las credenciales en la base de datos. En entornos con múltiples consultas simultáneas,
 * este proceso repetitivo es costoso y degrada el rendimiento, requiriendo el uso de Pools de Conexiones.
 */
public class ConnectionDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/java_course";
    private static final String USER = "postgres";
    private static final String PWD = "1234";

    // Abre y retorna una nueva conexión física a la base de datos
    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PWD);
    }
}
