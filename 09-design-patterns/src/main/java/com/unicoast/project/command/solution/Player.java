package com.unicoast.project.command.solution;

/*
 * PATRÓN COMMAND: RECEPTOR (RECEIVER)
 *
 * Propósito:
 * Contiene la lógica de negocio y sabe cómo realizar las operaciones asociadas
 * a cada solicitud del juego.
 *
 * Desacoplamiento:
 * El receptor no sabe qué comando lo invoca ni desde qué interfaz o atajo de teclado
 * se originó la petición. Simplemente expone sus métodos de acción.
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
