package com.unicoast.project.stream;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * CATÁLOGO DE MÉTODOS DE STREAM
 * Ejemplos del uso práctico de las Operaciones Intermedias y Terminales
 * más comunes en la API de Streams de Java.
 */
public class StreamMethodsExample {
    public static void main(String[] args) {
        List<String> names = List.of("Novak", "Rafael", "Roger", "Andy", "Stan", "Novak");
        /*
         * 1. filter() [Operación Intermedia] y toList() [Operación Terminal]
         * filter(): Recibe un Predicate para conservar solo los elementos que cumplen la condición.
         * toList(): Recolecta los elementos resultantes en una nueva Lista inmutable.
         * Resultado: [Novak, Rafael, Roger, Novak]
         */
        List<String> longNames = names.stream()
                .filter(name -> name.length() > 4)
                .toList();

        System.out.println(longNames);

        /*
         * 2. map() [Operación Intermedia] y collect() [Operación Terminal]
         * map(): Recibe una Function. Transforma cada elemento del stream en otro tipo de dato 
         *        (en este caso, de String a Integer representando su longitud).
         *
         * collect(): Operación terminal que "recolecta" los elementos del Stream en una estructura.
         * Recibe un Collector (de la clase utilitaria Collectors) que le indica CÓMO recolectar:
         * Collectors.toList() -> en una Lista mutable (Java 8+, alternativa clásica a toList()).
         * Collectors.toSet()  -> en un Set. Collectors.toMap() -> en un Map. Etc.
         * Resultado: [5, 6, 5, 4, 4, 5]
         */
        List<Integer> lengthNames = names.stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(lengthNames);

        /*
         * 3. flatMap() [Operación Intermedia]
         * Se utiliza para "aplanar" estructuras de datos complejas (como listas dentro de listas).
         * Transforma un Stream<List<Integer>> en un único Stream<Integer> continuo.
         * Resultado: [1, 2, 3, 4]
         */
        List<List<Integer>> list = List.of(List.of(1, 2), List.of(3, 4));
        List<Integer> flatNames = list.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(flatNames);

        /*
         * 4. distinct() y sorted() [Operaciones Intermedias]
         * distinct(): Elimina los elementos duplicados del flujo.
         * sorted(): Ordena los elementos según su orden natural (o mediante un Comparator externo).
         * Resultado: [1, 2, 3, 5]
         */
        List<Integer> numbers = List.of(3, 1, 3, 5, 2);
        List<Integer> uniqueOrdered = numbers.stream()
                .distinct()
                .sorted()
                .toList();

        System.out.println(uniqueOrdered);

        /*
         * 5. collect(Collectors.toSet()) [Operación Terminal]
         * Recolecta los elementos en un Set (conjunto). Por definición matemática,
         * la colección Set descartará automáticamente cualquier elemento duplicado 
         * que intente ingresar ("Novak" en este caso).
         * Resultado: [Novak, Rafael, Roger] (sin orden garantizado)
         */
        Set<String> setNames = names.stream()
                .filter(n -> n.length() > 4)
                .collect(Collectors.toSet());

        System.out.println(setNames);

        /*
         * 6. count() [Operación Terminal]
         * Devuelve un valor 'long' que representa la cantidad total de elementos 
         * que lograron llegar hasta el final del pipeline.
         * Resultado: 3 (Rafael, Stan, Novak contienen "a")
         */
        long count = names.stream()
                .filter(n -> n.contains("a"))
                .count();

        System.out.println(count);

        /*
         * 7. reduce() [Operación Terminal]
         * Toma todos los elementos del stream y los "reduce" a un único valor.
         * En este caso, recibe un valor inicial (0) y una función que toma 2 valores y devuelve 1 (BinaryOperator)
         * para ir sumando secuencialmente todos los números del flujo.
         * Resultado: 14 (3+1+3+5+2, usa la lista numbers con duplicados)
         */
        int sum = numbers.stream()
                .reduce(0, (result, number) -> result + number);

        System.out.println(sum);

        /*
         * 8. forEach() [Operación Terminal]
         * Ejecuta una acción (Consumer) para cada elemento del stream.
         * Es una operación que genera un "efecto secundario" (imprimir en consola).
         * Resultado: NOVAK, RAFAEL, ROGER, ANDY, STAN, NOVAK (uno por línea)
         */
        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
