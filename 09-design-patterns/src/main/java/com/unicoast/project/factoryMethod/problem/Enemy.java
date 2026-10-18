package com.unicoast.project.factoryMethod.problem;

/*
 * EL PROBLEMA (SIN FACTORY METHOD):
 *
 * Todos los enemigos se crean con esta misma clase y hacen exactamente lo mismo.
 * No es posible hacer que un Dragón lance fuego o un Esqueleto dispare flechas
 * sin llenar el código de condicionales (if/else).
 */
public class Enemy {
    protected String name;

    public Enemy(String name) {
        this.name = name;
    }

    public void attack() {
        System.out.println(name + " ataca ferozmente.");
    }
}
