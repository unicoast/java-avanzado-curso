package com.unicoast.project.finalProject.controller;

import com.unicoast.project.finalProject.exceptions.InvalidProductException;
import com.unicoast.project.finalProject.exceptions.ProductNotFoundException;
import com.unicoast.project.finalProject.model.Product;
import com.unicoast.project.finalProject.service.ProductService;
import com.unicoast.project.finalProject.util.Validates;

import java.util.List;
import java.util.Optional;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(Product product) throws InvalidProductException {
        Validates.validateObject(product, "El producto no puede ser nulo");
        productService.saveProduct(product);
    }

    public void removeProduct(Long id) throws ProductNotFoundException, InvalidProductException {
        Validates.validateNumber(id, "El ID del producto no puede ser nulo");
        productService.deleteProduct(id);
    }

    public List<Product> getAllProducts() throws InvalidProductException {
        return productService.getAllProducts();
    }

    public Optional<Product> getProductById(Long id) throws InvalidProductException {
        Validates.validateNumber(id, "El ID del producto no puede ser nulo");
        return productService.getProductById(id);
    }

    public void updateProduct(Product product) throws ProductNotFoundException, InvalidProductException {
        Validates.validateObject(product, "El producto no puede ser nulo");
        productService.updateProduct(product);
    }
}
