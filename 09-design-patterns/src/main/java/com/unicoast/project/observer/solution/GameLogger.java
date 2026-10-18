package com.unicoast.project.observer.solution;

/*
 * PATRÓN OBSERVER: OBSERVADOR CONCRETO (CONCRETE OBSERVER) - REGISTRO DE EVENTOS
 *
 * Registra en consola o archivo de log el estado actualizado de la salud del jugador
 * tras cada cambio notificado por el sujeto.
 */
public class GameLogger implements Observer {

    @Override
    public void update(int health) {
        System.out.println("Registro: salud del jugador = " + health);
    }
}
