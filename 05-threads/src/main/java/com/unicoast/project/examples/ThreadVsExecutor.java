package com.unicoast.project.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * COMPARACIÓN: CREACIÓN MANUAL DE THREADS vs EXECUTORSERVICE
 *
 * ¿Por qué usar ExecutorService en lugar de 'new Thread()'?
 *
 * 1. CREACIÓN MANUAL (new Thread()):
 *    - Cada iteración crea un nuevo hilo en el Sistema Operativo.
 *    - Es costoso en memoria y procesamiento (crear y destruir hilos constantemente).
 *    - No hay límite: si lanzas miles de iteraciones, crearás miles de hilos reales, lo que puede saturar el sistema.
 *
 * 2. EXECUTORSERVICE (newFixedThreadPool):
 *    - Administra un grupo (pool) con un número determinado de hilos (en este caso 2).
 *    - Reutiliza los mismos hilos para procesar todas las tareas enviadas.
 *    - Si se envían más tareas de los hilos disponibles, las coloca en cola sin saturar la máquina.
 *    - Requiere llamar a 'shutdown()' para liberar el pool y permitir que el programa finalice.
 */
public class ThreadVsExecutor {
    public static void main(String[] args) {
        // 1. Ejecución creando un nuevo Thread en cada iteración
        System.out.println("Con Thread -----------");
        for (int i = 1; i < 4; i++){
            new Thread(
                    () -> System.out.println("Tarea A Thread " + Thread.currentThread().getName())
            ).start();
        }

        /*
         * 2. Ejecución reutilizando hilos mediante ExecutorService
         * newFixedThreadPool(2) crea únicamente 2 hilos que procesarán las 3 tareas en cola.
         */
        System.out.println("Con Executor -----------");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 1; i < 4; i++){
            /*
             * executor.execute(Runnable): Envía la tarea al pool para que uno de los hilos
             * libres la tome y la ejecute.
             */
            executor.execute(
                    () -> System.out.println("Tarea A Executor " + Thread.currentThread().getName())
            );
        }

        // Cierra el pool de hilos ordenadamente tras completar las tareas enviadas
        executor.shutdown();
    }
}
