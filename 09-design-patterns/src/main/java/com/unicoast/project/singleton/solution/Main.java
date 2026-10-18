package com.unicoast.project.singleton.solution;

/*
 * PATRÓN DE DISEÑO: SINGLETON
 * TIPO DE PATRÓN: CREACIONAL
 *
 * ¿Qué es?
 * Es un patrón que garantiza que una clase tenga una única instancia en toda
 * la aplicación y provee un punto de acceso global a ella.
 *
 * ¿Qué problema soluciona?
 * Evita la duplicación o colisión de datos. En el juego, evita que se creen
 * varios generadores independientes y se repitan los mismos IDs en distintos personajes.
 *
 * ¿Cómo lo soluciona?
 * Define el constructor como privado ('private IdGenerator()') y provee el método global
 * 'getInstance()'. Así todos los personajes solicitan el ID a esa misma instancia
 * y los números salen siempre ordenados y únicos (1, 2, 3...).
 */
public class Main {
    public static void main(String[] args) {

        /*
         * Creación secuencial de entidades.
         * Cada invocación al constructor consulta la misma instancia de IdGenerator.
         */
        Player player = new Player();   // ID: 1
        Player player2 = new Player();  // ID: 2
        Player player3 = new Player();  // ID: 3
        Enemy enemy = new Enemy();      // ID: 4
        Enemy enemy2 = new Enemy();     // ID: 5
        Enemy enemy3 = new Enemy();     // ID: 6

        // Visualización de identificadores únicos correlativos
        player.showId();
        player2.showId();
        player3.showId();
        enemy.showId();
        enemy2.showId();
        enemy3.showId();
    }
}
