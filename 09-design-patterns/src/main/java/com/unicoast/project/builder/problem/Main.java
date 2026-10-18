package com.unicoast.project.builder.problem;

/*
 * DEMOSTRACIÓN DEL PROBLEMA:
 *
 * Para crear a Auron, hay que pasar 9 argumentos obligatorios por constructor.
 * A simple vista es difícil saber qué significa cada número (10, 15, 12, 0, 8, 7, 1500)
 * y un simple cambio de posición arruinaría las estadísticas del personaje.
 */
public class Main {
    public static void main(String[] args) {
        GameCharacter character = new GameCharacter(
                "Auron", "Warrior", 10, 15, 12, 0, 8, 7, 1500
        );

        character.showStats();
    }
}
