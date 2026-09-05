-- =============================================================================
-- ESQUEMA DE BASE DE DATOS PRINCIPAL: PostgreSQL (Módulo 07-jdbc-postgresql)
-- Base de datos objetivo: java_course
-- Componentes: Categories, Products, Relaciones e Índices
-- =============================================================================

-- -----------------------------------------------------------------------------
-- 1. LIMPIEZA PREVIA (Opcional para reinicialización limpia)
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS categories CASCADE;

-- Alternativa: Vaciar datos y reiniciar los IDs autoincrementales a 1 sin eliminar tablas:
-- TRUNCATE TABLE products, categories RESTART IDENTITY CASCADE;

-- -----------------------------------------------------------------------------
-- 2. TABLA: CATEGORIES
-- Se define en primer lugar debido a que 'products' depende de su clave primaria.
-- -----------------------------------------------------------------------------
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- -----------------------------------------------------------------------------
-- 3. TABLA: PRODUCTS
-- Incluye clave foránea vinculada a la tabla 'categories'.
-- -----------------------------------------------------------------------------
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0,
    category_id INTEGER REFERENCES categories(id) ON DELETE SET NULL
);

-- -----------------------------------------------------------------------------
-- 4. ÍNDICES DE PRODUCTS
-- Acelera las búsquedas y joins filtrando por 'category_id'.
-- -----------------------------------------------------------------------------
CREATE INDEX idx_products_category ON products(category_id);

-- -----------------------------------------------------------------------------
-- 5. DATOS DE PRUEBA (SEEDING)
-- Inserción de catálogo completo para desarrollo y pruebas.
-- -----------------------------------------------------------------------------
INSERT INTO categories (name) VALUES
    ('TECNOLOGÍA'),
    ('ACCESORIOS'),
    ('LIBROS'),
    ('CURSOS');

INSERT INTO products (name, price, stock, category_id) VALUES
    ('Notebook Lenovo ThinkPad', 899.99, 10, 1),
    ('Monitor LG UltraWide 29', 249.99, 15, 1),
    ('Mouse Logitech MX Master 3', 99.50, 40, 2),
    ('Teclado Mecánico Keychron K2', 85.00, 25, 2),
    ('Clean Code - Robert C. Martin', 45.00, 30, 3),
    ('Effective Java - Joshua Bloch', 55.00, 20, 3),
    ('Java Avanzado', 1200.00, 20, 4),
    ('Master en Microservicios Spring Boot', 1500.00, 15, 4);

-- Sincronizar secuencias SERIAL al valor máximo insertado
SELECT setval('categories_id_seq', (SELECT COALESCE(MAX(id), 1) FROM categories));
SELECT setval('products_id_seq', (SELECT COALESCE(MAX(id), 1) FROM products));

-- -----------------------------------------------------------------------------
-- 6. COMANDOS ÚTILES DE CONSULTA Y MANTENIMIENTO
-- -----------------------------------------------------------------------------
-- Vaciar tablas y reiniciar secuencias SERIAL:
-- TRUNCATE TABLE products, categories RESTART IDENTITY CASCADE;

-- Consulta con LEFT JOIN para verificar relaciones entre productos y categorías:
-- SELECT p.id, p.name AS producto, p.price, p.stock, c.name AS categoria
-- FROM products p
-- LEFT JOIN categories c ON p.category_id = c.id;
