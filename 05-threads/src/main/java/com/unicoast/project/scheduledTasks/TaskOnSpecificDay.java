package com.unicoast.project.scheduledTasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * PROGRAMACIÓN DE TAREAS EN UNA FECHA Y HORA ESPECÍFICA
 *
 * Muestra cómo programar la ejecución de una tarea en un instante específico del futuro:
 * 1. Se define la fecha/hora objetivo utilizando 'LocalDateTime'.
 * 2. Se calcula la diferencia (*delay*) en milisegundos entre la hora actual ('LocalDateTime.now()')
 *    y la fecha objetivo mediante 'Duration.between(now, dateTime).toMillis()'.
 * 3. Si el retardo es positivo, se programa la tarea utilizando 'ScheduledExecutorService.schedule()'.
 */
public class TaskOnSpecificDay {
    public static void main(String[] args) {
        // Fecha y hora objetivo para la ejecución de la tarea
        LocalDateTime dateTime = LocalDateTime.of(2026, 8, 11, 19, 25);

        // Obtenemos el instante de tiempo actual
        LocalDateTime now = LocalDateTime.now();

        // Cálculo del tiempo de espera (delay) en milisegundos
        long delay = Duration.between(now, dateTime).toMillis();

        // Validación para evitar programar tareas en fechas del pasado
        if(delay < 0){
            System.out.println("La fecha ya pasó");
            return;
        }

        // Se crea un pool de hilos programado
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        // Programa la tarea para ejecutarse una vez transcurrido el tiempo calculado
        executorService.schedule( () -> {
            System.out.println("Enviando recordatorio");
            executorService.shutdown();
        }, delay, TimeUnit.MILLISECONDS);
    }
}
