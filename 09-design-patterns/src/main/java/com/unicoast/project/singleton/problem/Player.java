package com.unicoast.project.singleton.problem;

/*
 * Entidad Player (Problem):
 * Recibe una instancia de 'IdGenerator' por parámetro (inyección manual).
 * Si se le suministra una instancia diferente a la usada por otras entidades,
 * se produce una colisión de identificadores.
 */
public class Player {
    private int id;

    public Player(IdGenerator generator) {
        this.id = generator.generateId();
    }

    public void showId() {
        System.out.println("Jugador con Id: " + id);
    }
}
