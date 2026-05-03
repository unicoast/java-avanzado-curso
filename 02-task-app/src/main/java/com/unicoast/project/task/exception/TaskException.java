package com.unicoast.project.task.exception;

// Excepción para errores de lógica del repositorio (tarea no encontrada, lista vacía, etc.)
public class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
}
