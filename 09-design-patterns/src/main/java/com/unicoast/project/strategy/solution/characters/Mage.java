package com.unicoast.project.strategy.solution.characters;

import com.unicoast.project.strategy.solution.strategies.MagicAttack;

/*
 * CONTEXTO CONCRETO: Mage
 *
 * Extiende de GameCharacter e inicializa su estrategia por defecto con 'MagicAttack'.
 */
public class Mage extends GameCharacter {

    public Mage(String name) {
        super(name);
        this.attackStrategy = new MagicAttack();
    }
}
