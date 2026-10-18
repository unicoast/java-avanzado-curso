package com.unicoast.project.factoryMethod.solution;

/*
 * PATRÓN FACTORY METHOD: PRODUCTO CONCRETO (CONCRETE PRODUCT) - ESQUELETO
 *
 * Implementa 'Enemy' definiendo el comportamiento particular de un Esqueleto
 * (ataque a distancia con arco y flecha).
 */
public class Skeleton extends Enemy {

    public Skeleton() {
        this.name = "Skeleton";
    }

    @Override
    public void attack() {
        System.out.println(name + " dispara una flecha con su arco.");
    }
}
