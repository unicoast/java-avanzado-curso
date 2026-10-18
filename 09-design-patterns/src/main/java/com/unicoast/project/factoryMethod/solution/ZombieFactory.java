package com.unicoast.project.factoryMethod.solution;

/*
 * PATRÓN FACTORY METHOD: CREADOR CONCRETO (CONCRETE CREATOR) - FÁBRICA DE ZOMBIS
 *
 * Sobrescribe el método fábrica 'createEnemy()' para instanciar y retornar
 * un objeto concreto de tipo 'Zombie'.
 */
public class ZombieFactory extends EnemyFactory {

    @Override
    public Enemy createEnemy() {
        return new Zombie();
    }
}
