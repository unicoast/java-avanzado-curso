-- -----------------------------------------------------------------------------
-- 1. LIMPIEZA PREVIA (Opcional para reiniciar el entorno de pruebas)
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS persons CASCADE;

-- -----------------------------------------------------------------------------------
-- 2. TABLA: PERSONS
-- Entidad que almacena la información básica de las personas.
-- Se utiliza DATE para birth_date, compatible con java.time.LocalDate.
-- -----------------------------------------------------------------------------------
CREATE TABLE persons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birth_date DATE
);