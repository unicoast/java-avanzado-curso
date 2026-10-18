package com.unicoast.project.postgres;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/*
 * CONCEPTO CLAVE: Punto de entrada para persistencia con PostgreSQL y PersonService
 * PROPÓSITO: Demostrar el flujo completo de guardado secuencial, consulta individual por ID
 *            y listado general de entidades con fechas mediante la capa de servicio.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Separación en capas: Main interactúa únicamente con PersonService, sin conocer detalles de JDBC ni Connection.
 * - Inserción y consulta: Permite verificar la persistencia de múltiples entidades con LocalDate
 *   y su posterior recuperación tanto individual (findById) como masiva (findAllPersons).
 * - Gestión de recursos: El bloque finally asegura el cierre de ConnectionPool, liberando conexiones físicas y finalizando hilos de HikariCP.
 */
public class Main {
    public static void main(String[] args) {
        PersonService service = new PersonService();

        Person person1 = new Person("Novak Djokovic", LocalDate.of(1987, 5, 22));
        Person person2 = new Person("Rafael Nadal", LocalDate.of(1986, 6, 3));
        Person person3 = new Person("Roger Federer", LocalDate.of(1981, 8, 8));

        try {
            // 1. Guardar las entidades en la base de datos
            service.savePerson(person1);
            service.savePerson(person2);
            service.savePerson(person3);

            // 2. Consulta de registro por clave primaria (ID 1)
            System.out.println("--- Consulta por ID ---");
            Person firstPerson = service.findPersonById(1);

            if (firstPerson != null) {
                System.out.println(firstPerson);
            }

            // 3. Consulta y listado de todas las personas registradas
            System.out.println("\n--- Listado completo de personas ---");
            List<Person> allPersons = service.findAllPersons();
            allPersons.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.closePool();
        }
    }
}
