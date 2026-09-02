package com.unicoast.project.finalProject.view;

import com.unicoast.project.finalProject.controller.PlayerController;

import java.util.Scanner;

/*
 * CAPA DE VISTA: PLAYER CONSOLE VIEW (INTERFAZ POR CONSOLA)
 *
 * Responsabilidades:
 * 1. Muestra mensajes y captura interactivamente datos ingresados por consola.
 * 2. Delega el procesamiento de entrada al 'PlayerController'.
 * 3. Permite finalizar la carga de datos al ingresar 'exit'.
 */
public class PlayerConsoleView {
    private final PlayerController controller;
    private final Scanner scanner;

    public PlayerConsoleView(PlayerController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void start(){
        System.out.println("Para salir del programa ingrese 'exit'");
        while(true){
            System.out.println("Ingrese el nombre");
            String name = scanner.nextLine().trim();
            if(name.equalsIgnoreCase("exit")){
                controller.finishInput();
                System.out.println("Fin del programa");
                break;
            }

            System.out.println("Ingrese la edad");
            String age = scanner.nextLine().trim();

            boolean ok = controller.processInput(name, age);

            if(!ok){
                System.out.println("Edad incorrecta...");
            }
        }
    }
}
