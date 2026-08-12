package com.unicoast.project.finalProject;

import com.unicoast.project.finalProject.log.model.LogEntry;
import com.unicoast.project.finalProject.log.model.LogSummary;
import com.unicoast.project.finalProject.log.service.LogProcessorTask;
import com.unicoast.project.finalProject.log.service.LogService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * PROYECTO FINAL: PROCESADOR DE LOGS CONCURRENTE Y MULTIHILO
 *
 * Flujo principal de la aplicación:
 * 1. Escanea el directorio 'logs' buscando archivos con extensión '.log'.
 * 2. Crea un 'ExecutorService' con un pool fijo de 3 hilos ('newFixedThreadPool(3)').
 * 3. Lee cada archivo utilizando 'LogService', instancia un 'LogProcessorTask' (Callable)
 *    y lo envía al executor con 'submit()', guardando las referencias 'Future<LogSummary>'.
 * 4. Obtiene los resúmenes calculados llamando a 'future.get()' e imprime los resultados.
 * 5. Cierra el pool de hilos mediante 'shutdown()'.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando análisis de logs...");

        // Referencia a la carpeta que almacena los archivos de registro
        File logsFolder = new File("logs");
        File[] logFiles = logsFolder.listFiles((dir, name) -> name.endsWith(".log"));

        // Verificación de existencia de archivos .log
        if (logFiles == null || logFiles.length == 0) {
            System.out.println("No se encontraron archivos .log en la carpeta 'logs'. Asegúrate de crearla y poner archivos dentro.");
            return;
        }

        LogService service = new LogService();

        // Pool de 3 hilos para procesar múltiples archivos en paralelo
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<LogSummary>> futures = new ArrayList<>();

        // Lectura y envío asíncrono de tareas al executor
        for(File logFile: logFiles){
            List<LogEntry> entries = service.readLogsFromFile(logFile.getAbsolutePath());
            LogProcessorTask task = new LogProcessorTask(entries);
            futures.add(executorService.submit(task));

        }

        // Recuperación bloqueante de resultados futuros e impresión del resumen
        for(Future<LogSummary> future: futures){
            try {
                LogSummary summary = future.get();
                System.out.println(summary);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e.getMessage());
            }
        }

        // Cierre ordenado de los hilos del executor
        executorService.shutdown();
    }
}
