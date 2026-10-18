package com.unicoast.project.strategy.solution.strategies;

/*
 * ESTRATEGIA CONCRETA: BowAttack
 *
 * Implementa el contrato 'AttackStrategy' encapsulando el algoritmo
 * específico para realizar un ataque con arco.
 */
public class BowAttack implements AttackStrategy {
    @Override
    public void attack(String characterName) {
        System.out.println(characterName + " dispara una flecha.");
    }
}
