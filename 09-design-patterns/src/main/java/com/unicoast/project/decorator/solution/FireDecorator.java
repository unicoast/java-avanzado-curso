package com.unicoast.project.decorator.solution;

/*
 * PATRÓN DE DISEÑO: DECORATOR
 *
 * ROL: DECORADOR CONCRETO (CONCRETE DECORATOR)
 *
 * ¿Qué representa esta clase?
 * Es un decorador concreto que añade la capacidad de daño de fuego a cualquier 'Weapon'.
 *
 * Beneficios del diseño:
 * 1. Independencia del componente concreto: No está atado a 'Sword'. Puede decorar
 *    cualquier clase que implemente 'Weapon' (hachas, arcos, lanzas, etc.).
 * 2. Extensión no invasiva: En attack(), invoca primero a 'super.attack()' para preservar
 *    el comportamiento previo (sea el arma base u otro decorador) y luego añade el efecto
 *    de fuego adicional.
 * 3. Cumplimiento de OCP: Si se crea un 'IceDecorator' o 'ThunderDecorator',
 *    no se necesita modificar 'FireDecorator' ni ninguna otra clase existente.
 */
public class FireDecorator extends WeaponDecorator {

    public FireDecorator(Weapon decoratedWeapon) {
        super(decoratedWeapon);
    }

    @Override
    public void attack() {
        super.attack();
        System.out.println("Daño de fuego adicional.");
    }
}

