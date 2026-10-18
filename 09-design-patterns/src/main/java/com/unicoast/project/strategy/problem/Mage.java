package com.unicoast.project.strategy.problem;

/*
 * EL PROBLEMA (SIN STRATEGY):
 * El mago tiene su hechizo fijo. Si se requiere que otro personaje use magia
 * o que el mago cambie a un arma distinta, es necesario duplicar o modificar código.
 */
public class Mage {
    private String name;

    public Mage(String name) {
        this.name = name;
    }

    public void attack() {
        System.out.println(name + " lanza un hechizo mágico.");
    }
}
