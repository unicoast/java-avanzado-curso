package com.unicoast.project.factoryMethod.solution;

/*
 * PATRÓN FACTORY METHOD: PRODUCTO CONCRETO (CONCRETE PRODUCT) - DRAGÓN
 *
 * Implementa la interfaz/clase base 'Enemy', definiendo las características
 * y el ataque especializado de un Dragón (ataque a distancia con fuego).
 */
public class Dragon extends Enemy {

    public Dragon() {
        this.name = "Dragon";
    }

    @Override
    public void attack() {
        System.out.println(name + " lanza fuego desde lo alto.");
    }
}
