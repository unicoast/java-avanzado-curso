package com.unicoast.project.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * PARALELISMO EN JAVA (PARALLELISM)
 *
 * ¿Qué es el Paralelismo?
 * Es la ejecución SIMULTÁNEA de múltiples tareas exactamente en el mismo instante de tiempo.
 * Requiere soporte de hardware (múltiples núcleos de CPU o procesadores lógicos)
 * y múltiples hilos de ejecución activos en paralelo.
 *
 * Diferencia fundamental entre Concurrencia y Paralelismo:
 * - Concurrencia: Gestionar y estructurar múltiples tareas a la vez (pueden turnarse en 1 solo hilo).
 * - Paralelismo: Hacer y ejecutar múltiples tareas en el mismo instante de tiempo (múltiples hilos).
 *
 * En este ejemplo:
 * - Se utiliza 'newFixedThreadPool(3)' para contar con 3 hilos de trabajo simultáneos (3 actores).
 * - Se envían 3 tareas (A, B y C) de 1.5 segundos cada una.
 * - Al ejecutarse en paralelo en 3 hilos distintos al mismo tiempo, el tiempo total será de ~1500 ms (en lugar de ~4500 ms).
 */
public class ParallelismExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Ejecutando newSingleThreadExecutor");
        // 3 actores que resuelven las tareas en forma simultanea en 3 hilos distintos
        ExecutorService single = Executors.newFixedThreadPool(3);

        long start = System.currentTimeMillis();

        // Envío de las 3 tareas al pool de hilos mediante submit()
        single.submit(() -> task("Tarea A"));
        single.submit(() -> task("Tarea B"));
        single.submit(() -> task("Tarea C"));

        // Solicita el apagado ordenado del executor
        single.shutdown();

        // Espera hasta 1 minuto a que todas las tareas en ejecución completen su trabajo
        if(!single.awaitTermination(1, TimeUnit.MINUTES)){
            System.out.println("Tareas demorads, forzando salida");
            single.shutdown();
        }

        long end = System.currentTimeMillis();

        System.out.println("Tiempo total: " + (end - start) + " ms");
    }

    /*
     * Método auxiliar que simula una tarea pesada con latencia mediante Thread.sleep().
     */
    public static void task(String name){
        System.out.println("Iniciando la tarea " + Thread.currentThread().getName());
        try {
            // Simula la ejecución de un trabajo de 1.5 segundos
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Tarea completada en el hilo " + Thread.currentThread().getName());
    }
}
