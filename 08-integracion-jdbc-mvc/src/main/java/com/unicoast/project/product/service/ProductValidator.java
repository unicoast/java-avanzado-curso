package com.unicoast.project.product.service;

import com.unicoast.project.product.exceptions.InvalidProductException;
import com.unicoast.project.product.model.Product;

/*
 * VALIDADOR DE NEGOCIO: PROGRAMACIÓN DEFENSIVA PARA PRODUCTOS
 *
 * Principio y Responsabilidad:
 * Centraliza la validación de invariantes del modelo de dominio antes de procesar operaciones de persistencia.
 * Al validar en la capa de negocio previa a la transacción, se evita el consumo innecesario de conexiones del pool
 * y se previene la ejecución de sentencias SQL condenadas a fallar por restricciones de integridad referencial o de tabla.
 *
 * Reglas de Validación:
 * 1. Nombre: No puede ser nulo ni una cadena vacía.
 * 2. Precio: Debe ser igual o superior a cero (no se admiten valores negativos).
 * 3. Stock: Debe ser igual o superior a cero (el inventario no puede ser negativo).
 */
public class ProductValidator {

    /*
     * Evaluar el estado del producto y lanzar InvalidProductException ante el incumplimiento de cualquier regla.
     */
    public static void validate(Product product) throws InvalidProductException {
        if (product.getName() == null || product.getName().isEmpty()){
            throw new InvalidProductException("El nombre del producto no puede ser nulo o vacío");
        }
        if (product.getPrice() < 0){
            throw new InvalidProductException("El precio del producto no puede ser negativo");
        }
        if (product.getStock() < 0){
            throw new InvalidProductException("El stock del producto no puede ser negativo");
        }
    }
}
