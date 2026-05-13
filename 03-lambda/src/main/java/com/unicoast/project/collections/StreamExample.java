package com.unicoast.project.collections;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * INTRODUCCIÓN A STREAMS
 * Un Stream es un flujo de datos que permite procesar colecciones de forma
 * declarativa ("qué quiero") en vez de imperativa ("cómo lo hago").
 * Toda operación de Stream se divide en dos categorías:
 * - Intermedia: transforma el flujo (filter, map, sorted). NO ejecuta nada por sí sola.
 * - Terminal: dispara la ejecución del pipeline y produce un resultado (toList, count, forEach).
 */
public class StreamExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Novak", "Rafael", "Roger", "Andy", "Stan");

        /*
         * OPERACIÓN INTERMEDIA: filter()
         * Recibe un 'Predicate' (que devuelve boolean). Deja pasar solo los elementos que cumplan la condición.
         *
         * OPERACIÓN TERMINAL: toList()
         * Recolecta el resultado final en una nueva Lista inmutable, sin afectar la lista 'names'.
         * Resultado: [Novak, Rafael, Roger]
         */
        List<String> filterNames = names.stream()
                                        .filter(name -> name.length() > 4)
                                        .toList();

        System.out.println("Lista original de nombres: " + names);
        System.out.println("Nombres con más de 4 letras " + filterNames);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        /*
         * OPERACIÓN INTERMEDIA: map()
         * Recibe una 'Function' (T -> R). Transforma CADA elemento en otra cosa (aquí multiplica el número por sí mismo).
         *
         * NOTA (Lazy Evaluation): El 'map' no se ejecuta en este momento. 
         * Los Streams son perezosos; solo se ejecutan cuando se invoca una Operación Terminal (como toList).
         * Resultado: [1, 4, 9, 16, 25]
         */
        Stream<Integer> integerStream = numbers.stream()
                                         .map(n -> n * n);

        // Al invocar toList(), el Stream "fluye" y ejecuta el map() para generar la nueva lista.
        List<Integer> squaredNumbers = integerStream.toList();

        System.out.println("Números originales: " + numbers);
        System.out.println("Números al cuadrado: " + squaredNumbers);
    }
}
