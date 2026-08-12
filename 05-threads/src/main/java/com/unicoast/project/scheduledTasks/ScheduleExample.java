package com.unicoast.project.scheduledTasks;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * PROGRAMACIÓN DE TAREAS TEMPORIZADAS CON SCHEDULEDEXECUTORSERVICE
 *
 * En Java, la interfaz 'ScheduledExecutorService' (paquete java.util.concurrent)
 * reemplaza a la antigua clase 'java.util.Timer' para programar tareas con retrasos
 * o de forma periódica utilizando pools de hilos.
 *
 * Métodos principales:
 * 1. schedule(Runnable, delay, unit): Ejecuta una tarea una sola vez tras un retardo (*delay*).
 * 2. scheduleAtFixedRate(Runnable, initialDelay, period, unit):
 *    Ejecuta una tarea de forma periódica con un intervalo fijo, independientemente de la duración de la tarea.
 * 3. scheduleWithFixedDelay(Runnable, initialDelay, delay, unit):
 *    Ejecuta una tarea periódicamente con un retardo fijo entre el fin de una ejecución y el inicio de la siguiente.
 */
public class ScheduleExample {

    public static void main(String[] args) {
        /*
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        executorService.schedule( () -> {
            System.out.println("Tarea después de 4 segundos");
        }, 4, TimeUnit.SECONDS);

        // cerró el recurso para que no entren más tareas
        // executorService.shutdown();

        executorService.schedule( () -> {
            System.out.println("Tarea después de 5 segundos");
        }, 5, TimeUnit.SECONDS);
        executorService.shutdown();
         */

        // Ejemplo heredado (Legacy) usando java.util.Timer
        /*
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Tarea de dos segundos");
                timer.cancel();
            }
        }, 2000);
         */

        // Instancia un pool programado con 1 hilo
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Definición de una tarea con clase anónima que cuenta las ejecuciones para autocerrarse
        Runnable task = new Runnable() {
            int counter = 0;
            @Override
            public void run() {
                System.out.println("Enviando recordatorio");
                counter++;
                if(counter > 3){
                    System.out.println("Se enviaron todos los recordatorios");
                    executor.shutdown();
                }
            }
        };

        /*
        executor.scheduleAtFixedRate( () -> {
            System.out.println("Enviando recordatorio");
        }, 0, 3, TimeUnit.SECONDS);
         */

        // Programa la tarea periódica: inicio inmediato (0s) y repetición cada 3 segundos
        executor.scheduleAtFixedRate( task,  0, 3, TimeUnit.SECONDS);
    }
}
