package com.unicoast.project.example03;

import java.util.Optional;

/*
 * TRANSFORMACIONES Y FILTRADOS CON OPTIONAL (map, flatMap, filter)
 * Permite transformar y filtrar el valor encapsulado sin necesidad de extraerlo
 * del contenedor ("la caja"), de manera similar a las operaciones en Streams.
 */
public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("     niCoLás     ");

//        Optional<String> nameUppercase = name
//                .map(n -> n.toUpperCase());

        /*
         * 1. Transformación con map()
         * Permite aplicar una función de transformación sobre el valor si está presente,
         * obteniendo un nuevo Optional con el resultado. Si está vacío, se retorna
         * un Optional.empty() de inmediato.
         * En este ejemplo se encadena:
         * - map(String::trim): Remoción de espacios en blanco al inicio y final.
         * - map(n -> n.toUpperCase()): Conversión a mayúsculas.
         * - orElse(...): Extracción del resultado o retorno de un mensaje alternativo si estuviera vacío.
         */
        String nameUppercase = name
                .map(String::trim)
                .map(n -> n.toUpperCase())
                .orElse("El nombre no se pudo convertir a mayúsculas");

//        System.out.println("Nombre en Optional<String> nameUppercase = name
//                .map(n -> n.toUpperCase());: " + nameUppercase.orElse("No se pudo convertir el nombre a mayúsculas"));

        System.out.println("Nombre procesado y en mayúsculas: " + nameUppercase);

        /*
         * 2. Transformación con flatMap()
         * Analogía de la caja: Consiste en un contenedor (Optional) que en su interior tiene otro
         * contenedor (Optional) que envuelve el valor real ("Valor interno").
         *
         * Si se aplica map() sobre un método que retorna un Optional, se obtiene
         * una estructura anidada: Optional<Optional<String>>.
         *
         * Con flatMap() es posible aplanar la estructura, de modo que se elimina
         * la envoltura externa y se obtiene directamente un único Optional<String>.
         */
        Optional<Optional<String>> optionalOfOptional = Optional.of(Optional.of("Valor interno"));
        // Optional<Optional<String>> optionalofOptional = Optional.of(Optional.empty());

        // flatMap reduce la estructura de Optional<Optional<T>> a Optional<T>
        Optional<String> resultFlatMap = optionalOfOptional.flatMap(value -> value);
        // Optional<String> resultMap = optionalofOptional.map(op -> op.orElse("default"));

        System.out.println("Resultado de flatMap: " + resultFlatMap);

        /*
         * 3. Filtrado con filter()
         * Permite evaluar el valor dentro del Optional usando una condición (Predicate).
         * - Si la condición se cumple (true): Se mantiene el Optional original con su valor.
         * - Si la condición no se cumple (false) o el Optional estaba vacío: Se obtiene un Optional.empty().
         */
        Optional<String> rut = Optional.of("1234567");
        Optional<String> result = rut.filter(r -> r.startsWith("1"));

        System.out.println("Rut: " + result.orElse("El rut no es válido"));

        /*
         * 4. Ejemplo Práctico Combinado
         * Se realiza una limpieza de espacios y validación sintáctica básica en un pipeline fluido
         * sin condicionales anidadas ni chequeos de null o de longitud.
         */
        Optional<String> email = Optional.ofNullable("     admin@unicoast.dev     ");

        email.map(String::trim)
                .filter(e -> e.contains("@")) // Si no tiene arroba, el Optional se vacía aquí
                .ifPresent(e -> System.out.println("El email es válido: " + e));
    }
}


