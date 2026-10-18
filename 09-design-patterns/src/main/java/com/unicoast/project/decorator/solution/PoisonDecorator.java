package com.unicoast.project.decorator.solution;

/*
 * PATRÓN DE DISEÑO: DECORATOR
 *
 * ROL: DECORADOR CONCRETO (CONCRETE DECORATOR)
 *
 * ¿Qué representa esta clase?
 * Es un decorador concreto que añade daño de veneno a cualquier objeto 'Weapon'.
 *
 * Principio clave demostrado:
 * Componibilidad ilimitada: Al ser un 'WeaponDecorator', puede envolver directamente
 * a una 'Sword', o puede envolver a un 'FireDecorator' que a su vez envuelve a una 'Sword'.
 * Esto permite crear armas con fuego Y veneno en tiempo de ejecución sin necesidad
 * de crear una clase combinatoria 'FirePoisonSword'.
 */
public class PoisonDecorator extends WeaponDecorator {

    public PoisonDecorator(Weapon decoratedWeapon) {
        super(decoratedWeapon);
    }

    @Override
    public void attack() {
        super.attack();
        System.out.println("Daño de veneno adicional.");
    }
}

