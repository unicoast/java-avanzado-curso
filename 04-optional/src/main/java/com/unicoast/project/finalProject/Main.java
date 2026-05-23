package com.unicoast.project.finalProject;

import com.unicoast.project.finalProject.controller.ProductController;
import com.unicoast.project.finalProject.interfaces.ProductRepository;
import com.unicoast.project.finalProject.repository.ProductRepositoryServices;
import com.unicoast.project.finalProject.service.ProductService;
import com.unicoast.project.finalProject.view.ProductView;

public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepositoryServices();
        ProductService productService = new ProductService(productRepository);
        ProductController productController = new ProductController(productService);
        ProductView productView = new ProductView(productController);

        productView.showMenu();
    }
}
