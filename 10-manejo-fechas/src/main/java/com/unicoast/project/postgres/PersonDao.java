package com.unicoast.project.postgres;

import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * CONCEPTO CLAVE: Patrón DAO (Data Access Object) y conversión JDBC con java.sql.Date
 * PROPÓSITO: Encapsular el acceso a datos para la entidad Person y tender el puente entre
 *            la API moderna java.time.LocalDate y los tipos SQL de JDBC.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Conversión bidireccional JDBC <-> LocalDate:
 *   - Escritura (INSERT): java.sql.Date.valueOf(localDate) transforma el LocalDate en java.sql.Date para el PreparedStatement.
 *   - Lectura (SELECT): resultSet.getDate("columna").toLocalDate() transforma el java.sql.Date en un LocalDate inmutable.
 * - Prevención de inyección SQL: PreparedStatement parametriza las consultas (?, ?) garantizando seguridad.
 * - try-with-resources: Garantiza el cierre automático e incondicional de PreparedStatement y ResultSet,
 *   evitando fugas de cursores y descriptores en el servidor PostgreSQL.
 */
@RequiredArgsConstructor
public class PersonDao {
    private final Connection connection;

    public void save(Person person) throws SQLException {
        String sql = "INSERT INTO persons (name, birth_date) VALUES (?, ?)";

        try (
                PreparedStatement statement = connection.prepareStatement(sql)
        ){
            statement.setString(1, person.getName());
            statement.setDate(2, Date.valueOf(person.getBirthDate()));

            int rows = statement.executeUpdate();
            showMessage(rows, "Persona ingresada correctamente", "");
        }
    }

    public Person findById(int id) throws SQLException {
        String sql = "SELECT * FROM persons WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSets = statement.executeQuery();

            if (resultSets.next()) {
                String name = resultSets.getString("name");
                LocalDate birthDate = resultSets.getDate("birth_date").toLocalDate();

                return new Person(id, name, birthDate);
            } else {
                System.out.println("Persona con ID: " + id + " no encontrada");
                return null;
            }
        }
    }

    public List<Person> findAll() throws SQLException {
        String sql = "SELECT * FROM persons ORDER BY id";
        List<Person> persons = new ArrayList<>();

        try (
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();

                persons.add(new Person(id, name, birthDate));
            }
        }
        return persons;
    }

    private void showMessage(int rows, String msgOK, String msgError) {
        if (rows > 0) {
            System.out.println(msgOK);
        } else if (!msgError.isBlank()) {
            System.out.println(msgError);
        }
    }
}
