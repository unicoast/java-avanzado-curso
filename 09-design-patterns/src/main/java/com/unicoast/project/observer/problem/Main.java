package com.unicoast.project.observer.problem;

/*
 * DEMOSTRACIÓN DEL PROBLEMA:
 *
 * Al recibir daño, el jugador se encarga directamente de actualizar la interfaz,
 * escribir en el registro y validar logros, acoplando fuertemente múltiples sistemas.
 */
public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        player.takeDamage(20);
        player.takeDamage(40);
    }
}
