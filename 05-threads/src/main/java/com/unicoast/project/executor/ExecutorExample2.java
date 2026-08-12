package com.unicoast.project.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * TIPOS DE THREAD POOLS EN EXECUTORSERVICE
 *
 * La clase de utilidad 'Executors' proporciona métodos de fábrica (factory methods)
 * para instanciar distintos tipos de 'ExecutorService' según las necesidades del sistema:
 *
 * 1. FIXED THREAD POOL (newFixedThreadPool):
 *    - Mantiene un número fijo y determinado de hilos reutilizables (en este ejemplo, 2).
 *    - Las tareas adicionales esperan en una cola hasta que un hilo se libere.
 *    - Recomendado para controlar y limitar el uso de recursos del sistema.
 *
 * 2. CACHED THREAD POOL (newCachedThreadPool):
 *    - Crea hilos a medida que se necesitan y reutiliza aquellos que se encuentren libres.
 *    - Si un hilo permanece 60 segundos inactivo, es destruido para liberar memoria.
 *    - Recomendado para múltiples tareas asíncronas de corta duración.
 *
 * 3. SINGLE THREAD EXECUTOR (newSingleThreadExecutor):
 *    - Utiliza únicamente un hilo de trabajo para procesar todas las tareas enviadas.
 *    - Garantiza la ejecución secuencial y ordenada (FIFO) de las tareas.
 *    - Útil cuando se requiere que las tareas se ejecuten de forma estrictamente lineal sin concurrencia.
 */
public class ExecutorExample2 {
    public static void main(String[] args) {
        // Definición de una tarea reutilizable mediante una expresión Lambda (Runnable)
        Runnable task = () -> {
            System.out.println("Ejecutando la tarea " + Thread.currentThread().getName());
            try {
                // Simula la ejecución de un trabajo pesado (1.5 segundos)
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Tarea completada en el hilo " + Thread.currentThread().getName());
        };

        /*
         * 1. FIXED THREAD POOL
         * Con un límite de 2 hilos y 5 tareas enviadas:
         * Las primeras 2 tareas se ejecutan simultáneamente en los hilos disponibles.
         * Las 3 tareas restantes aguardan en la cola hasta que se libere un hilo.
         */
        System.out.println("Ejecutando newFixedThreadPool");
        ExecutorService fixedPool = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 5; i++){
            fixedPool.execute(task);
        }
        fixedPool.shutdown(); // Inicia el apagado ordenado tras completar las tareas enviadas

        /*
         * 2. CACHED THREAD POOL
         * Al no contar con un límite rígido de hilos, el pool creará automáticamente
         * los hilos necesarios (en este caso 5 hilos) para procesar las tareas enviadas.
         */
        System.out.println("Ejecutando newCachedThreadPool");
        ExecutorService cachedPool = Executors.newCachedThreadPool();

        for (int i = 1; i <= 5; i++){
            cachedPool.execute(task);
        }
        cachedPool.shutdown(); // Inicia el apagado ordenado tras completar las tareas enviadas

        /*
         * 3. SINGLE THREAD EXECUTOR
         * Un único hilo procesa todas las tareas enviadas de manera secuencial,
         * una tras otra en el orden en que fueron recibidas.
         */
        System.out.println("Ejecutando newSingleThreadExecutor");
        ExecutorService single = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 5; i++){
            single.execute(task);
        }
        single.shutdown(); // Inicia el apagado ordenado tras completar las tareas enviadas
    }
}
