package com.unicoast.project.record;

import java.util.Objects;

/*
 * CLASE INMUTABLE TRADICIONAL (Antes de los Records)
 * Para garantizar inmutabilidad se requiere: clase 'final', campos 'private final',
 * solo getters (sin setters), y sobrescribir equals(), hashCode() y toString().
 * Todo este código repetitivo (boilerplate) es lo que los Records (Java 16+) eliminan automáticamente.
 */
public final class Product {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // equals() y hashCode(): Comparan por VALOR (datos) en vez de por REFERENCIA (memoria).
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
