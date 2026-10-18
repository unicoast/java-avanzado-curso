package com.unicoast.project.strategy.problem;

/*
 * EL PROBLEMA (SIN STRATEGY):
 * El ataque está escrito directamente dentro de la clase ('dispara una flecha').
 * Si el arquero quiere cambiar de arma o usar magia en plena partida,
 * no puede hacerlo sin modificar esta clase.
 */
public class Archer {
    private String name;

    public Archer(String name) {
        this.name = name;
    }

    public void attack() {
        System.out.println(name + " dispara una flecha.");
    }
}
