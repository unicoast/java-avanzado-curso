package com.unicoast.project.singleton.problem;

/*
 * Entidad Enemy (Problem):
 * Depende de la instancia externa de 'IdGenerator' que reciba en su constructor.
 */
public class Enemy {
    private int id;

    public Enemy(IdGenerator generator) {
        this.id = generator.generateId();
    }

    public void showId() {
        System.out.println("Enemigo con Id: " + id);
    }
}
