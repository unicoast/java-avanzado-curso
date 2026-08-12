package com.unicoast.project.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * CONCURRENCIA EN JAVA: EXECUTOR SERVICE (Thread Pools)
 *
 * ¿Qué es un ExecutorService?
 * Es una interfaz del paquete 'java.util.concurrent' que gestiona y reutiliza
 * un grupo de hilos (Thread Pool) para ejecutar tareas de forma asíncrona.
 *
 * Ventajas sobre la creación manual de hilos (new Thread()):
 * 1. REUTILIZACIÓN DE HILOS: Reutiliza los mismos hilos en lugar de crearlos y destruirlos constantemente.
 * 2. CONTROL DE RECURSOS: Con 'newFixedThreadPool(2)', se limita la ejecución a un máximo de 2 hilos simultáneos.
 * 3. GESTIÓN DEL CICLO DE VIDA: El método 'shutdown()' apaga el pool ordenadamente una vez completadas las tareas.
 */
public class ExecutorExample {
    public static void main(String[] args) {
        // Se crea un pool fijo con 2 hilos ejecutores
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Envío de tareas Runnable al pool mediante expresiones Lambda
        executor.execute( () -> System.out.println("Tarea A " + Thread.currentThread().getName()));
        executor.execute( () -> System.out.println("Tarea B " + Thread.currentThread().getName()));
        executor.execute( () -> System.out.println("Tarea C " + Thread.currentThread().getName()));

        // Apagado ordenado del pool para liberar recursos
        executor.shutdown();
    }
}
