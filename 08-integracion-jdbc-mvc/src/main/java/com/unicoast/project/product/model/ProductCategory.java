package com.unicoast.project.product.model;

/*
 * ENUMERACIÓN DE CATEGORÍAS PREDEFINIDAS: PRODUCT CATEGORY
 *
 * Propósito y Rol en el Sistema:
 * Define el catálogo estandarizado de categorías comerciales admitidas en la aplicación.
 * Aunque la persistencia física en PostgreSQL utiliza la entidad relacional 'Category' vinculada
 * mediante la clave foránea 'category_id', esta enumeración cumple funciones clave de soporte:
 * 1. Validación estricta y sugerencias de entrada para la interfaz de consola (ProductView).
 * 2. Operaciones de tipado seguro y filtrado funcional en memoria (ProductRepository.findByCategory).
 */
public enum ProductCategory {
    ELECTRONICOS,
    COMIDAS,
    LIBROS,
    OTROS
}
