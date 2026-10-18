package com.unicoast.project.postgres;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/*
 * CONCEPTO CLAVE: Pool de conexiones JDBC con HikariCP
 * PROPÓSITO: Administrar y reutilizar conexiones hacia la base de datos PostgreSQL de forma eficiente,
 *            minimizando la sobrecarga de apertura y cierre de sockets TCP por cada operación.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Parámetros clave de HikariCP:
 *   - maximumPoolSize: Límite superior de conexiones físicas concurrentes que el pool mantendrá abiertas.
 *   - minimumIdle: Número mínimo de conexiones inactivas listas para su uso inmediato.
 *   - idleTimeout: Tiempo máximo en milisegundos que una conexión inactiva puede permanecer en el pool antes de liberarse.
 *   - connectionTimeout: Tiempo límite que un hilo esperará por una conexión disponible antes de lanzar una excepción SQLException.
 *   - leakDetectionThreshold: Tiempo a partir del cual HikariCP emite una advertencia si una conexión obtenida no ha sido devuelta (cierre olvidado).
 * - Patrón Singleton / Inicializador estático:
 *   El bloque static garantiza que el DataSource se configure e instancie una única vez durante el ciclo de vida de la aplicación.
 */
public class ConnectionPool {
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/java_course");
        config.setUsername("postgres");
        config.setPassword("1234");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);

        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);

        config.setLeakDetectionThreshold(15000);

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("Connection Pool cerrado correctamente.");
        }
    }
}
