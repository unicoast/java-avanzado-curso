package com.unicoast.project.singleton.solution;

/*
 * Entidad Player (Solution):
 * Obtiene su identificador invocando el punto de acceso global 'IdGenerator.getInstance().generateId()'.
 * Al utilizar la misma instancia que las demás entidades, se garantiza la secuencia única de IDs.
 */
public class Player {
    private final int id;

    public Player() {
        this.id = IdGenerator.getInstance().generateId();
    }

    public void showId() {
        System.out.println("Jugador con Id: " + id);
    }
}
