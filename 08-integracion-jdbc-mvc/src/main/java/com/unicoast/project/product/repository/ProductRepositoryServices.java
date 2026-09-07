package com.unicoast.project.product.repository;

import com.unicoast.project.category.persistence.CategoryDao;
import com.unicoast.project.db.ConnectionPool;
import com.unicoast.project.product.exceptions.InvalidProductException;
import com.unicoast.project.product.exceptions.ProductNotFoundException;
import com.unicoast.project.product.interfaces.ProductRepository;
import com.unicoast.project.product.model.Product;
import com.unicoast.project.product.model.ProductCategory;
import com.unicoast.project.product.persistence.ProductDao;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * IMPLEMENTACIÓN DEL REPOSITORIO: CACHÉ EN MEMORIA Y PERSISTENCIA RELACIONAL CON JDBC
 *
 * Arquitectura de Repositorio Híbrido:
 * Este componente combina una estructura de datos en memoria con persistencia física relacional:
 * 1. Mantiene una colección 'List<Product>' como caché de acceso de baja latencia para lecturas.
 * 2. Al instanciarse, precarga la caché consultando la base de datos PostgreSQL ('dao.findAll()').
 * 3. En cada operación de mutación (save, delete, update), sincroniza la base de datos relacional
 *    a través de 'ProductDao' bajo la conexión transaccional y actualiza la lista local en memoria.
 * 4. Aprovecha la programación funcional con Streams ('filter', 'findFirst', 'anyMatch') y 'Optional'.
 */
@Getter
public class ProductRepositoryServices implements ProductRepository {

    private final List<Product> products;
    private final ProductDao dao;
    private final CategoryDao categoryDao;

    /*
     * Constructor del repositorio.
     * Inicializar los DAOs y precargar en memoria los productos existentes en la base de datos PostgreSQL.
     */
    public ProductRepositoryServices(CategoryDao categoryDao) throws SQLException, InvalidProductException {
        dao = new ProductDao(categoryDao);
        this.categoryDao = categoryDao;
        try (Connection connection = ConnectionPool.getConnection()) {
            products = dao.findAll(connection);
        } catch (SQLException e) {
            throw new InvalidProductException("Error al inicializar la lista: " + e.getMessage());
        }
    }

    /*
     * Retornar una copia defensiva de la lista en memoria para evitar modificaciones externas no controladas.
     * Lanzar InvalidProductException si la lista no contiene elementos.
     */
    @Override
    public List<Product> findAll() throws InvalidProductException {
        if (products.isEmpty()) {
            throw new InvalidProductException("La lista está vacía");
        }

        return new ArrayList<>(products);
    }

    /*
     * Búsqueda funcional en memoria utilizando Java Streams y Optional.
     */
    @Override
    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    /*
     * Consulta directa a la base de datos PostgreSQL, omitiendo la caché local para obtener el estado persistido.
     */
    @Override
    public Optional<Product> findByIdDB(Connection connection, Long id) throws SQLException {
        return dao.findById(connection, id);
    }

    /*
     * Persistencia transaccional: Insertar el registro en PostgreSQL mediante 'ProductDao'
     * y añadirlo a la colección en memoria.
     */
    @Override
    public Product save(Connection connection, Product product) throws SQLException {
        Product newProduct = dao.save(connection, product);
        products.add(newProduct);
        return newProduct;
    }

    /*
     * Eliminación sincronizada: Remover el producto de la colección en memoria
     * y ejecutar la sentencia DELETE física en la base de datos.
     */
    @Override
    public void delete(Connection connection, Long id) throws SQLException {
        products.removeIf(product -> product.getId().equals(id));
        dao.delete(connection, id);
    }

    /*
     * Filtrado funcional de productos por categoría utilizando la API de Streams.
     */
    @Override
    public List<Product> findByCategory(ProductCategory category) {
        return products.stream()
                .filter(product -> product.getCategory().equals(category))
                .toList();
    }

    /*
     * Modificación sincronizada: Actualizar el registro en la base de datos relacional
     * y reemplazar el elemento en la colección en memoria.
     */
    @Override
    public void update(Connection connection, Optional<Product> product) throws ProductNotFoundException, SQLException {
        if (product.isPresent()) {
            Long idToUpdate = product.get().getId();
            int index = findIndexById(idToUpdate);
            if (index != -1) {
                products.set(index, product.get());
                dao.update(connection, product.get());
            } else {
                throw new ProductNotFoundException("Producto con id " + idToUpdate + " no encontrado");
            }
        } else {
            throw new ProductNotFoundException("Producto no encontrado");
        }
    }

    /*
     * Comprobar mediante Streams si algún producto en memoria coincide con el ID proporcionado.
     */
    @Override
    public boolean existsById(Long id) {
        return products.stream().anyMatch(product -> product.getId().equals(id));
    }

    /*
     * Buscar la posición ordinal de un producto en la lista en memoria a partir de su ID.
     * Retornar el índice de la posición o -1 si no se encuentra.
     */
    public int findIndexById(Long id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
