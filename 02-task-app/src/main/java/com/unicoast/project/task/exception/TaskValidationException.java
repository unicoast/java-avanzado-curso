package com.unicoast.project.task.exception;

// Excepción para errores de validación de datos (campos nulos, vacíos, etc.)
// Separada de TaskException para distinguir errores de validación vs errores de lógica
public class TaskValidationException extends Exception {
    public TaskValidationException(String message) {
        super(message);
    }
}
