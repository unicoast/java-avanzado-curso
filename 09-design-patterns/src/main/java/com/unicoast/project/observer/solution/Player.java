package com.unicoast.project.observer.solution;

import java.util.ArrayList;
import java.util.List;

/*
 * PATRÓN OBSERVER: SUJETO CONCRETO (CONCRETE SUBJECT)
 *
 * Propósito:
 * Implementa 'Subject' encapsulando el estado principal del jugador ('health')
 * y manteniendo una lista dinámica de observadores interesados en sus cambios.
 *
 * Ventajas sobre el enfoque sin patrón:
 * 1. Cumplimiento de SRP: 'Player' solo gestiona su lógica interna (salud y daño).
 *    Ya no conoce nada sobre la UI, el logger ni el sistema de logros.
 * 2. Cumplimiento de OCP: Se pueden agregar o remover cualquier cantidad de observadores
 *    en tiempo de ejecución sin tocar una sola línea de código de esta clase.
 * 3. Notificación Desacoplada: 'notifyObservers()' recorre la lista polimórfica e invoca
 *    'update(health)' en cada suscriptor.
 */
public class Player implements Subject {
    private int health;
    private List<Observer> observers = new ArrayList<>();

    /*
     * Constructor que inicializa la salud del jugador en su valor base.
     */
    public Player() {
        this.health = 100;
    }

    /*
     * Modifica el estado del jugador y desencadena automáticamente la notificación a los observadores.
     *
     * @param damage Cantidad de puntos de daño recibidos.
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println("El jugador recibió " + damage + " de daño. Salud actual: " + health);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(health);
        }
    }
}
