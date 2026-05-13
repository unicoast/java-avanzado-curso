package com.unicoast.project.record;

import java.util.Objects;

/*
 * RECORD (Java 16+)
 * Forma sintética de declarar una clase inmutable. En una línea, Java genera:
 * campos private final, constructor principal (canónico), métodos de acceso (name(), price()),
 * equals(), hashCode() y toString().
 *
 * No permiten setters ni extender clases, pero SÍ implementar interfaces,
 * tener métodos/atributos estáticos y constructores adicionales.
 * Caso de uso ideal: DTOs, respuestas de API, contenedores de datos sin lógica de negocio.
 */
public record ProductDto(String name, double price) {
    // La línea de arriba YA define el constructor principal (canónico): ProductDto(String name, double price).
    // Java lo genera internamente con: this.name = name; this.price = price;
    // NO se escribe manualmente, Java lo genera automáticamente.

    public static String ATTRIBUTE = "Hola";

    /*
     * Constructor compacto (se escribe SIN paréntesis):
     * NO es un constructor nuevo. Es una versión REDUCIDA del canónico.
     * Se llama "compacto" porque ahorra escribir los paréntesis y la asignación de campos.
     * Solo se escribe la validación, y Java asigna this.name y this.price automáticamente al final.
     */
    public ProductDto {
        Objects.requireNonNull(name);
    }

    /*
     * Constructor personalizado (con paréntesis DISTINTOS al canónico):
     * DEBE llamar al constructor principal (canónico) usando this(...).
     * this(name, 0) -> llama a ProductDto(String name, double price) con price = 0.
     */
    public ProductDto(String name) {
        this(name, 0);
    }

    public static void methodStatic(){
        System.out.println("Hola soy un método estático");
    }
}
