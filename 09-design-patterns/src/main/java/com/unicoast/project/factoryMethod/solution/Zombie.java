package com.unicoast.project.factoryMethod.solution;

/*
 * PATRÓN FACTORY METHOD: PRODUCTO CONCRETO (CONCRETE PRODUCT) - ZOMBI
 *
 * Implementa 'Enemy' definiendo el comportamiento específico de un Zombi
 * (ataque físico cuerpo a cuerpo).
 */
public class Zombie extends Enemy {

    public Zombie() {
        this.name = "Zombie";
    }

    @Override
    public void attack() {
        System.out.println(name + " lanza un ataque cuerpo a cuerpo.");
    }
}
