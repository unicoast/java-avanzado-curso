package com.unicoast.project.advancedChaining;

/*
 * MODELO DE DOMINIO: STUDENT (Estudiante)
 *
 * Representa la entidad básica de datos utilizada para los ejemplos de encadenamiento y transformación con flatMap.
 *
 * Principios aplicados:
 * 1. Encapsulamiento: Atributos privados accesibles mediante métodos getters y setters.
 * 2. Representación textual: Sobrescritura de 'toString()' para inspección clara en los suscriptores.
 */
public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
