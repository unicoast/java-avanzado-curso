package com.unicoast.project.finalProject.controller;

import com.unicoast.project.finalProject.model.player.Player;
import com.unicoast.project.finalProject.service.PlayerService;
import com.unicoast.project.finalProject.stream.PlayerStream;
import io.reactivex.rxjava3.core.Observable;

/*
 * CAPA DE CONTROLADOR: PLAYER CONTROLLER (ORQUESTADOR REACTIVO)
 *
 * Responsabilidades:
 * 1. Conecta el stream de entrada con las reglas de negocio del servicio mediante 'flatMap()'.
 * 2. Manejo de resiliencia con 'onErrorResumeNext()' para evitar que un error rompa el flujo principal.
 * 3. Valida sintácticamente los datos de entrada antes de publicarlos en el bus reactivo.
 */
public class PlayerController {
    private final PlayerStream stream;
    private final PlayerService service;

    public PlayerController(PlayerStream stream, PlayerService service) {
        this.stream = stream;
        this.service = service;
        // se debe suscribir aquí, por qué? cuando se construye el controlador directamente se suscribe al stream para que devuelva
        this.service.subscribeTo(
                stream.getStream()
                        // flatMap transforma un objeto en un Observable
                        .flatMap(service::verifyPlayer)
                        .flatMap(player -> service.validateName(player))
                        .onErrorResumeNext(throwable -> {
                            System.out.println("Error: " + throwable.getMessage());
                            // recuperar el flujo (stream) para el onComplete para seguir emitiendo datos
                            return Observable.empty();
                        })

                        /*
                        // esto no porque la validacion se hace arriba y debe ser en el service, no controller
                        .filter(player -> player.getAge() >= 21)
                        .map(player -> new Player(player.getName().toUpperCase(), player.getAge()))
                         */
        );
    }

    public boolean processInput(String name, String ageInput){
        try {
            int age = Integer.parseInt(ageInput);
            stream.publish(new Player(name, age));
            return true;
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void finishInput(){
        stream.complete();
    }
}
