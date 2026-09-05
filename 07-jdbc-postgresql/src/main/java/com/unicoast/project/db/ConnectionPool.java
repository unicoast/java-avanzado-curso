package com.unicoast.project.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/*
 * POOL DE CONEXIONES EMPRESARIAL CON HIKARICP
 *
 * ¿Qué es un Connection Pool (Pool de Conexiones)?
 * Es un repositorio de conexiones de base de datos precalentadas y reutilizables.
 * En lugar de abrir y cerrar conexiones físicas pesadas con cada petición, la aplicación
 * solicita una conexión al pool ('borrow'), la utiliza y al cerrarla ('close()') no se destruye,
 * sino que se devuelve al pool ('recycle') lista para la siguiente tarea.
 *
 * HikariCP:
 * Es la biblioteca de Connection Pool más rápida y eficiente del ecosistema Java,
 * utilizada por defecto en frameworks como Spring Boot.
 *
 * ¿Por qué usar un bloque static?
 * El bloque estático ('static { ... }') se ejecuta una sola vez cuando la JVM carga la clase en memoria.
 * Esto asegura que el pool de conexiones se configure e inicialice de forma única antes de cualquier llamada.
 */
public class ConnectionPool {

    private static HikariDataSource dataSource;

    static {
        System.out.println("Bloque static...");

        HikariConfig config = new HikariConfig();

        // Cadena de conexión JDBC hacia la base de datos PostgreSQL
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/java_course");

        // Nombre de usuario de la base de datos
        config.setUsername("postgres");

        // Contraseña de acceso
        config.setPassword("1234");

        // maximumPoolSize (10): Límite máximo de conexiones físicas simultáneas (activas + inactivas) en el pool
        config.setMaximumPoolSize(10);

        // minimumIdle (2): Cantidad mínima de conexiones en reposo listas para ser usadas de inmediato
        config.setMinimumIdle(2);

        // idleTimeout (30000 ms = 30 s): Tiempo máximo que una conexión inactiva puede permanecer abierta antes de ser reciclada
        config.setIdleTimeout(30000);

        // connectionTimeout (30000 ms = 30 s): Tiempo máximo de espera que aguardará una petición por una conexión libre antes de lanzar SQLException
        config.setConnectionTimeout(30000);

        // leakDetectionThreshold (15000 ms = 15 s): Umbral tras el cual HikariCP registra una advertencia en logs si una conexión no fue cerrada a tiempo (detección de fugas)
        config.setLeakDetectionThreshold(15000);

        // Inicialización del DataSource central a partir de la configuración definida
        dataSource = new HikariDataSource(config);
    }

    // Método para obtener una conexión activa disponible del pool para su uso
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
