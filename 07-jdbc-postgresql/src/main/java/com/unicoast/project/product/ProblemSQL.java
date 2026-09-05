package com.unicoast.project.product;

import com.unicoast.project.db.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * VULNERABILIDAD DE INYECCIÓN SQL (SQL INJECTION) Y EL RIESGO DE 'STATEMENT'
 *
 * ¿Qué es la Inyección SQL (SQL Injection)?
 * Es una vulnerabilidad de seguridad crítica donde un atacante manipula la estructura
 * lógica de una consulta SQL aprovechando la concatenación directa de cadenas de texto.
 *
 * Anatomía del ataque en este ejemplo:
 * 1. Entrada normal esperada: 'Televisor Samsung'
 * 2. Carga maliciosa (Payload): "Televisor Samsung'); DROP TABLE products; --"
 * 3. Consulta resultante concatenada:
 *    INSERT INTO products (name, price, stock) VALUES ('Televisor Samsung'); DROP TABLE products; --', 29.99, 10)
 *    - Cierra el primer INSERT con ');
 *    - Inyecta la instrucción destructiva DROP TABLE products;
 *    - Invalida el resto de la sintaxis con el comentario SQL --
 *
 * ¿Cómo se soluciona?
 * PROHIBIDO concatenar valores en sentencias SQL.
 * Se debe utilizar siempre 'PreparedStatement' con parámetros parametrizados ('?'),
 * donde el motor trata las entradas estrictamente como datos literales y nunca como código ejecutable.
 */
public class ProblemSQL {
    public static void main(String[] args) {
        // Entrada legítima normal (comentada):
        // String name = "Televisor Samsung";

        // Simulación de entrada maliciosa con inyección SQL:
        String name = "Televisor Samsung'); DROP TABLE products; --";
        double price = 29.99;
        int stock = 10;

        // Concatenación insegura de strings que produce la vulnerabilidad:
        String sql = "INSERT INTO products (name, price, stock) VALUES ('" + name + "', " + price + ", " + stock + ")";

        // Ejemplo resultante tras la concatenación (comentado):
        // INSERT INTO products (name, price, stock) VALUES ('Televisor Samsung');
        // DROP TABLE products; --', 29.99, 10)

        try (Connection conn = ConnectionDB.connection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Producto insertado (Statement)");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
