package com.unicoast.project.product.view;

import com.unicoast.project.category.model.Category;
import com.unicoast.project.product.controller.ProductController;
import com.unicoast.project.product.exceptions.InvalidProductException;
import com.unicoast.project.product.exceptions.ProductNotFoundException;
import com.unicoast.project.product.model.Product;
import com.unicoast.project.product.model.ProductCategory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/*
 * CAPA DE PRESENTACIÓN: VISTA POR CONSOLA (PATRÓN MVC)
 *
 * Responsabilidad en la Arquitectura:
 * Punto de interacción por consola del sistema. Gestión de la presentación visual,
 * menú interactivo, lectura de datos mediante 'Scanner' y despliegue de mensajes informativos o de error.
 *
 * Desacoplamiento y Delegación:
 * No ejecuta lógica de negocio ni sentencias SQL. Toda la operativa se delega a 'ProductController'.
 *
 * Tratamiento Defensivo y Resiliencia de Entrada:
 * - Implementa métodos auxiliares para validar entradas del teclado ('readValidLong', 'readValidDouble', etc.),
 *   previniendo excepciones 'InputMismatchException' o bloqueos indeseados por saltos de línea pendientes.
 * - Captura de forma diferenciada excepciones de dominio ('InvalidProductException', 'ProductNotFoundException')
 *   y excepciones de base de datos ('SQLException'), presentando mensajes claros sin interrumpir el ciclo de vida del menú.
 */
public class ProductView {
    private final ProductController productController;
    private final Scanner scanner;

    /*
     * Constructor que inyecta el controlador e inicializa el escáner de consola.
     */
    public ProductView(ProductController productController) {
        this.productController = productController;
        scanner = new Scanner(System.in);
    }

