-- =============================================================================
-- ESQUEMA DE BASE DE DATOS: CALLABLESTATEMENT (Módulo 07-jdbc-postgresql)
-- Base de datos objetivo: java_course
-- Componentes: Tabla Students, Stored Function y Stored Procedure
-- Usado por: com.unicoast.project.callable (FunctionExample y ProcedureExample)
-- =============================================================================

-- -----------------------------------------------------------------------------
-- 1. LIMPIEZA PREVIA (Opcional para reinicialización limpia)
-- -----------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS register_student(VARCHAR, VARCHAR, DATE, INTEGER);
DROP FUNCTION IF EXISTS find_by_id_students(INTEGER);
DROP TABLE IF EXISTS students CASCADE;

-- Alternativa: Vaciar estudiantes y reiniciar ID a 1 sin eliminar la tabla ni funciones:
-- TRUNCATE TABLE students RESTART IDENTITY CASCADE;

-- -----------------------------------------------------------------------------
-- 2. TABLA: STUDENTS
-- Modelo de prueba para persistencia y lectura con rutinas almacenadas.
-- -----------------------------------------------------------------------------
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    birth_date DATE NOT NULL
);

-- -----------------------------------------------------------------------------
-- 3. FUNCIÓN ALMACENADA (STORED FUNCTION): find_by_id_students
-- Utilizada por com.unicoast.project.callable.FunctionExample.
-- Invocación en JDBC: SELECT * FROM find_by_id_students(?) con executeQuery().
-- -----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION find_by_id_students(p_id INTEGER)
RETURNS TABLE (
    id INTEGER,
    name VARCHAR(100),
    email VARCHAR(100),
    birth_date DATE
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY
    SELECT s.id, s.name, s.email, s.birth_date
    FROM students s
    WHERE s.id = p_id;
END;
$$;

-- -----------------------------------------------------------------------------
-- 4. PROCEDIMIENTO ALMACENADO (STORED PROCEDURE): register_student
-- Utilizado por com.unicoast.project.callable.ProcedureExample.
-- Invocación en JDBC: CALL register_student(?, ?, ?, ?) con execute() y OUT param.
-- -----------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE register_student(
    IN p_name VARCHAR(100),
    IN p_email VARCHAR(100),
    IN p_birth_date DATE,
    OUT p_new_id INTEGER
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO students (name, email, birth_date)
    VALUES (p_name, p_email, p_birth_date)
    RETURNING id INTO p_new_id;
END;
$$;

-- -----------------------------------------------------------------------------
-- 5. DATOS DE PRUEBA (SEEDING)
-- -----------------------------------------------------------------------------
-- Incluye el estudiante con ID = 10 para probar FunctionExample directamente
INSERT INTO students (id, name, email, birth_date) VALUES
    (1, 'Carlos Perez', 'carlos@example.com', '1995-04-12'),
    (2, 'Maria Gomez', 'maria@example.com', '1998-08-23'),
    (10, 'Juan Morales', 'juan.morales@example.com', '2000-01-15');

-- Sincronizar la secuencia SERIAL al valor máximo insertado
SELECT setval('students_id_seq', (SELECT COALESCE(MAX(id), 1) FROM students));

-- -----------------------------------------------------------------------------
-- 6. COMANDOS ÚTILES DE CONSULTA Y MANTENIMIENTO
-- -----------------------------------------------------------------------------
-- Probar la función directamente en PostgreSQL:
-- SELECT * FROM find_by_id_students(10);

-- Probar el procedimiento directamente en PostgreSQL:
-- CALL register_student('Prueba Procedure', 'test.proc@example.com', '2000-01-01', NULL);
