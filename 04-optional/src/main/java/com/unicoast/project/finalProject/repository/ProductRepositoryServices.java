package com.unicoast.project.finalProject.repository;

import com.unicoast.project.finalProject.exceptions.InvalidProductException;
import com.unicoast.project.finalProject.exceptions.ProductNotFoundException;
import com.unicoast.project.finalProject.interfaces.ProductRepository;
import com.unicoast.project.finalProject.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryServices implements ProductRepository {

    private final List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findAll() throws InvalidProductException {
        if (products.isEmpty()){
            throw new InvalidProductException("La lista esta vacía");
        }

        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public void delete(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public void update(Optional<Product> product) throws ProductNotFoundException {
        if (product.isPresent()){
            Long idToUpdate = product.get().getId();
            int index = findIndexById(idToUpdate);
            if (index != -1) {
                products.set(index, product.get());
            } else {
                throw new ProductNotFoundException("Producto con id " + idToUpdate + " no encontrado");
            }
        } else {
            throw new ProductNotFoundException("Producto no encontrado");
        }
    }

    @Override
    public boolean existsById(Long id) {
        return products.stream().anyMatch(product -> product.getId().equals(id));
    }

    private int findIndexById(Long id){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }
}
