package com.unicoast.project.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

/*
 * CONCEPTO CLAVE: Serialización y Deserialización JSON con Jackson y Java 8 Time (JSR-310)
 * PROPÓSITO: Demostrar cómo Jackson transforma entidades Java con campos LocalDate hacia y desde JSON.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Registro de JavaTimeModule:
 *   Por defecto, Jackson Databind solo soporta los tipos heredados (java.util.Date).
 *   Para soportar java.time.LocalDate sin errores de definición, es obligatorio registrar
 *   la extensión JavaTimeModule (dependencia jackson-datatype-jsr310).
 * - Uso de Text Blocks ("""):
 *   Introducidos como estándar en Java 15, permiten definir cadenas JSON multilínea limpias
 *   sin requerir caracteres de escape (\").
 */
public class Person {
    private String name;

    /*
     * @JsonFormat sirve para definir el formato de serialización y deserialización de la fecha:
     * 1. Deserialización (JSON -> Objeto Java):
     *    Indica a Jackson el patrón textual exacto ("dd/MM/yyyy") para interpretar la fecha recibida.
     *    Sin esta anotación, Jackson esperaría el estándar ISO-8601 ("1981-08-08") y fallaría al recibir barras.
     * 2. Serialización (Objeto Java -> JSON):
     *    Indica a Jackson cómo formatear el LocalDate en texto al generar la salida JSON.
     */
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    public Person() {
    }

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public static void main(String[] args) throws JsonProcessingException {
        // ObjectMapper es el componente central de Jackson para serializar y deserializar
        ObjectMapper mapper = new ObjectMapper();

        // Registro indispensable: habilita el soporte para los tipos de java.time (JSR-310)
        mapper.registerModule(new JavaTimeModule());

        // 1. Serialización: Objeto Java -> Cadena JSON
        Person person = new Person("Novak", LocalDate.of(1987, 5, 22));
        String json = mapper.writeValueAsString(person);
        System.out.println(json);

        // 2. Deserialización: Cadena JSON con formato "dd/MM/yyyy" -> Objeto Java Person
        String roger = """
            {
                "name": "Roger",
                "birthDate": "08/08/1981"
            }
        """;

        Person person2 = mapper.readValue(roger, Person.class);
        System.out.println(person2.getName());
        System.out.println(person2.getBirthDate());
    }
}
