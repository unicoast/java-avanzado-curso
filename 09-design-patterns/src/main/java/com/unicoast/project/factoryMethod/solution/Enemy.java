package com.unicoast.project.factoryMethod.solution;

/*
 * PATRÓN FACTORY METHOD: PRODUCTO ABSTRACTO (ABSTRACT PRODUCT)
 *
 * Propósito:
 * Define la interfaz o clase base común para todos los objetos que el
 * método fábrica ('createEnemy()') puede instanciar.
 *
 * Ventajas:
 * 1. Desacoplamiento: El cliente ('Game') programa contra esta abstracción ('Enemy')
 *    en lugar de clases concretas, facilitando la adición de nuevos enemigos.
 * 2. Polimorfismo: Cada subclase concreta proporciona su propia implementación
 *    del método 'attack()'.
 */
public abstract class Enemy {
    protected String name;

    /*
     * Operación abstracta que cada enemigo concreto implementará según su comportamiento.
     */
    public abstract void attack();
}
