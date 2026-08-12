package com.unicoast.project.finalProject.log.service;

import com.unicoast.project.finalProject.log.model.LogEntry;
import com.unicoast.project.finalProject.log.model.LogSummary;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/*
 * TAREA DE PROCESAMIENTO ASÍNCRONO DE LOGS (LogProcessorTask)
 *
 * Implementa la interfaz 'Callable<LogSummary>', lo que permite que cada lote de datos
 * se procese asíncronamente dentro de un hilo del 'ExecutorService'.
 *
 * Cálculos realizados con la API de Java Streams:
 * 1. Conteo total de entradas enviadas a la tarea.
 * 2. Filtrado de entradas de error (statusCode >= 400).
 * 3. Recolección de usuarios únicos en un Set.
 * 4. Cálculo del promedio de tiempo de respuesta con mapToInt() y average().
 * 5. Agrupamiento y conteo de errores por código de estado mediante Collectors.groupingBy().
 */
public class LogProcessorTask implements Callable<LogSummary> {
    private final List<LogEntry> logEntries;

    public LogProcessorTask(List<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    /*
     * Método call(): Ejecutado asíncronamente en el hilo de trabajo.
     * Procesa la lista de registros y retorna un objeto LogSummary con las métricas consolidadas.
     */
    @Override
    public LogSummary call() throws Exception {
        System.out.println("Tarea: Procesando " + logEntries.size() + " " + "entradas de log en el hilo " + Thread.currentThread().getName());

        int totalEntries = logEntries.size();

        // statusCode >= 400 se considera error
        List<LogEntry> errorsLogs = logEntries.stream().filter(e -> e.getStatusCode() >= 400).toList();
        int errorCount = errorsLogs.size();

        Set<String> uniqueUsers = logEntries.stream()
                .map(LogEntry::getUser)
                .collect(Collectors.toSet());

        /*
        double averageResponseTime = logEntries.stream()
                .map(LogEntry::getResponseTimeMs)
                .collect(Collectors.averagingDouble(Integer::intValue));
        */

        double averageResponseTime = logEntries.stream()
                .mapToInt(LogEntry::getResponseTimeMs)
                .average()
                .orElse(0.0);

        Map<Integer, Long> errorCountsByCode = errorsLogs.stream()
                .collect(Collectors.groupingBy(
                        LogEntry::getStatusCode,
                        Collectors.counting()
                ));

        System.out.println("Finalizando: " + logEntries.size() + " " + "entradas de log en el hilo " + Thread.currentThread().getName());

        return new LogSummary(totalEntries, errorCount, uniqueUsers, averageResponseTime, errorCountsByCode);
    }
}
