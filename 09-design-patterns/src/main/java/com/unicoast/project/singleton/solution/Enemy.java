package com.unicoast.project.singleton.solution;

/*
 * Entidad Enemy (Solution):
 * Obtiene su identificador desde 'IdGenerator.getInstance().generateId()',
 * compartiendo el estado global del generador sin riesgo de colisión.
 */
public class Enemy {
    private final int id;

    public Enemy() {
        this.id = IdGenerator.getInstance().generateId();
    }

    public void showId() {
        System.out.println("Enemigo con Id: " + id);
    }
}
