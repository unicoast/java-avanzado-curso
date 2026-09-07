package com.unicoast.project.product.controller;

import com.unicoast.project.product.exceptions.InvalidProductException;
import com.unicoast.project.product.exceptions.ProductNotFoundException;
import com.unicoast.project.product.model.Product;
import com.unicoast.project.product.model.ProductCategory;
import com.unicoast.project.product.service.ProductService;
import com.unicoast.project.product.util.Validates;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/*
 * CONTROLADOR: PATRÓN MODELO-VISTA-CONTROLADOR (MVC)
 *
 * Rol Arquitectónico:
 * Actúa como intermediario entre la capa de presentación (ProductView) y la capa de lógica de negocio (ProductService).
 * Su responsabilidad principal es recibir las peticiones de la vista, validar defensivamente que los parámetros
 * obligatorios no sean nulos mediante 'Validates', y delegar la operación al servicio correspondiente.
 *
 * Desacoplamiento y Responsabilidad Única:
 * El controlador no interactúa directamente con la base de datos ni contiene consultas SQL o sentencias transaccionales;
 * toda la persistencia y la orquestación ACID permanecen encapsuladas en 'ProductService' y los DAOs.
 *
 * Tratamiento de Excepciones:
 * Gestión y propagación de excepciones de dominio (InvalidProductException, ProductNotFoundException) así como
 * excepciones de persistencia (SQLException) para permitir que la vista informe adecuadamente de cualquier incidencia.
 */
public class ProductController {
    private final ProductService productService;

    /*
     * Constructor que inyecta la capa de servicio de productos.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*
     * Validar que el producto no sea nulo y delegar su registro transaccional al servicio.
     */
    public void addProduct(Product product) throws InvalidProductException, SQLException {
        Validates.validateObject(product, "El producto no puede ser nulo");
        productService.saveProduct(product);
    }
    
    /*
     * Validar que el ID no sea nulo y delegar la eliminación física y en memoria del producto al servicio.
     */
    public void removeProduct(Long id) throws ProductNotFoundException, InvalidProductException, SQLException {
        Validates.validateNumber(id, "El ID del producto no puede ser nulo");
        productService.deleteProduct(id);
    }

    /*
     * Obtener la totalidad de productos a través del servicio.
     */
    public List<Product> getAllProducts() throws InvalidProductException {
        return productService.getAllProducts();
    }

    /*
     * Obtener del servicio la lista de productos filtrados por una categoría determinada.
     */
    public List<Product> getAllProductsByCategory(ProductCategory category) {
        return productService.getAllProductsByCategory(category);
    }

    /*
     * Validar el identificador y consultar el producto en la caché local en memoria.
     */
    public Optional<Product> getProductById(Long id) throws InvalidProductException {
        Validates.validateNumber(id, "El ID del producto no puede ser nulo");
        return productService.getProductById(id);
    }

    /*
     * Validar el identificador y solicitar una lectura directa a la base de datos PostgreSQL.
     */
    public Optional<Product> getProductByIdDB(Long id) throws InvalidProductException, SQLException {
        Validates.validateNumber(id, "El ID del producto no puede ser nulo");
        return productService.getProductByIdDB(id);
    }

    /*
     * Validar que la entidad no sea nula y solicitar la actualización transaccional al servicio.
     */
    public void updateProduct(Product product) throws ProductNotFoundException, InvalidProductException, SQLException {
        Validates.validateObject(product, "El producto no puede ser nulo");
        productService.updateProduct(product);
    }
}
