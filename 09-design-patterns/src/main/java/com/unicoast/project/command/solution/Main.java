package com.unicoast.project.command.solution;

import java.util.Scanner;

/*
 * PATRÓN DE DISEÑO: COMMAND (COMANDO)
 * TIPO DE PATRÓN: DE COMPORTAMIENTO (BEHAVIORAL)
 *
 * ¿Qué es?
 * Es un patrón que encapsula una petición o acción como un objeto independiente.
 *
 * ¿Qué problema soluciona?
 * Evita acoplar el bucle del juego con cadenas de 'if-else' y permite registrar
 * un historial de comandos para implementar operaciones de deshacer (Undo).
 *
 * ¿Cómo lo soluciona?
 * Convierte las acciones en objetos ('AttackCommand', 'DefendCommand') con métodos
 * 'execute()' y 'undo()'. Esto permite vincular teclas libremente en el 'InputHandler'
 * y deshacer la última acción con la tecla 'U' sin modificar la clase Player.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Instanciación del receptor (Receiver)
        Player player = new Player("Nicolás");

        // 2. Creación de los comandos concretos vinculados al jugador
        Command attack = new AttackCommand(player);
        Command defend = new DefendCommand(player);

        // 3. Asignación de teclas a cada comando en el InputHandler
        InputHandler inputHandler = new InputHandler();
        inputHandler.bind("A", attack);
        inputHandler.bind("D", defend);

        // 4. Bucle interactivo de consola
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- DEMO PATRÓN COMMAND CON UNDO ---");
        System.out.println("Comandos: A (Atacar), D (Defender).");
        System.out.println("Escribe 'U' para Deshacer la última acción.");
        System.out.println("Escribe 'SALIR' para terminar.");

        while (true) {
            System.out.print("Ingresa una acción: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("SALIR")) {
                break;
            }

            if (input.equals("U")) {
                inputHandler.undoLastCommand();
            } else {
                inputHandler.handleInput(input);
            }
            System.out.println("----------------------------------------");
        }

        scanner.close();
        System.out.println("Fin demo Patrón Command.");
    }
}
