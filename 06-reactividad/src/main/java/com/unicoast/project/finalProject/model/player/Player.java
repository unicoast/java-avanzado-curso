package com.unicoast.project.finalProject.model.player;

/*
 * MODELO DE DOMINIO: PLAYER (Jugador)
 *
 * Representa la entidad central de negocio que viaja a través del pipeline reactivo.
 * Posee atributos encapsulados (name y age), constructores, getters, setters y toString().
 */
public class Player {
    private String name;
    private int age;

    public Player(String name, int age) {
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
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
