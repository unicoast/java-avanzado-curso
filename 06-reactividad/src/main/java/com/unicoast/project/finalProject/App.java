package com.unicoast.project.finalProject;

import com.unicoast.project.finalProject.controller.PlayerController;
import com.unicoast.project.finalProject.model.player.Player;
import com.unicoast.project.finalProject.service.PlayerService;
import com.unicoast.project.finalProject.stream.PlayerStream;
import com.unicoast.project.finalProject.view.PlayerConsoleView;

/*
 * PROYECTO FINAL: APLICACIÓN REACTIVA CON PATRÓN MVC Y BUS DE EVENTOS
 *
 * Flujo de Inicialización:
 * 1. PlayerStream: Bus de eventos reactivo central ('PublishSubject').
 * 2. PlayerService: Capa de lógica de negocio y almacenamiento en memoria.
 * 3. PlayerController: Orquesta el flujo, validaciones con flatMap y resiliencia con onErrorResumeNext.
 * 4. PlayerConsoleView: Vista por consola que captura la interacción y delega al controlador.
 */
public class App {
    public static void main(String[] args) {
        PlayerStream stream = new PlayerStream();

        /*
         * PRUEBA INICIAL AISLADA DEL STREAM (Comentada):
         * Permite probar la emisión y finalización directa sobre el bus antes de integrar el MVC completo.
         *
        stream.getStream().subscribe(
                item -> System.out.println(item),
                error -> System.out.println(error),
                () -> System.out.println("Fin")
        );

        stream.publish(new Player("Nicolás", 26));
        stream.publish(new Player("Novak", 39));

        // no se emite nada más
        stream.complete();
         */

        PlayerService playerService = new PlayerService();
        PlayerController playerController = new PlayerController(stream, playerService);
        PlayerConsoleView view = new PlayerConsoleView(playerController);

        view.start();
    }
}
