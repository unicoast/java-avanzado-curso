package com.unicoast.project.product.exceptions;

/*
 * EXCEPCIÓN DE NEGOCIO: PRODUCTO INVÁLIDO
 *
 * Naturaleza y Propósito:
 * Es una excepción comprobada (Checked Exception) que hereda de 'java.lang.Exception'.
 * Se utiliza para modelar infracciones a las reglas de dominio del sistema:
 * 1. Atributos obligatorios nulos o vacíos (ej. nombre no especificado).
 * 2. Valores numéricos incompatibles con el negocio (precio o stock negativo).
 * 3. Intentos de duplicación o inconsistencias de estado.
 *
 * Al ser comprobada, la JVM exige su declaración explícita en la firma ('throws')
 * o su captura controlada ('try-catch'), garantizando un tratamiento defensivo de errores.
 */
public class InvalidProductException extends Exception {

    /*
     * Construir la excepción asociada a un mensaje descriptivo del motivo de invalidación.
     */
    public InvalidProductException(String message) {
        super(message);
    }
}
