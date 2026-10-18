package com.unicoast.project.factoryMethod.solution;

/*
 * PATRÓN DE DISEÑO: FACTORY METHOD (CREADOR ABSTRACTO)
 * TIPO DE PATRÓN: CREACIONAL
 *
 * Propósito:
 * Define el método fábrica ('createEnemy()') que retorna un 'Enemy'.
 * Las subclases ('DragonFactory', 'SkeletonFactory', etc.) se encargan de instanciar
 * la clase concreta correspondiente sin acoplar el código que las utiliza.
 */
public abstract class EnemyFactory {

    /*
     * Método Fábrica (Factory Method):
     * Cada fábrica concreta sobreescribe este método para retornar una instancia
     * específica de su correspondiente enemigo.
     */
    public abstract Enemy createEnemy();
}
