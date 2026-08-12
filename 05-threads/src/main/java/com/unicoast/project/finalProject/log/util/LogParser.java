package com.unicoast.project.finalProject.log.util;

import com.unicoast.project.finalProject.log.model.LogEntry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/*
 * UTILIDAD: PARSEADOR DE LÍNEAS DE LOG (LogParser)
 *
 * Transforma líneas de texto plano provenientes del archivo de registro en objetos 'LogEntry'.
 *
 * Formato esperado de cada línea:
 * "yyyy-MM-dd HH:mm:ss | usuario | accion | codigoEstado | tiempoRespuestaMs"
 * Ejemplo: "2025-05-12 09:01:35 | user_e | checkout | 200 | 850"
 */
public class LogParser {
    // Formateador de fecha y hora acorde a las estampas de tiempo de los archivos de registro
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Parsea una línea de texto y devuelve un Optional<LogEntry>. Retorna Optional.empty() ante errores de formato.
    public static Optional<LogEntry> parseLine(String line){
        try {
            // 2025-05-12 09:01:35 | user_e | checkout | 200 | 850
            // Se usa "\\|" porque el caracter pipe '|' es un operador especial en expresiones regulares (RegEx) que requiere escape '\\'
            String[] parts = line.split("\\|");

            if(parts.length != 5) return Optional.empty();

            LocalDateTime timestamp = LocalDateTime.parse(parts[0].trim(), formatter);
            String user = parts[1].trim();
            String action = parts[2].trim();
            int statusCode = Integer.parseInt(parts[3].trim());
            int responseTimeMs = Integer.parseInt(parts[4].trim());

            return Optional.of(new LogEntry(timestamp, user, action, statusCode, responseTimeMs));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
