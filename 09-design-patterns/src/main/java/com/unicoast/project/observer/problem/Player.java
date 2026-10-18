package com.unicoast.project.observer.problem;

/*
 * EL PROBLEMA (SIN OBSERVER):
 *
 * El jugador hace demasiadas cosas: cuando recibe daño, él mismo actualiza
 * la barra de vida, escribe en el log y revisa los logros.
 * Si a futuro se requiere agregar sonido o alertas, es necesario modificar
 * la clase Player repetidamente.
 */
public class Player {
    private int health;

    public Player() {
        this.health = 100;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println("El jugador recibió " + damage + " de daño. Salud actual: " + health);

        // Notificación manual directamente acoplada a cada subsistema
        updateHealthBar();
        logDamage();
        checkAchievements();
    }

    private void updateHealthBar() {
        System.out.println("Barra de salud actualizada: " + health);
    }

    private void logDamage() {
        System.out.println("Registro: salud del jugador = " + health);
    }

    private void checkAchievements() {
        if (health < 50) {
            System.out.println("Logro desbloqueado: ¡Estás herido, pero sigues en pie!");
        }
    }
}