    /*
     * Ejecutar el bucle principal de interacción del menú hasta seleccionar la opción de salida (7).
     */
    public void showMenu() {
        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Mostrar Productos");
            System.out.println("3. Buscar Producto por ID");
            System.out.println("4. Eliminar Producto por ID");
            System.out.println("5. Modificar Producto por ID");
            System.out.println("6. Buscar Productos por Categoría");
            System.out.println("7. Salir");
            System.out.print("Opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> addProduct();
                case 2 -> showAllProducts();
                case 3 -> findProductById();
                case 4 -> deleteProduct();
                case 5 -> updateProduct();
                case 6 -> showAllByCategory();
                case 7 -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    /*
     * Capturar los atributos por consola, instanciar el producto con su categoría
     * y delegar al controlador su inserción persistida.
     */
    public void addProduct() {
        try {
            String name = readNonEmptyString("Ingrese el nombre del producto:\n");
            double price = readValidDouble("Ingrese el precio del producto: ", 0);
            int stock = readValidInt("Ingrese el stock del producto: ", 0);
            String categoryName = readNonEmptyString("Ingrese la categoría del producto:\nELECTRONICOS, COMIDAS, LIBROS, OTROS\n");
            Category category = new Category(categoryName.trim().toUpperCase());

            Product product = new Product(name, price, stock, category);
            productController.addProduct(product);
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }

    /*
     * Obtener y mostrar la lista completa de productos por consola.
     */
    public void showAllProducts() {
        try {
            System.out.println("\nLista de productos:");
            List<Product> products = productController.getAllProducts();
            products.forEach(this::showProduct);
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * Buscar un producto por identificador en la colección en memoria.
     */
    public void findProductById() {
        try {
            long id = readValidLong("Ingrese el ID del producto a buscar: ", 0);
            Optional<Product> optionalProduct = productController.getProductById(id);

            if (optionalProduct.isPresent()) {
                Product product1 = optionalProduct.get();
                showProduct(product1);
            } else {
                System.out.println("El producto con ID " + id + " no se encuentra registrado");
            }
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * Modificar atributos individuales o totales de un producto existente.
     * Consultar el estado persistido mediante 'getProductByIdDB' y delegar los cambios validados al controlador.
     */
    public void updateProduct() {
        try {
            long id = readValidLong("Ingrese el ID del producto a buscar: ", 0);
            Optional<Product> optionalProduct = productController.getProductByIdDB(id);

            if (optionalProduct.isPresent()) {
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

                switch (option) {
                    case 1 -> product1.setName(readNonEmptyString("Ingrese el nuevo nombre del producto: "));
                    case 2 -> product1.setPrice(readValidDouble("Ingrese el nuevo precio del producto: ", 0));
                    case 3 -> product1.setStock(readValidInt("Ingrese el nuevo stock del producto: ", 0));
                    case 4 -> {
                        String categoryName = readNonEmptyString("Ingrese la nueva categoría del producto:\nELECTRONICOS, COMIDAS, LIBROS, OTROS\n");
                        product1.setCategory(new Category(categoryName.trim().toUpperCase()));
                    }
                    case 5 -> {
                        product1.setName(readNonEmptyString("Ingrese el nuevo nombre del producto: "));
                        product1.setPrice(readValidDouble("Ingrese el nuevo precio del producto: ", 0));
                        product1.setStock(readValidInt("Ingrese el nuevo stock del producto: ", 0));
                        String categoryName = readNonEmptyString("Ingrese la nueva categoría del producto:\nELECTRONICOS, COMIDAS, LIBROS, OTROS\n");
                        product1.setCategory(new Category(categoryName.trim().toUpperCase()));
                    }
                    case 6 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Opción inválida.");
                        return;
                    }
                }
                productController.updateProduct(product1);
            } else {
                System.out.println("El producto con ID " + id + " no se encuentra registrado");
            }
        } catch (ProductNotFoundException | InvalidProductException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }

    /*
     * Filtrar y mostrar los productos pertenecientes a una categoría específica.
     */
    public void showAllByCategory() {
        System.out.println("\n--- Buscar Productos por Categoría ---");
        try {
            ProductCategory category = readValidCategory("Ingrese la categoría del producto:\nELECTRONICOS, COMIDAS, LIBROS, OTROS\n");
            List<Product> products = productController.getAllProductsByCategory(category);
            if (products.isEmpty()) {
                System.out.println("No hay productos registrados para esa categoría.");
            } else {
                products.forEach(this::showProduct);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /*
     * Eliminar un producto por identificador tanto a nivel físico como en memoria.
     */
    private void deleteProduct() {
        try {
            long id = readValidLong("Ingrese el ID del producto a buscar: ", 0);
            productController.removeProduct(id);
        } catch (ProductNotFoundException | InvalidProductException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }

    /*
     * Imprimir los detalles estructurados de un producto por consola.
     */
    private void showProduct(Product product) {
        System.out.println("\nProducto:");
        System.out.println("Id: " + product.getId());
        System.out.println("Nombre: " + product.getName());
        System.out.println("Precio: " + product.getPrice());
        System.out.println("Stock: " + product.getStock());
        System.out.println("Categoría: " + product.getCategory());
        System.out.println("------------------------------");
    }

    /*
     * Lectura segura de texto: garantiza que no sea vacío y contenga al menos 3 caracteres.
     */
    private String readNonEmptyString(String message) {
        String input;
        do {
            System.out.print(message);
            input = scanner.nextLine();
            if (input.length() < 3) {
                System.out.println("El valor no puede ser vacío y debe tener al menos 3 caracteres. Intente nuevamente.");
            }
        } while (input.length() < 3);

        return input;
    }

    /*
     * Lectura segura de un valor numérico tipo Long con límite inferior.
     */
    private long readValidLong(String message, long min) {
        long value;
        do {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                value = Long.parseLong(input);
                if (value < min) {
                    System.out.println("El valor debe ser mayor a " + min + ". Intente nuevamente.");
                    continue;
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        } while (true);
    }

    /*
     * Lectura segura de un valor numérico entero con límite inferior.
     */
    private int readValidInt(String message, int min) {
        int value;
        do {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                value = Integer.parseInt(input);
                if (value < min) {
                    System.out.println("El valor debe ser mayor a " + min + ". Intente nuevamente.");
                    continue;
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        } while (true);
    }

    /*
     * Lectura segura de un valor decimal (double) con límite inferior.
     */
    private double readValidDouble(String message, double min) {
        double value;
        do {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                value = Double.parseDouble(input);
                if (value < min) {
                    System.out.println("El valor debe ser mayor a " + min + ". Intente nuevamente.");
                    continue;
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        } while (true);
    }

    /*
     * Lectura segura y conversión hacia una constante válida del enum ProductCategory.
     */
    private ProductCategory readValidCategory(String message) {
        do {
            String input = readNonEmptyString(message);
            try {
                return ProductCategory.valueOf(input.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Categoría no válida. Intente nuevamente.");
            }
        } while (true);
    }
}
