package com.unicoast.project.factoryMethod.solution;

/*
 * PATRÓN FACTORY METHOD: CREADOR CONCRETO (CONCRETE CREATOR) - FÁBRICA DE ESQUELETOS
 *
 * Sobrescribe el método fábrica 'createEnemy()' para instanciar y retornar
 * un objeto concreto de tipo 'Skeleton'.
 */
public class SkeletonFactory extends EnemyFactory {

    @Override
    public Enemy createEnemy() {
        return new Skeleton();
    }
}
