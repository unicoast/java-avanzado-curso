package com.unicoast.project.strategy.solution.characters;

import com.unicoast.project.strategy.solution.strategies.MeleeAttack;

/*
 * CONTEXTO CONCRETO: Warrior
 *
 * Extiende de GameCharacter e inicializa su estrategia por defecto con 'MeleeAttack'.
 */
public class Warrior extends GameCharacter {

    public Warrior(String name) {
        super(name);
        this.attackStrategy = new MeleeAttack();
    }
}
