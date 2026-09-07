package com.unicoast.project.category.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * ENTIDAD DE DOMINIO: CATEGORY (MODELO RELACIONAL)
 *
 * Mapeo de la tabla relacional 'categories' de PostgreSQL:
 * - id (BIGSERIAL / PRIMARY KEY): Identificador único autoincremental generado por la base de datos.
 * - name (VARCHAR): Nombre descriptivo de la categoría (ej. ELECTRONICOS, LIBROS).
 *
 * Relación Relacional:
 * Modela el lado "Uno" en una relación Uno a Muchos (1:N) frente a la entidad 'Product'.
 * En la base de datos, la tabla 'products' contiene la clave foránea 'category_id' que apunta a esta entidad.
 *
 * Anotaciones de Lombok:
 * - @Data: Genera automáticamente getters, setters, equals, hashCode y toString.
 * - @AllArgsConstructor: Constructor completo con todos los atributos (usado al mapear desde ResultSet).
 * - @NoArgsConstructor: Constructor sin argumentos requerido por frameworks y utilitarios.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Long id;
    private String name;

    /*
     * Constructor sobrecargado para creación de nuevas categorías en memoria.
     * Permite instanciar la categoría antes de su inserción, momento en el cual aún no posee ID asignado.
     */
    public Category(String name) {
        this.name = name;
    }
}
