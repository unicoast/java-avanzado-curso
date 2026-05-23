package com.unicoast.project.finalProject.service;

import com.unicoast.project.finalProject.exceptions.InvalidProductException;
import com.unicoast.project.finalProject.model.Product;

public class ProductValidator {
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
