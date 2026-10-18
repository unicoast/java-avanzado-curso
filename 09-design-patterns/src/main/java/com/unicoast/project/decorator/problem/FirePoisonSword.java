package com.unicoast.project.decorator.problem;

/*
 * Subclase combinatoria: es necesario crear una clase nueva solo para
 * poder juntar fuego y veneno, repitiendo el código de ambas.
 * Si se agregan más efectos (hielo, rayo), se genera una cantidad inmanejable de clases.
 */
public class FirePoisonSword extends Sword {

    @Override
    public void attack() {
        super.attack();
        System.out.println("Daño de fuego adicional.");
        System.out.println("Daño de veneno adicional.");
    }
}
