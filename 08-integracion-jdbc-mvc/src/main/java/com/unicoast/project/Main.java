package com.unicoast.project;

import com.unicoast.project.category.persistence.CategoryDao;
import com.unicoast.project.db.ConnectionPool;
import com.unicoast.project.product.controller.ProductController;
import com.unicoast.project.product.exceptions.InvalidProductException;
import com.unicoast.project.product.interfaces.ProductRepository;
import com.unicoast.project.product.repository.ProductRepositoryServices;
import com.unicoast.project.product.service.ProductService;
import com.unicoast.project.product.view.ProductView;

import java.sql.SQLException;

/*
 * ARQUITECTURA DEL SISTEMA: PATRÓN MVC CON PERSISTENCIA RELACIONAL (JDBC + POSTGRESQL)
 *
 * Propósito y Diseño General:
 * Sistema empresarial para la gestión integral de productos y categorías. Implementa una arquitectura
 * por capas basada en el patrón Modelo-Vista-Controlador (MVC), persistencia relacional física en PostgreSQL,
 * pool de conexiones HikariCP, control transaccional ACID y programación funcional (Optional, Streams).
 *
 * Cadena de Inyección de Dependencias (Inversión de Control manual):
 * 1. CategoryDao: Acceso a datos de bajo nivel para la entidad Category.
 * 2. ProductRepositoryServices: Implementación de repositorio que combina caché en memoria y persistencia mediante DAOs.
 * 3. ProductService: Capa de negocio y orquestador de transacciones ACID (commit / rollback).
 * 4. ProductController: Intermediario que valida parámetros y delega operaciones al servicio.
 * 5. ProductView: Interfaz de consola que captura la interacción y presenta los resultados.
 *
 * Gestión del ciclo de vida de recursos:
 * El bloque finally garantiza el cierre ordenado del pool de conexiones (ConnectionPool.closePool()),
 * liberando los recursos de red y sockets asociados a PostgreSQL al concluir la ejecución del programa.
 */
public class Main {

    /*
     * Punto de entrada de la aplicación.
     * Instanciar y conectar los componentes respetando la separación de responsabilidades en capas.
     */
    public static void main(String[] args) {
        try {
            CategoryDao categoryDao = new CategoryDao();
            ProductRepository repositoryServices = new ProductRepositoryServices(categoryDao);
            ProductService productService = new ProductService(repositoryServices);
            ProductController controller = new ProductController(productService);
            ProductView view = new ProductView(controller);

            view.showMenu();

        } catch (SQLException | InvalidProductException e) {
            System.out.println("Error en la ejecución de la aplicación: " + e.getMessage());
        } finally {
            /*
             * Cierre explícito del Connection Pool de HikariCP para evitar fugas de recursos al finalizar.
             */
            ConnectionPool.closePool();
        }
    }
}
