package com.unicoast.project;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * PROCESAMIENTO PARALELO CON PARALLEL STREAM EN JAVA
 *
 * ¿Qué es un parallelStream()?
 * Es una funcionalidad introducida en Java 8 que permite procesar elementos
 * de una colección dividiendo el trabajo entre múltiples hilos de forma automática,
 * aprovechando los núcleos del procesador mediante el pool común 'ForkJoinPool.commonPool()'.
 *
 * Diferencia entre Stream Secuencial y Parallel Stream:
 * 1. Stream Secuencial (stream()): Procesa un elemento a la vez en un solo hilo.
 *    Si cada elemento demora 1 segundo sobre 5 elementos, el tiempo total será de ~5 segundos.
 * 2. Parallel Stream (parallelStream()): Divide los elementos y los ejecuta en paralelo.
 *    Las 5 tareas se ejecutan concurrentemente, completando el lote en ~1 segundo.
 *
 * ¿Por qué es la antesala a la Programación Reactiva?
 * Aunque parallelStream() realiza tareas en paralelo, su operación terminal (forEach) sigue siendo
 * síncrona y bloqueante en el hilo invocador. La programación reactiva (RxJava) da el salto hacia
 * flujos de datos asíncronos, no bloqueantes y guiados por eventos en tiempo real.
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        long start = System.currentTimeMillis();

        numbers.parallelStream().map(
                n -> {
                    try {
                        // Forma clásica anterior de pausar el hilo (en milisegundos):
                        // Thread.sleep(1000);

                        // Forma moderna y más legible con TimeUnit:
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Procesando número: " + n);

                    return n * 2;
                }).forEach(System.out::println);

        long end = System.currentTimeMillis();

        System.out.println("Tiempo total (secuencial): " + (end - start) + " ms");
    }
}
