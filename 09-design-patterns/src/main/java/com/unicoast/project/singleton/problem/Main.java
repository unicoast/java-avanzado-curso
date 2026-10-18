package com.unicoast.project.singleton.problem;

/*
 * DEMOSTRACIÓN DEL PROBLEMA:
 *
 * Al crear dos generadores distintos ('gen1' y 'gen2'), tanto el Player
 * como el Enemy reciben el ID 1. ¡Hay duplicación de IDs!
 */
public class Main {
    public static void main(String[] args) {
        // Se instancian dos generadores independientes en memoria
        IdGenerator gen1 = new IdGenerator();
        IdGenerator gen2 = new IdGenerator();

        // Cada entidad recibe una instancia distinta, produciendo duplicación de IDs
        Player player = new Player(gen1); // Genera ID: 1
        Enemy enemy = new Enemy(gen2);    // Genera ID: 1 (¡Colisión de identificadores!)

        player.showId();
        enemy.showId();
    }
}
