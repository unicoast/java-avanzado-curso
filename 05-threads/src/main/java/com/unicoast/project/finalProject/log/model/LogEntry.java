package com.unicoast.project.finalProject.log.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
 * MODELO DE DATOS: ENTRADA DE LOG (LogEntry)
 *
 * Representa la estructura de una entidad individual de evento de registro (log).
 * Utiliza anotaciones de Lombok (@Data, @NoArgsConstructor, @AllArgsConstructor)
 * para generar automáticamente los getters, setters, métodos toString(), equals() y hashCode().
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogEntry {
    private LocalDateTime timestamp;
    private String user;
    private String action;
    private int statusCode;
    private int responseTimeMs;
}
