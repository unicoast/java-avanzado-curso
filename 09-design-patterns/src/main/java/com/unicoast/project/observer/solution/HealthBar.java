package com.unicoast.project.observer.solution;

/*
 * PATRÓN OBSERVER: OBSERVADOR CONCRETO (CONCRETE OBSERVER) - BARRA DE SALUD
 *
 * Simula el componente visual de la interfaz de usuario.
 * Se actualiza cada vez que el sujeto notifica un cambio en los puntos de salud.
 */
public class HealthBar implements Observer {

    @Override
    public void update(int health) {
        System.out.println("Barra de salud actualizada: " + health);
    }
}
