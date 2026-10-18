package com.unicoast.project.observer.solution;

/*
 * PATRÓN OBSERVER: INTERFAZ OBSERVADOR (OBSERVER INTERFACE)
 *
 * Propósito:
 * Define el contrato que deben cumplir todos los componentes que deseen
 * ser notificados sobre los cambios de estado en un Sujeto ('Subject').
 *
 * Desacoplamiento:
 * El sujeto no necesita conocer los tipos concretos de sus observadores;
 * solo se comunica a través de este método 'update()'.
 */
public interface Observer {

    /*
     * Método invocado por el sujeto observable cuando ocurre un cambio relevante.
     *
     * @param health Estado actualizado de la salud del jugador.
     */
    void update(int health);
}
