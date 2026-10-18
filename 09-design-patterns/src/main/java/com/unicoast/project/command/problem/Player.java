package com.unicoast.project.command.problem;

/*
 * EL PROBLEMA (SIN COMMAND):
 *
 * El jugador tiene sus acciones como métodos normales ('attack()', 'defend()').
 * Como las acciones no son objetos, no es posible guardarlas en un historial,
 * ponerlas en cola ni deshacerlas (Undo).
 */
public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
        System.out.println("Jugador " + name + " creado.");
    }

    public void attack() {
        System.out.println(name + " ataca con su espada.");
    }

    public void defend() {
        System.out.println(name + " se defiende con su escudo.");
    }

    public void useItem() {
        System.out.println(name + " usa una pocion de vida.");
    }

    public void run() {
        System.out.println(name + " corre a gran velocidad.");
    }
}
