package com.unicoast.project.product.model;

import com.unicoast.project.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * ENTIDAD DE DOMINIO: PRODUCT (MODELO RELACIONAL Y DE NEGOCIO)
 *
 * Mapeo con la tabla relacional 'products' de PostgreSQL:
 * - id (BIGSERIAL / PRIMARY KEY): Clave primaria autoincremental generada por secuencias de base de datos.
 * - name (VARCHAR): Nombre representativo del producto.
 * - price (DOUBLE PRECISION): Precio unitario de comercialización.
 * - stock (INTEGER): Cantidad física disponible en inventario.
 * - category (Category): Relación Many-to-One (Muchos a Uno). En la base de datos se persiste como
 *   la columna de clave foránea 'category_id REFERENCES categories(id)'.
 *
 * Anotaciones de Lombok:
 * - @Data: Genera automáticamente métodos de acceso (getters/setters), métodos de identidad (equals/hashCode)
 *   y representación legible en texto (toString).
 * - @AllArgsConstructor: Constructor completo con todos los atributos, ideal para hidratar objetos desde ResultSet.
 * - @NoArgsConstructor: Constructor por defecto sin argumentos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private double price;
    private int stock;
    private Category category;

    /*
     * Constructor sobrecargado para creación de nuevos productos.
     * Omite el atributo 'id' para permitir que el motor de base de datos asigne la clave primaria autoincremental.
     */
    public Product(String name, double price, int stock, Category category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }
}
