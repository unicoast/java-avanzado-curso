package com.unicoast.project.postgres;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/*
 * CONCEPTO CLAVE: Entidad de dominio con LocalDate y Lombok
 * PROPÓSITO: Modelar la tabla 'persons' de la base de datos PostgreSQL, mapeando la columna DATE a java.time.LocalDate.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Mapeo de tipos:
 *   - PostgreSQL 'DATE' -> Java 'java.time.LocalDate'.
 *   - PostgreSQL 'BIGSERIAL' / 'INTEGER' -> Java 'int' / 'long'.
 * - Lombok:
 *   - @Data: Genera automáticamente getters, setters, equals(), hashCode() y toString().
 *   - @AllArgsConstructor: Provee el constructor con todos los campos (id, name, birthDate), ideal para lecturas (SELECT).
 * - Constructor secundario: Permite instanciar registros nuevos antes de persistirlos, omitiendo el ID autogenerado por la BD.
 */
@Data
@AllArgsConstructor
public class Person {
    private int id;
    private String name;
    private LocalDate birthDate;

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}
