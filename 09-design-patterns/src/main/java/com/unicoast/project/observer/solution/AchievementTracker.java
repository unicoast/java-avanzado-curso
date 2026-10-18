package com.unicoast.project.observer.solution;

/*
 * PATRÓN OBSERVER: OBSERVADOR CONCRETO (CONCRETE OBSERVER) - SISTEMA DE LOGROS
 *
 * Evalúa reglas de progreso y logros basadas en el estado notificado.
 * Si la salud cae por debajo de 50 puntos, desbloquea el logro correspondiente.
 */
public class AchievementTracker implements Observer {

    @Override
    public void update(int health) {
        if (health < 50) {
            System.out.println("Logro desbloqueado: ¡Estás herido, pero sigues en pie!");
        }
    }
}
