package com.unicoast.project.finalProject.service;

import com.unicoast.project.finalProject.model.player.Player;
import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.List;

/*
 * CAPA DE SERVICIO: PLAYER SERVICE (LÓGICA DE NEGOCIO REACTIVA)
 *
 * Responsabilidades:
 * 1. Mantiene el almacenamiento en memoria ('List<Player>').
 * 2. Escucha el stream de datos para agregar jugadores y calcular estadísticas al completarse.
 * 3. Ejecuta validaciones asíncronas encapsuladas en 'Observable.create()'.
 */
public class PlayerService {
    private final List<Player> players = new ArrayList<>();

    public void subscribeTo(Observable<Player> stream){
        stream.subscribe(
                player -> {
                    System.out.println("Agregando jugador... " + player);
                    players.add(player);
                },
                error -> System.out.println("Error en el Stream: " + error.getMessage()),
                () -> {
                    System.out.println("Stream completo. Jugadores cargados...");
                    players.forEach(System.out::println);
                    showStatatics();
                }
        );
    }

    private void showStatatics(){
        System.out.println("Estadísticas");
        System.out.println("Total: " + players.size());
        if(!players.isEmpty()){
            double ageAverage = players.stream()
                    .mapToInt(Player::getAge)
                    .average()
                    .orElse(0);
            System.out.println("Promedio de edad: " + ageAverage);
        }
    }

    // Lógica de negocio, jugadores
    public Observable<Player> verifyPlayer(Player player) {
        return Observable.create(emitter -> {
            System.out.println("Verificando... " + player.getName());
            Thread.sleep(1000);

            if(player.getAge() >= 18){
                emitter.onNext(player);
            } else {
                System.out.println("Jugador con edad no permitida..." + player.getName());
            }

            emitter.onComplete();
        });
    }

    public Observable<Player> validateName(Player player) {
        return Observable.create(emitter -> {
            System.out.println("Verificando... " + player.getName());
            Thread.sleep(1000);

            if(player.getName().length() < 3){
                // esto rompe el flujo
              emitter.onError(new IllegalArgumentException("Error en la longitud del nombre..."));
            } else {
                emitter.onNext(player);
                System.out.println("Jugador con edad no permitida..." + player.getName());
            }

            emitter.onComplete();
        });
    }
}
