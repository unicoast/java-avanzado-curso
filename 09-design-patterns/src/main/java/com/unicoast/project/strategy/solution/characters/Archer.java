package com.unicoast.project.strategy.solution.characters;

import com.unicoast.project.strategy.solution.strategies.BowAttack;

/*
 * CONTEXTO CONCRETO: Archer
 *
 * Extiende de GameCharacter e inicializa su estrategia por defecto con 'BowAttack'.
 */
public class Archer extends GameCharacter {

    public Archer(String name) {
        super(name);
        this.attackStrategy = new BowAttack();
    }
}
