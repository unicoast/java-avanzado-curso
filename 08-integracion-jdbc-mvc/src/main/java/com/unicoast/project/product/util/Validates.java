package com.unicoast.project.product.util;

import com.unicoast.project.product.exceptions.InvalidProductException;

/*
 * CLASE DE UTILIDAD: VALIDACIONES GENÉRICAS Y DEFENSIVAS
 *
 * Propósito y Principio DRY (Don't Repeat Yourself):
 * Proporciona métodos estáticos genéricos para la comprobación preliminar de argumentos en la capa de Controlador y Vista.
 * Asegura que los parámetros obligatorios no sean nulos o vacíos antes de transferir la ejecución al servicio,
 * lanzando 'InvalidProductException' con un mensaje personalizado.
 *
 * Tipado Genérico:
 * - <T extends Number>: Permite verificar cualquier tipo numérico del ecosistema Java (Long, Integer, Double).
 * - <T>: Aplica a cualquier objeto de dominio o DTO.
 */
public class Validates {

    /*
     * Validar que el parámetro numérico recibido no sea nulo.
     */
    public static <T extends Number> void validateNumber(T value, String message) throws InvalidProductException {
        if (value == null){
            throw new InvalidProductException(message);
        }
    }

    /*
     * Validar que la instancia de un objeto no sea nula.
     */
    public static <T> void validateObject(T obj, String message) throws InvalidProductException {
        if (obj == null){
            throw new InvalidProductException(message);
        }
    }

    /*
     * Validar que una cadena de texto no sea nula ni esté compuesta únicamente por espacios en blanco.
     */
    public static void validateText(String txt, String message) throws InvalidProductException {
        if (txt == null || txt.trim().isEmpty()){
            throw new InvalidProductException(message);
        }
    }
}
