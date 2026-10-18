package com.unicoast.project.command.problem;

import java.util.Scanner;

/*
 * DEMOSTRACIÓN DEL PROBLEMA:
 *
 * Utiliza una cadena de 'if-else' para llamar a los métodos del jugador.
 * Para cambiar una tecla o agregar una acción nueva, es necesario modificar
 * este código directamente, en lugar de solo asignar un comando a un botón.
 */
public class Main {
    public static void main(String[] args) {
        Player player = new Player("Carlos el Inmortal");
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- DEMOSTRACIÓN DEL PROBLEMA (Acoplamiento Directo) ---");
        System.out.println("Comandos: A (Atacar), D (Defender), I (Item), R (Correr). Escribe 'salir' para terminar.");

        while (true) {
            System.out.print("Ingresa una acción: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("SALIR")) {
                break;
            }

            if (input.equals("A")) {
                player.attack();
            } else if (input.equals("D")) {
                player.defend();
            } else if (input.equals("I")) {
                player.useItem();
            } else if (input.equals("R")) {
                player.run();
            } else {
                System.out.println("Comando no reconocido: " + input);
            }
            System.out.println("----------------------------------------");
        }

        scanner.close();
        System.out.println("Fin de la demostración del problema.");
    }
}
