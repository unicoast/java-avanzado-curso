package com.unicoast.project.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * ¿QUÉ ES UN STREAM (Flujo)?
 * Un Stream es una "tubería" o "cinta transportadora" virtual que permite 
 * procesar colecciones de datos (como Listas o Arrays) de forma muy sencilla.
 * 
 * En lugar de usar bucles 'for' o 'while' clásicos, el Stream se conecta a la lista 
 * para definir QUÉ hacer con los datos (filtrar, mapear, imprimir), 
 * dejando que Java se encargue del CÓMO iterarlos internamente.
 * 
 * MUY IMPORTANTE (Inmutabilidad): Los Streams NUNCA alteran los datos originales. 
 * Cualquier transformación o filtro que se aplique dará como resultado un flujo de datos 
 * completamente nuevo, dejando la lista original intacta.
 */
public class StreamExample {
    public static void main(String[] args) {
        /*
         * LISTAS vs STREAMS
         * - Lista: Colección estática. Almacena los datos en memoria.
         * - Stream: Secuencia dinámica. No almacena datos, solo los procesa funcionalmente.
         * 
         * Regla fundamental: Los Streams son inmutables y de "UN SOLO USO". 
         * Una vez consumidos (por ejemplo, con un forEach), no se pueden volver a utilizar.
         */
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream<Integer> streamNumbers = numbers.stream(); // Este Stream ahora está listo para ser consumido
        // streamNumbers.forEach(System.out::println);

        Stream<String> stream = Stream.of("A", "B", "C");
        // stream.forEach(System.out::println);

        String[] array = {"X", "Y", "Z"};
        Stream<String> streamArray = Arrays.stream(array);
        // streamArray.forEach(System.out::println);

        /*
         * GENERACIÓN INFINITA
         * Stream.generate() espera un 'Supplier' (los paréntesis de la lambda están vacíos).
         * El 'limit(5)' es crucial: si no estuviera, este Stream imprimiría "Hola Mundo!" infinitamente.
         */
        Stream<String> holaStream = Stream.generate(() -> "Hola Mundo!").limit(5);
        // holaStream.forEach(System.out::println);

        List<String> names = List.of("Novak", "Rafael", "Roger", "Andy", "Stan");
        
        /*
         * ¿CÓMO DIFERENCIAR UNA OPERACIÓN INTERMEDIA (OI) DE UNA TERMINAL (OT)?
         * 
         * 1. Operación Intermedia (OI):
         *    - Siempre DEVUELVE UN STREAM.
         *    - Se usan para transformar o filtrar datos (Ej: map(), filter(), limit()).
         * 
         * 2. Operación Terminal (OT):
         *    - Devuelve un RESULTADO CONCRETO (otro tipo de dato como List, Integer, o imprime en consola).
         *    - Consumen el Stream por completo.
         *    - Un Stream SOLO puede tener UNA operación terminal (y marca el fin de la tubería).
         *    - Ejemplos: toList(), collect(), forEach(), count().
         * 
         * PIPELINE FUNCIONAL (Encadenamiento)
         * El verdadero poder del Stream es encadenar varias operaciones:
         * 1. stream(): Abre la tubería de datos.
         * 2. filter (OI): Deja pasar solo nombres largos (devuelve Stream).
         * 3. map (OI): Transforma a mayúsculas (devuelve Stream).
         * 4. forEach (OT): Consume el resultado final e imprime (devuelve void, fin del Stream).
         */
        names.stream().filter(name -> name.length() > 4)
                .map((name) -> name.toUpperCase())
                .forEach(System.out::println);

        /*
         * EVALUACIÓN PEREZOSA (Lazy Evaluation)
         * Los Streams son "flojos" (perezosos). Las operaciones intermedias (como map o filter) 
         * NO se ejecutan de inmediato. Solo se van a ejecutar si y solo si al final del 
         * pipeline existe una OPERACIÓN TERMINAL (como forEach, toList, count, etc.).
         * 
         * Regla de oro: UN SOLO USO.
         */
        Stream<String> stream1 = Stream.of("Cristiano", "Messi", "Neymar");
        
        /* 
         * Si se descomenta la línea de abajo, el stream ejecuta su operación terminal (forEach) y se cierra.
         * Al intentar hacer el toList() en la siguiente línea, Java lanzaría la excepción: 
         * 'IllegalStateException: stream has already been operated upon or closed'.
         */
        // stream1.forEach(System.out::println);
        List<String> strings = stream1.toList();
        System.out.println(strings);
    }
}
