package com.unicoast.project.category;

/*
 * MODELO DE DOMINIO: CATEGORY (Categoría)
 *
 * Representa la entidad de categoría asociada a los productos en la base de datos relacional.
 *
 * Características del modelo:
 * 1. Mapeo relacional: Corresponde a la tabla 'categories' con columnas 'id' (BIGSERIAL) y 'name' (VARCHAR).
 * 2. Constructor sobrecargado:
 *    - Constructor con (name): Para inserciones nuevas donde el ID se genera automáticamente en la base de datos.
 *    - Constructor con (id, name): Para entidades ya existentes o recuperadas mediante consultas SELECT.
 * 3. Encapsulamiento con Getters, Setters y método 'toString()' para inspección clara en consola.
 */
public class Category {
    private Long id;
    private String name;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
