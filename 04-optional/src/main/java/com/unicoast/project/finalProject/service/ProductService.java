package com.unicoast.project.finalProject.service;

import com.unicoast.project.finalProject.exceptions.InvalidProductException;
import com.unicoast.project.finalProject.exceptions.ProductNotFoundException;
import com.unicoast.project.finalProject.interfaces.ProductRepository;
import com.unicoast.project.finalProject.model.Product;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() throws InvalidProductException {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public void saveProduct(Product product) throws InvalidProductException {
        ProductValidator.validate(product);
        if (!productRepository.existsById(product.getId())){
            productRepository.save(product);
            System.out.println("Producto guardado: " + product.getName());
        } else {
            throw new InvalidProductException("El producto que desea agregar ya se encuentra registrado");
        }
    }

    public void deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            productRepository.delete(id);
            System.out.println("Producto eliminado: " + optionalProduct.get().getName());
        } else {
            throw new ProductNotFoundException("El producto con ID " + id + " no se encuentra registrado");
        }
    }

    public void updateProduct(Product product) throws ProductNotFoundException, InvalidProductException {
        ProductValidator.validate(product);
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        
        if (optionalProduct.isPresent()){
            productRepository.update(optionalProduct);
            System.out.println("El producto ha sido actualizado");
        } else {
            throw new ProductNotFoundException("El producto con ID " + product.getId() + " no se encuentra registrado");
        }
    }
}
