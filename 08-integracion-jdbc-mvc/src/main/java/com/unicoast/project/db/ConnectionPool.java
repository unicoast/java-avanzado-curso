package com.unicoast.project.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/*
 * POOL DE CONEXIONES EMPRESARIAL CON HIKARICP
 *
 * ¿Qué es un Connection Pool (Pool de Conexiones)?
 * Es un mecanismo de optimización que mantiene un conjunto de conexiones físicas a la base de datos
 * abiertas y listas para ser reutilizadas. En lugar de crear y destruir una conexión física con cada consulta
 * (operación costosa a nivel de red y procesamiento), la aplicación toma una conexión prestada del pool,
 * ejecuta sus sentencias y, al invocar 'connection.close()', la devuelve al pool para su reciclaje.
 *
 * HikariCP:
 * Biblioteca de alto rendimiento seleccionada por defecto en entornos empresariales y frameworks como Spring Boot.
 * Ofrece latencias mínimas, recolección de métricas y detección inteligente de fugas de conexión.
 *
 * Inicialización mediante bloque static:
 * El bloque estático se ejecuta una sola vez cuando la máquina virtual Java (JVM) carga la clase en memoria.
 * Esto asegura que el DataSource se configure de manera centralizada y thread-safe antes de atender peticiones.
 */
public class ConnectionPool {

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();

        // Parámetros de conexión JDBC hacia el motor PostgreSQL:
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/java_course");
        config.setUsername("postgres");
        config.setPassword("1234");

        // maximumPoolSize (10): Límite máximo de conexiones físicas concurrentes gestionadas por el pool.
        config.setMaximumPoolSize(10);

        // minimumIdle (2): Conexiones mínimas inactivas que se mantienen disponibles en reposo.
        config.setMinimumIdle(2);

        // idleTimeout (30000 ms = 30 s): Tiempo máximo que una conexión inactiva puede permanecer en el pool antes de ser reciclada.
        config.setIdleTimeout(30000);

        // connectionTimeout (30000 ms = 30 s): Tiempo máximo de espera para obtener una conexión libre antes de lanzar SQLException.
        config.setConnectionTimeout(30000);

        // leakDetectionThreshold (15000 ms = 15 s): Umbral para registrar una alerta en logs si una conexión no se cierra a tiempo.
        config.setLeakDetectionThreshold(15000);

        dataSource = new HikariDataSource(config);
    }

    /*
     * Obtener una conexión activa del pool.
     * Retornar un objeto Connection que debe cerrarse en un bloque finally o try-with-resources para volver al pool.
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /*
     * Cerrar el pool de conexiones y liberar todos los sockets y recursos vinculados.
     * Se debe invocar al apagar o salir de la aplicación.
     */
    public static void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("Connection Pool cerrado correctamente.");
        }
    }
}