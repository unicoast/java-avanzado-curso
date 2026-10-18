package com.unicoast.project.strategy.solution.strategies;

/*
 * ESTRATEGIA CONCRETA: MagicAttack
 *
 * Implementa el contrato 'AttackStrategy' encapsulando el algoritmo
 * específico para realizar un ataque mágico.
 */
public class MagicAttack implements AttackStrategy {
    @Override
    public void attack(String characterName) {
        System.out.println(characterName + " lanza un hechizo mágico.");
    }
}
