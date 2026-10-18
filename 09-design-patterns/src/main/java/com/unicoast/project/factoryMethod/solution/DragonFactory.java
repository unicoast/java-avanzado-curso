package com.unicoast.project.factoryMethod.solution;

/*
 * PATRÓN FACTORY METHOD: CREADOR CONCRETO (CONCRETE CREATOR) - FÁBRICA DE DRAGONES
 *
 * Sobrescribe el método fábrica 'createEnemy()' para instanciar y retornar
 * un objeto concreto de tipo 'Dragon'.
 */
public class DragonFactory extends EnemyFactory {

    @Override
    public Enemy createEnemy() {
        return new Dragon();
    }
}
