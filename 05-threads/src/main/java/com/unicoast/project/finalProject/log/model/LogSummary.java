package com.unicoast.project.finalProject.log.model;

import java.util.Map;
import java.util.Set;

/*
 * MODELO DE DATOS: RESUMEN DE LOGS (LogSummary)
 *
 * Contiene las métricas y estadísticas consolidadas del procesamiento de un archivo de logs:
 * - totalEntries: Total de registros procesados.
 * - errorCount: Cantidad de errores detectados (código HTTP >= 400).
 * - uniqueUsers: Conjunto (Set) con los nombres de usuario únicos.
 * - averageResponseTime: Tiempo promedio de respuesta en milisegundos.
 * - errorCountsByCode: Mapa que relaciona cada código HTTP de error con su frecuencia.
 */
public class LogSummary {
    private int totalEntries;
    private int errorCount;
    private Set<String> uniqueUsers;
    private double averageResponseTime;
    private Map<Integer, Long> errorCountsByCode;

    public LogSummary(int totalEntries, int errorCount, Set<String> uniqueUsers, double averageResponseTime, Map<Integer, Long> errorCountsByCode) {
        this.totalEntries = totalEntries;
        this.errorCount = errorCount;
        this.uniqueUsers = uniqueUsers;
        this.averageResponseTime = averageResponseTime;
        this.errorCountsByCode = errorCountsByCode;
    }

    public String toString() {
        return "Total: " + totalEntries +
                ", Errores: " + errorCount +
                ", Usuarios únicos: " + uniqueUsers.size() +
                ", Tiempo promedio: " + averageResponseTime + "ms" +
                ", Errores por código: " + errorCountsByCode;
    }
}
