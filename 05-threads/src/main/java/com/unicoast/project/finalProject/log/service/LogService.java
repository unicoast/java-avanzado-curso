package com.unicoast.project.finalProject.log.service;

import com.unicoast.project.finalProject.log.model.LogEntry;
import com.unicoast.project.finalProject.log.util.LogParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/*
 * SERVICIO: LECTURA DE ARCHIVOS DE LOG (LogService)
 *
 * Utiliza las APIs de Java NIO.2 ('Files.lines()') y Java Streams para leer eficientemente
 * archivos de registro en disco, filtrar las líneas válidas y convertirlas en una 'List<LogEntry>'.
 */
public class LogService {
     // Lee un archivo de texto por su ruta y retorna la lista de entradas de log parseadas correctamente.
    public List<LogEntry> readLogsFromFile(String filePath){
        try {
            return Files.lines(Path.of(filePath))
                    .map(LogParser::parseLine)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        } catch (IOException e){
            System.out.println(e.getMessage());
            return List.of();
        }
    }
}
