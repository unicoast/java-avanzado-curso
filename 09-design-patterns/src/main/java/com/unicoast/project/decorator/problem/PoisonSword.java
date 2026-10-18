package com.unicoast.project.decorator.problem;

/*
 * Subclase para agregar veneno a la espada.
 * Problema: para tener fuego y veneno al mismo tiempo,
 * Java no permite heredar de dos clases a la vez.
 */
public class PoisonSword extends Sword {

    @Override
    public void attack() {
        super.attack();
        System.out.println("Daño de veneno adicional");
    }
}
