package com.unicoast.project.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/*
 * COLLECTIONS Y PROGRAMACIÓN FUNCIONAL
 * A partir de Java 8, la API de Colecciones (List, Set, Map) se actualizó
 * para integrar el paradigma funcional. Se añadieron métodos que reciben 
 * interfaces funcionales (Consumer, Predicate, UnaryOperator) para recorrer, 
 * filtrar y modificar colecciones de forma declarativa ("qué" hacer, no "cómo").
 */
public class CollectionsExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Novak", "Rafael", "Roger", "Andy", "Stan");

        /*
         * 1. RECORRER (forEach)
         * El método 'forEach' espera un 'Consumer' (recibe un dato, no devuelve nada).
         * En este caso, se utiliza la sintaxis acortada (Referencia a Método):
         * Resultado: Novak, Rafael, Roger, Andy, Stan (uno por línea)
         */
        names.forEach(System.out::println);

        /*
         * Ejemplo equivalente pero desglosado:
         * Se crea el Consumer explícitamente mediante una Lambda y se pasa al forEach.
         */
        Consumer<String> name = (n) -> System.out.println(n);
        names.forEach(name);

        /*
         * List.of() crea una lista INMUTABLE (no permite añadir, eliminar ni nulls).
         * Por ello, se envuelve en un 'new ArrayList<>()' para que sea modificable en este ejemplo.
         */
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5));

        /*
         * 2. ELIMINAR BAJO CONDICIÓN (removeIf)
         * El método 'removeIf' espera un 'Predicate' (recibe un dato, devuelve boolean).
         * IMPORTANTE: Esta operación NO es una función pura. 
         * 'removeIf' muta la lista original, generando un efecto secundario.
         * Resultado: [1, 3, 5] (se eliminaron los pares: 2, 4)
         */
        numbers.removeIf(n -> n % 2 == 0);
        System.out.println(numbers);

        List<String> words = new ArrayList<>(List.of("Java", "Python", "C++", "JavaScript"));
        
        /*
         * 3. TRANSFORMAR (replaceAll)
         * Espera un 'UnaryOperator'. ¿Qué es eso? Es un tipo especial de 'Function'.
         * Mientras que una Function<T, R> puede recibir un tipo y devolver otro distinto,
         * el UnaryOperator<T> OBLIGA a que el tipo de entrada y de salida sean EL MISMO 
         * (Ej: Recibe String, devuelve String modificado).
         * Resultado: [JAVA, PYTHON, C++, JAVASCRIPT]
         */
        words.replaceAll(word -> word.toUpperCase());
        System.out.println(words);
    }
}
