package com.unicoast.project.product.interfaces;

import com.unicoast.project.product.exceptions.InvalidProductException;
import com.unicoast.project.product.exceptions.ProductNotFoundException;
import com.unicoast.project.product.model.Product;
import com.unicoast.project.product.model.ProductCategory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/*
 * CONTRATO DEL PATRÓN REPOSITORY: ABSTRACCIÓN DE ACCESO A PRODUCTOS
 *
 * Principio de Inversión de Dependencias (DIP - SOLID):
 * Define el contrato que desacopla la capa de negocio (ProductService) de los detalles de implementación
 * del almacenamiento. El servicio interactúa exclusivamente con esta interfaz, manteniendo la arquitectura
 * modular e independiente de la tecnología subyacente.
 *
 * Soporte Transaccional con Connection:
 * Los métodos que alteran el estado de persistencia (save, delete, update) o que requieren lectura
 * directa de la fuente física (findByIdDB) reciben una instancia activa de 'Connection'.
 * Esto permite que las operaciones participen en transacciones atómicas gestionadas por la capa de servicio.
 */
public interface ProductRepository {

    /*
     * Retornar una copia de todos los productos disponibles.
     * Lanzar InvalidProductException si no existen productos registrados.
     */
    List<Product> findAll() throws InvalidProductException;

    /*
     * Buscar un producto por su clave primaria en la estructura local en memoria.
     * Retornar un Optional con la entidad o Optional.empty() si no existe.
     */
    Optional<Product> findById(Long id);

    /*
     * Persistir un nuevo producto en la base de datos relacional y añadirlo a la colección en memoria.
     * Participar en la transacción asociada al objeto Connection.
     */
    Product save(Connection connection, Product product) throws InvalidProductException, SQLException;

    /*
     * Eliminar el producto por su clave primaria tanto en la base de datos como en la estructura en memoria.
     */
    void delete(Connection connection, Long id) throws SQLException;

    /*
     * Filtrar los productos de acuerdo con la categoría especificada.
     */
    List<Product> findByCategory(ProductCategory category);

    /*
     * Actualizar los datos del producto tanto en memoria como en la tabla relacional.
     * Lanzar ProductNotFoundException si la entidad a modificar no se encuentra.
     */
    void update(Connection connection, Optional<Product> product) throws ProductNotFoundException, SQLException;

    /*
     * Verificar si existe un producto registrado con el identificador proporcionado.
     */
    boolean existsById(Long id);

    /*
     * Realizar una consulta directa a la base de datos para obtener el estado físico actual del registro por ID.
     */
    Optional<Product> findByIdDB(Connection connection, Long id) throws SQLException;
}
