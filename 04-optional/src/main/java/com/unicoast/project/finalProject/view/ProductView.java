package com.unicoast.project.finalProject.view;

import com.unicoast.project.finalProject.controller.ProductController;
import com.unicoast.project.finalProject.exceptions.InvalidProductException;
import com.unicoast.project.finalProject.exceptions.ProductNotFoundException;
import com.unicoast.project.finalProject.model.Product;
import com.unicoast.project.finalProject.model.ProductCategory;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductView {
    private final ProductController productController;
    private final Scanner scanner;

    public ProductView(ProductController productController) {
        this.productController = productController;
        scanner = new Scanner(System.in);
    }

    public void showMenu(){
        while (true){
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Mostrar Productos");
            System.out.println("3. Buscar Producto por ID");
            System.out.println("4. Eliminar Producto por ID");
            System.out.println("5. Modificar Producto por ID");
            System.out.println("6. Salir");
            System.out.print("Opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1 ->  addProduct();
                case 2 ->  showAllProducts();
                case 3 ->  findProductById();
                case 4 ->  deleteProduct();
                case 5 ->  updateProduct();
                case 6 -> {
                    scanner.close();
                    return;
                }
            }
        }
    }

    public void addProduct(){
        try {
            long id = readValidLong("Ingrese el ID del producto: ", 0);
            String name = readNonEmptyString("Ingrese el nombre del producto:\n");
            double price = readValidDouble("Ingrese el precio del producto: ", 0);
            int stock = readValidInt("Ingrese el stock del producto: ", 0);
            ProductCategory category = readValidCategory("Ingrese la categoría del producto:\nELECTRONICOS, COMIDAS, LIBROS, OTROS\n");

            Product product = new Product(id, name, price, stock, category);
            productController.addProduct(product);
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showAllProducts(){
        try {
            System.out.println("\nLista de productos:");
            List<Product> products = productController.getAllProducts();
            products.forEach(this::showProduct);
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findProductById(){
        try {
            long id = readValidLong("Ingrese el ID del producto a buscar: ", 0);
            Optional<Product> optionalProduct = productController.getProductById(id);

            if (optionalProduct.isPresent()){
                Product product1 = optionalProduct.get();
                showProduct(product1);
            } else {
                System.out.println("El producto con ID " + id + " no se encuentra registrado");
            }
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProduct(){
        try {
            long id = readValidLong("Ingrese el ID del producto a buscar: ", 0);
            Optional<Product> optionalProduct = productController.getProductById(id);

            if (optionalProduct.isPresent()){
                System.out.println("\nProducto a MODIFICAR:");
                Product product1 = optionalProduct.get();
                showProduct(product1);

                System.out.println("\nSeleccione el campo que desea modificar");
                System.out.println("1. Nombre");
                System.out.println("2. Precio");
                System.out.println("3. Stock");
                System.out.println("4. Categoría");
                System.out.println("5. TODOS");
                System.out.println("6. Salir");
                System.out.print("Opción: ");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option){
                    case 1 -> optionalProduct.get().setName(readNonEmptyString("Ingrese el nuevo nombre del producto: "));
                    case 2 -> optionalProduct.get().setPrice(readValidDouble("Ingrese el nuevo precio del producto: ", 0));
                    case 3 -> optionalProduct.get().setStock(readValidInt("Ingrese el nuevo stock del producto: ", 0));
                    case 4 -> {
                        ProductCategory category = readValidCategory("Ingrese la nueva categoría del producto:\nELECTRONICOS, COMIDAS, LIBROS, OTROS\n");
                        optionalProduct.get().setCategory(category);
                    }
                    case 5 -> {
                        optionalProduct.get().setName(readNonEmptyString("Ingrese el nuevo nombre del producto: "));
                        optionalProduct.get().setPrice(readValidDouble("Ingrese el nuevo precio del producto: ", 0));
                        optionalProduct.get().setStock(readValidInt("Ingrese el nuevo stock del producto: ", 0));
                        ProductCategory category = readValidCategory("Ingrese la nueva categoría del producto:\nELECTRONICOS, COMIDAS, LIBROS, OTROS\n");
                        optionalProduct.get().setCategory(category);
                    }
                    case 6 -> {
                        return;
                    }
                }
                productController.updateProduct(product1);
            } else {
                System.out.println("El producto con ID " + id + " no se encuentra registrado");
            }
        } catch (ProductNotFoundException | InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteProduct(){
        try {
            long id = readValidLong("Ingrese el ID del producto a buscar: ", 0);
            productController.removeProduct(id);
        } catch (ProductNotFoundException | InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showProduct(Product product){
        System.out.println("\nProducto:");
        System.out.println("Id: " + product.getId());
        System.out.println("Nombre: " + product.getName());
        System.out.println("Precio: " + product.getPrice());
        System.out.println("Stock: " + product.getStock());
        System.out.println("Categoría: " + product.getCategory());
        System.out.println("------------------------------");
    }

    private String readNonEmptyString(String message){
        String input;
        do {
            System.out.print(message);
            input = scanner.nextLine();
            if (input.length() < 3){
                System.out.println("El valor no puede ser vacío y debe tener al menos 3 caracteres. Intente nuevamente.");
            }
        } while (input.length() < 3);

        return input;
    }

    private long readValidLong(String message, long min){
        long value;
        do {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                value = Long.parseLong(input);
                if (value < min){
                    System.out.println("El valor debe ser mayor a " + min + ". Intente nuevamente.");
                    continue;
                }

                return value;
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private int readValidInt(String message, int min){
        int value;
        do {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                value = Integer.parseInt(input);
                if (value < min){
                    System.out.println("El valor debe ser mayor a " + min + ". Intente nuevamente.");
                    continue;
                }

                return value;
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private double readValidDouble(String message, double min){
        double value;
        do {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                value = Double.parseDouble(input);
                if (value < min){
                    System.out.println("El valor debe ser mayor a " + min + ". Intente nuevamente.");
                    continue;
                }

                return value;
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private ProductCategory readValidCategory(String message){
        do {
            String input = readNonEmptyString(message);
            try {
                return ProductCategory.valueOf(input.trim().toUpperCase());
            } catch (IllegalArgumentException e){
                System.out.println("Categoría no válida. Intente nuevamente.");
            }
        } while (true);
    }
}
