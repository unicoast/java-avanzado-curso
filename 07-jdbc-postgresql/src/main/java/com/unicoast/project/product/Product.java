package com.unicoast.project.product;

import com.unicoast.project.category.Category;

/*
 * MODELO DE DOMINIO: PRODUCT (Producto)
 *
 * Representa la entidad central de producto y su relación muchos a uno con 'Category'.
 *
 * Mapeo de Atributos a Columnas SQL:
 * - 'id'       -> Columna 'id' (BIGSERIAL, Clave Primaria)
 * - 'name'     -> Columna 'name' (VARCHAR)
 * - 'price'    -> Columna 'price' (NUMERIC / DOUBLE PRECISION)
 * - 'stock'    -> Columna 'stock' (INTEGER)
 * - 'category' -> Clave Foránea 'category_id' (BIGINT) vinculada a la tabla 'categories'
 *
 * Constructores Sobrecargados:
 * 1. (name, price, stock): Para creaciones simples antes de asociar categoría.
 * 2. (id, name, price, stock): Para consultas directas donde no se incluye el JOIN de categoría.
 * 3. (id, name, price, stock, category): Para entidades completas con su relación mapeada.
 */
public class Product {
    private Long id;
    private String name;
    private double price;
    private int stock;
    private Category category;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(Long id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(Long id, String name, double price, int stock, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
