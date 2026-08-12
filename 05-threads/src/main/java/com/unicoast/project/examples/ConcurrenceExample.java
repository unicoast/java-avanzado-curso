package com.unicoast.project.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * CONCURRENCIA EN JAVA (CONCURRENCY)
 *
 * ¿Qué es la Concurrencia?
 * Es la capacidad de gestionar y hacer avanzar múltiples tareas en periodos de tiempo superpuestos.
 * No implica necesariamente que las tareas se ejecuten en el mismo instante exacto;
 * se refiere a la estructura del programa para alternar o encolar el trabajo.
 *
 * En este ejemplo:
 * - Se utiliza 'newSingleThreadExecutor()', un Executor con un único hilo de trabajo (un solo actor).
 * - Se envían 3 tareas (A, B y C) de 1.5 segundos cada una.
 * - Al existir solo 1 hilo disponible, las tareas se procesan secuencialmente una tras otra.
 * - El tiempo total de ejecución será de aproximadamente 4500 ms (1500 ms * 3).
 *
 * Concepto clave:
 * Concurrencia es lidiar con muchas cosas a la vez (gestión de tareas).
 */
public class ConcurrenceExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Ejecutando newSingleThreadExecutor");
        ExecutorService single = Executors.newSingleThreadExecutor();

        long start = System.currentTimeMillis();

        // Envío de tareas al executor mediante el método submit()
        single.submit(() -> task("Tarea A"));
        single.submit(() -> task("Tarea B"));
        single.submit(() -> task("Tarea C"));

        // Solicita el apagado ordenado del executor (no acepta nuevas tareas)
        single.shutdown();

        // Espera hasta 1 minuto a que todas las tareas enviadas completen su ejecución
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
