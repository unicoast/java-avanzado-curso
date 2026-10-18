package com.unicoast.project.postgres;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/*
 * CONCEPTO CLAVE: Capa de Servicio y Gestión Transaccional Manual (ACID)
 * PROPÓSITO: Coordinar la lógica de negocio y controlar el límite transaccional (commit/rollback)
 *            garantizando la integridad de los datos en la base de datos PostgreSQL.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Ciclo transaccional:
 *   1. connection.setAutoCommit(false): Deshabilita la confirmación automática por cada sentencia SQL.
 *   2. Ejecución de operaciones DAO: Se realizan los inserts/updates necesarios.
 *   3. connection.commit(): Si todo es exitoso, se consolidan atómicamente los cambios.
 *   4. connection.rollback(): En caso de excepción, se revierte cualquier cambio pendiente.
 * - Bloque finally:
 *   Restaura setAutoCommit(true) y devuelve la conexión al pool de HikariCP invocando connection.close().
 *   En un pool de conexiones, close() no cierra el socket TCP, sino que retorna la conexión a estado inactivo (idle).
 */
public class PersonService {
    public void savePerson(Person person) throws SQLException{
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);

            PersonDao personDAO = new PersonDao(connection);

            personDAO.save(person);

            connection.commit();

        } catch (SQLException e) {
            if(connection != null){
                connection.rollback();
            }

            System.out.println(e.getMessage());

            throw e;
        } finally {
            if(connection!=null){
                try{
                    connection.setAutoCommit(true);
                    connection.close();
                }catch (SQLException e){
                    System.out.println("Error al cerrar la conexión...");
                }
            }
        }
    }

    public Person findPersonById(int id) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()){
            PersonDao personDao = new PersonDao(connection);
            return personDao.findById(id);
        }
    }

    public List<Person> findAllPersons() throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            PersonDao personDao = new PersonDao(connection);
            return personDao.findAll();
        }
    }
}
