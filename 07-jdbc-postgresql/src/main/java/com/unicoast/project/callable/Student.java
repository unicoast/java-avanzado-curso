package com.unicoast.project.callable;

import java.sql.Date;

/*
 * MODELO DE DOMINIO: STUDENT (Estudiante)
 *
 * Representa la entidad utilizada para mapear los resultados retornados
 * por funciones almacenadas (Stored Functions) y procedimientos en PostgreSQL.
 *
 * Atributos mapeados:
 * - 'id'        -> INTEGER
 * - 'name'      -> VARCHAR
 * - 'email'     -> VARCHAR
 * - 'birthDate' -> DATE (java.sql.Date)
 */
public class Student {
    private int id;
    private String name;
    private String email;
    private Date birthDate;

    public Student(int id, String name, String email, Date birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
