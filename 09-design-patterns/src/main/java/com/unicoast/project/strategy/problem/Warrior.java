package com.unicoast.project.strategy.problem;

/*
 * EL PROBLEMA (SIN STRATEGY):
 * El guerrero tiene su ataque fijo con espada. No puede cambiar de estrategia
 * de combate de manera dinámica durante la partida.
 */
public class Warrior {
    private String name;

    public Warrior(String name) {
        this.name = name;
    }

    public void attack() {
        System.out.println(name + " ataca con una espada.");
    }
}
