package com.unicoast.project.factoryMethod.solution;

/*
 * PATRÓN DE DISEÑO: FACTORY METHOD (MÉTODO FÁBRICA)
 * TIPO DE PATRÓN: CREACIONAL
 *
 * ¿Qué es?
 * Es un patrón que define una interfaz para crear objetos, pero delega
 * en las subclases la decisión de qué clase concreta instanciar.
 *
 * ¿Qué problema soluciona?
 * Evita tener una sola clase genérica con condicionales ('if-else') para diferenciar
 * enemigos y evita atar el código del cliente a clases concretas.
 *
 * ¿Cómo lo soluciona?
 * Define una fábrica abstracta ('EnemyFactory') y subclases concretas ('SkeletonFactory',
 * 'ZombieFactory') que se encargan de instanciar al enemigo correspondiente con su ataque propio.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Instanciación de fábricas concretas
        EnemyFactory skeletonFactory = new SkeletonFactory();
        EnemyFactory zombieFactory = new ZombieFactory();

        // 2. Creación de enemigos a través de cada fábrica
        Enemy skeleton = skeletonFactory.createEnemy();
        Enemy zombie = zombieFactory.createEnemy();

        // 3. Cada enemigo ejecuta su propio ataque especializado
        skeleton.attack();
        zombie.attack();
    }
}
