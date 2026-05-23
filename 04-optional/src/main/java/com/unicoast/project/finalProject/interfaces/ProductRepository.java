package com.unicoast.project.finalProject.interfaces;

import com.unicoast.project.finalProject.exceptions.InvalidProductException;
import com.unicoast.project.finalProject.exceptions.ProductNotFoundException;
import com.unicoast.project.finalProject.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll() throws InvalidProductException;
    Optional<Product> findById(Long id);
    void save(Product product);
    void delete(Long id);
    void update(Optional<Product> product) throws ProductNotFoundException;
    boolean existsById(Long id);
}
