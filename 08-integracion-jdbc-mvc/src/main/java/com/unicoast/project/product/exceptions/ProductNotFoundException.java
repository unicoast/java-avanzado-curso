package com.unicoast.project.product.exceptions;

/*
 * EXCEPCIÓN DE NEGOCIO: PRODUCTO NO ENCONTRADO
 *
 * Naturaleza y Propósito:
 * Checked Exception que representa la ausencia de una entidad buscada mediante su identificador único.
 * Se lanza durante operaciones de actualización o eliminación cuando el producto solicitado no existe
 * ni en la caché en memoria ni en la tabla relacional 'products'.
 *
 * Permite que las capas superiores (Controlador y Vista) distingan semánticamente entre:
 * - Infracciones de validación (InvalidProductException).
 * - Fallos de infraestructura de base de datos (SQLException).
 * - Ausencia de registros solicitados (ProductNotFoundException).
 */
public class ProductNotFoundException extends Exception {

    /*
     * Construir la excepción con un mensaje informativo que detalla el ID no localizado.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
