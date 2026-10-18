package com.unicoast.project.strategy.solution.characters;

import com.unicoast.project.strategy.solution.strategies.AttackStrategy;

/*
 * CONTEXTO ABSTRACTO: GameCharacter
 *
 * En el patrón Strategy, el Contexto es la clase que mantiene una referencia
 * a un objeto Strategy (AttackStrategy) y delega en él la ejecución del comportamiento.
 *
 * Principio aplicado: Composición sobre Herencia.
 * En lugar de heredar el comportamiento de ataque, el personaje "tiene una" estrategia
 * de ataque, lo que permite cambiarla dinámicamente en tiempo de ejecución.
 */
public abstract class GameCharacter {
    protected String name;
    protected AttackStrategy attackStrategy;

    public GameCharacter(String name) {
        this.name = name;
    }

    /*
     * Permite cambiar la estrategia de ataque en tiempo de ejecución.
     */
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    /*
     * Delega la acción de ataque a la estrategia configurada actualmente.
     */
    public void attack() {
        if (attackStrategy != null) {
            attackStrategy.attack(name);
        } else {
            System.out.println(name + " no tiene estrategia de ataque.");
        }
    }
}
