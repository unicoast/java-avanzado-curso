package com.unicoast.project.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * PATRÓN SINGLETON PARA GESTIÓN DE CONEXIÓN A BASE DE DATOS
 *
 * ¿Qué es el Patrón Singleton?
 * Es un patrón de diseño creacional que garantiza que una clase tenga una ÚNICA instancia
 * en toda la aplicación y proporciona un punto de acceso global a ella.
 *
 * Pilares del Singleton:
 * 1. Constructor Privado: Impide la instanciación externa mediante 'new'.
 * 2. Atributo Estático ('instance'): Almacena la única referencia en memoria.
 * 3. Método de Acceso Estático ('getInstance()'): Retorna la instancia existente o la crea si no existe (Lazy Initialization).
 *
 * Consideraciones en JDBC:
 * Aunque el Singleton reutiliza una misma conexión física reduciendo la creación repetida,
 * compartir una sola conexión entre múltiples hilos concurrentes puede generar cuellos de botella
 * o bloqueos, razón por la cual en entornos de producción se prefiere un Connection Pool.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/java_course";
    private static final String USER = "postgres";
    private static final String PWD = "1234";

    // Referencia única compartida de la clase
    private static DatabaseConnection instance;

    // Conexión física activa asociada al Singleton
    private Connection connection;

    // Debe ser privado porque:
    // - Evita la creación libre de objetos desde fuera de la clase con 'new DatabaseConnection()'.
    // - Obliga a que todas las partes de la aplicación obtengan la conexión a través de 'getInstance()'.
    private DatabaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PWD);
        System.out.println("Base de datos conectada exitosamente");
    }

    // 'synchronized' asegura que solo un hilo a la vez pueda ejecutar este método.
    // Esto evita condiciones de carrera (race conditions) donde dos hilos concurrentes intenten crear dos instancias al mismo tiempo.
    // Además, 'instance.connection.isClosed()' verifica si la conexión se cayó o fue cerrada, permitiendo reconectarse automáticamente de manera transparente.
    public static synchronized DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    // Expone la conexión física activa para ejecutar sentencias SQL
    public Connection getConnection() {
        return connection;
    }
}
