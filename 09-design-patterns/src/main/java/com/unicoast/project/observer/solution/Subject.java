package com.unicoast.project.observer.solution;

/*
 * PATRÓN DE DISEÑO: OBSERVER (INTERFAZ SUJETO)
 * TIPO DE PATRÓN: DE COMPORTAMIENTO (BEHAVIORAL)
 *
 * Propósito:
 * Define las operaciones para gestionar observadores:
 * suscribir ('addObserver'), desuscribir ('removeObserver') y avisar ('notifyObservers').
 */
public interface Subject {

    /*
     * Registra un nuevo observador en la lista de suscriptores.
     */
    void addObserver(Observer observer);

    /*
     * Elimina un observador de la lista de suscriptores.
     */
    void removeObserver(Observer observer);

    /*
     * Notifica a todos los observadores registrados sobre un cambio de estado.
     */
    void notifyObservers();
}
