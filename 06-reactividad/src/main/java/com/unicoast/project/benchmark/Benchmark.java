package com.unicoast.project.benchmark;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * BENCHMARK DE RENDIMIENTO: STREAM SECUENCIAL vs PARALLEL STREAM
 *
 * ¿Cuál es el propósito de esta prueba de rendimiento (Benchmark)?
 * Evaluar el impacto de la paralelización automática en operaciones intensivas de CPU
 * sobre una estructura de datos de gran tamaño (70 millones de elementos).
 *
 * Factores a considerar en Parallel Streams:
 * 1. Operaciones Intensivas de CPU (CPU-bound):
 *    - El cálculo matemático (i * i) para cada elemento se beneficia significativamente
 *      al distribuirse entre los múltiples núcleos de la máquina.
 *
 * 2. Costo de División y Unión (Overhead):
 *    - La paralelización requiere dividir la colección en bloques (spliterators),
 *      asignar tareas a los hilos de ForkJoinPool y combinar los resultados finales.
 *    - Para colecciones pequeñas (ej. 100 elementos), el stream secuencial suele ser más rápido
 *      porque no incurre en el costo de gestión de hilos.
 *
 * 3. Independencia de Elementos:
 *    - Las operaciones en paralelo solo son seguras si cada transformación es una función pura,
 *      sin efectos secundarios ni modificaciones de variables compartidas.
 */
public class Benchmark {
    public static void main(String[] args) {
        System.out.println("Generando conjunto de 70,000,000 elementos en memoria...");

        // Creación de un Set con 70 millones de enteros únicos
        Set<Integer> set = IntStream.range(1, 70_000_000)
                .boxed()
                .collect(Collectors.toSet());

        System.out.println("Iniciando pruebas de rendimiento...");

        /*
         * PRUEBA 1: Stream Secuencial (un solo hilo)
         * Procesa cada elemento uno tras otro en el hilo principal.
         */
        long start = System.currentTimeMillis();
        set.stream().map(i -> i * i).count();
        long end = System.currentTimeMillis();
        System.out.println("Tiempo con Stream Secuencial: " + (end - start) + " ms");

        /*
         * PRUEBA 2: Parallel Stream (múltiples hilos concurrentes)
         * Divide el conjunto entre los núcleos lógicos del procesador.
         */
        start = System.currentTimeMillis();
        set.parallelStream().map(i -> i * i).count();
        end = System.currentTimeMillis();
        System.out.println("Tiempo con Parallel Stream:   " + (end - start) + " ms");
    }
}
