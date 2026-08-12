package com.unicoast.project.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * GESTIÓN DEL CICLO DE VIDA DE EXECUTORSERVICE: SHUTDOWN vs SHUTDOWN NOW
 *
 * Cuando se utiliza un 'ExecutorService', es fundamental cerrar el pool de hilos al finalizar
 * las operaciones. De lo contrario, los hilos de trabajo continuarán ejecutándose en segundo plano
 * y la JVM (Java Virtual Machine) no podrá finalizar su proceso.
 *
 * Métodos principales para el cierre de ExecutorService:
 *
 * 1. shutdown() [Apagado Ordenado / Graceful Shutdown]:
 *    - Rechaza nuevas tareas enviadas al executor.
 *    - Permite que las tareas en ejecución y las tareas que están en cola completen su trabajo normalmente.
 *    - No interrumpe los hilos activos.
 *
 * 2. shutdownNow() [Apagado Forzado / Abrupt Shutdown]:
 *    - Rechaza nuevas tareas enviadas al executor.
 *    - Intenta detener de inmediato las tareas en ejecución mediante interrupciones (Thread.interrupt()).
 *    - Drena la cola de tareas pendientes sin ejecutarlas y devuelve una List<Runnable> con las tareas no iniciadas.
 *
 * 3. awaitTermination(long timeout, TimeUnit unit):
 *    - Bloquea el hilo llamante (por ejemplo, el hilo principal) hasta que:
 *      a) Todas las tareas del executor hayan completado su ejecución.
 *      b) Transcurra el tiempo máximo especificado (timeout).
 *      c) El hilo actual sea interrumpido.
 *    - Devuelve 'true' si el executor se apagó completamente, o 'false' si expiró el tiempo de espera.
 */
public class ShutdownComparison {
    public static void main(String[] args) throws InterruptedException {
        // Se crea un pool fijo con 2 hilos de trabajo
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Envío de 4 tareas al pool
        for (int i = 1; i<5; i++){
            final int taskId = i;
            executor.submit(()-> {
                System.out.println("Tarea Iniciando con Executor " + taskId
                        + " " + Thread.currentThread().getName());
                try {
                    // Simulación de tiempo de espera API
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    System.out.println("Tarea " + taskId + " fue interrumpida");
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException error) {}
                    return;
                }
                System.out.println("Tarea finalizada");
            });
        }

        // Pausa de 5 segundos en el hilo principal
        Thread.sleep(5000);

        // Bandera de simulación para cambiar entre apagado forzado u ordenado
        boolean error = true;

        if(error){
            /*
             * APAGADO DE EMERGENCIA:
             * 'shutdownNow()' envía una señal de interrupción a las tareas activas.
             */
            System.out.println("Situación crítica");
            executor.shutdownNow();
        } else {
            /*
             * APAGADO ORDENADO:
             * 'shutdown()' permite que las tareas activas finalicen.
             */
            System.out.println("Finalización ordenada");
            executor.shutdown();
        }

        /*
         * Espera hasta 1 segundo para verificar si el executor completó el apagado de todos sus hilos.
         */
        if(executor.awaitTermination(1, TimeUnit.SECONDS)){
            System.out.println("Tareas finalizadas correctamente");
        } else {
            System.out.println("Las tareas NO finalizaron correctamente");
        }
    }
}
