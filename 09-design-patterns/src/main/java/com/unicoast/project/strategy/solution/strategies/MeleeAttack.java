package com.unicoast.project.strategy.solution.strategies;

/*
 * ESTRATEGIA CONCRETA: MeleeAttack
 *
 * Implementa el contrato 'AttackStrategy' encapsulando el algoritmo
 * específico para realizar un ataque cuerpo a cuerpo.
 */
public class MeleeAttack implements AttackStrategy {
    @Override
    public void attack(String characterName) {
        System.out.println(characterName + " ataca cuerpo a cuerpo.");
    }
}
