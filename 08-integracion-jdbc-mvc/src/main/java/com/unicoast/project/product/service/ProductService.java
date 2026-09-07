package com.unicoast.project.product.service;

import com.unicoast.project.category.model.Category;
import com.unicoast.project.db.ConnectionPool;
import com.unicoast.project.product.exceptions.InvalidProductException;
import com.unicoast.project.product.exceptions.ProductNotFoundException;
import com.unicoast.project.product.interfaces.ProductRepository;
import com.unicoast.project.product.model.Product;
import com.unicoast.project.product.model.ProductCategory;
import com.unicoast.project.product.repository.ProductRepositoryServices;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/*
 * CAPA DE SERVICIO: GESTIÓN DE LÓGICA DE NEGOCIO Y CONTROL TRANSACCIONAL ACID CON JDBC
 *
 * ¿Qué es una Transacción ACID en JDBC?
 * Es un bloque indivisible de operaciones sobre la base de datos relacional que garantiza cuatro propiedades fundamentales:
 * - Atomicidad: Todas las operaciones se confirman con éxito (commit) o ninguna surte efecto (rollback).
 * - Consistencia: El estado de la base de datos preserva todas las restricciones de integridad y claves foráneas.
 * - Aislamiento (Isolation): Las operaciones concurrentes no interfieren con los datos intermedios de la transacción.
 * - Durabilidad: Una vez realizado el commit, las modificaciones persisten permanentemente frente a fallos del sistema.
 *
 * Flujo Transaccional Manual:
 * 1. Obtención de conexión desde el pool: 'Connection connection = ConnectionPool.getConnection()'.
 * 2. Inicio de transacción manual: 'connection.setAutoCommit(false)' desactiva el guardado automático.
 * 3. Ejecución de operaciones dependientes: Si la categoría no existe en la base de datos, se crea y persiste
 *    obteniendo su ID antes de insertar o actualizar el producto con su clave foránea.
 * 4. Confirmación atómica: 'connection.commit()' aplica todos los cambios de forma simultánea.
 * 5. Reversión ante errores: En el bloque 'catch', 'connection.rollback()' desace cualquier cambio parcial.
 * 6. Limpieza en 'finally': Se restaura 'setAutoCommit(true)' y se invoca 'connection.close()' para reciclar la conexión en HikariCP.
 */
public class ProductService {
    private final ProductRepository productRepository;

    /*
     * Constructor que inyecta la abstracción del repositorio de productos.
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*
     * Obtener la totalidad de productos registrados a través de la abstracción del repositorio.
     */
    public List<Product> getAllProducts() throws InvalidProductException {
        return productRepository.findAll();
    }

    /*
     * Consultar productos filtrados por una categoría específica.
     */
    public List<Product> getAllProductsByCategory(ProductCategory category) {
        return productRepository.findByCategory(category);
    }

    /*
     * Buscar un producto por ID consultando la estructura local en memoria.
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /*
     * Consultar un producto directamente en la base de datos relacional PostgreSQL.
     * Utiliza try-with-resources para garantizar el retorno automático de la conexión al pool.
     */
    public Optional<Product> getProductByIdDB(Long id) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            return productRepository.findByIdDB(connection, id);
        }
    }

    /*
     * Operación transaccional: Inserción atómica de producto y su categoría correspondiente.
     * Flujo:
     * 1. Validar invariantes del producto mediante ProductValidator.
     * 2. Iniciar la transacción manual desactivando autoCommit.
     * 3. Verificar si la categoría ya existe por nombre; si no existe, persistirla y asignar el ID generado.
     * 4. Persistir el producto vinculando la categoría.
     * 5. Confirmar la transacción con commit, o ejecutar rollback ante excepciones.
     */
    public void saveProduct(Product product) throws InvalidProductException, SQLException {
        ProductValidator.validate(product);
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);

            Optional<Category> optionalCategory = ((ProductRepositoryServices) productRepository)
                    .getCategoryDao().findCategoryByName(connection, product.getCategory().getName());

            if (optionalCategory.isPresent()) {
                if (product.getId() == null || !productRepository.existsById(product.getId())) {
                    product.setCategory(optionalCategory.get());
                } else {
                    throw new InvalidProductException("El producto que desea agregar ya se encuentra registrado");
                }
            } else {
                Optional<Category> optionalNewCategory = ((ProductRepositoryServices) productRepository)
                        .getCategoryDao().save(connection, product.getCategory());
                optionalNewCategory.ifPresent(product::setCategory);
            }

            productRepository.save(connection, product);
            connection.commit();
            System.out.println("Producto guardado: " + product.getName());
        } catch (SQLException | InvalidProductException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión");
                }
            }
        }
    }

    /*
     * Operación transaccional: Eliminación de un producto por ID.
     * Verificar la existencia previa en el repositorio antes de ejecutar el borrado físico y confirmar la transacción.
     */
    public void deleteProduct(Long id) throws ProductNotFoundException, SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);

            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                productRepository.delete(connection, id);
                connection.commit();
                System.out.println("Producto eliminado: " + optionalProduct.get().getName());
            } else {
                throw new ProductNotFoundException("El producto con ID " + id + " no se encuentra registrado");
            }
        } catch (SQLException | ProductNotFoundException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión");
                }
            }
        }
    }

    /*
     * Operación transaccional: Modificación integral de un producto existente.
     * Flujo:
     * 1. Validar el producto mediante ProductValidator.
     * 2. Comprobar la existencia en la base de datos PostgreSQL.
     * 3. Verificar o insertar la categoría asociada según corresponda.
     * 4. Actualizar los valores en la base de datos y en la estructura en memoria.
     * 5. Confirmar la transacción con commit, o ejecutar rollback ante errores.
     */
    public void updateProduct(Product product) throws ProductNotFoundException, SQLException, InvalidProductException {
        ProductValidator.validate(product);
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);

            Optional<Product> optionalProduct = ((ProductRepositoryServices) productRepository)
                    .getDao().findById(connection, product.getId());

            if (optionalProduct.isPresent()) {
                Optional<Category> category = ((ProductRepositoryServices) productRepository).getCategoryDao()
                        .findCategoryByName(connection, product.getCategory().getName());

                if (category.isPresent()) {
                    product.setCategory(category.get());
                } else {
                    Optional<Category> newCategory = ((ProductRepositoryServices) productRepository)
                            .getCategoryDao().save(connection, product.getCategory());
                    newCategory.ifPresent(product::setCategory);
                }

                productRepository.update(connection, Optional.of(product));
                connection.commit();
                System.out.println("El producto ha sido actualizado");
            } else {
                throw new ProductNotFoundException("El producto con ID " + product.getId() + " no se encuentra registrado");
            }
        } catch (SQLException | ProductNotFoundException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión");
                }
            }
        }
    }
}
