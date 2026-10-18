package com.unicoast.project.strategy.solution;

import com.unicoast.project.strategy.solution.characters.Archer;
import com.unicoast.project.strategy.solution.characters.GameCharacter;
import com.unicoast.project.strategy.solution.characters.Mage;
import com.unicoast.project.strategy.solution.characters.Warrior;
import com.unicoast.project.strategy.solution.strategies.MagicAttack;

/*
 * PATRÓN DE DISEÑO: STRATEGY (ESTRATEGIA)
 * TIPO DE PATRÓN: DE COMPORTAMIENTO (BEHAVIORAL)
 *
 * ¿Qué es?
 * Es un patrón que permite definir una familia de algoritmos, encapsular cada uno
 * en una clase separada y hacerlos intercambiables entre sí.
 *
 * ¿Qué problema soluciona?
 * Evita tener ataques soldados o rígidos dentro de cada personaje, permitiendo
 * cambiar de arma o estilo de combate en cualquier momento.
 *
 * ¿Cómo lo soluciona?
 * Separa los ataques en clases independientes ('BowAttack', 'MagicAttack', 'MeleeAttack')
 * bajo la interfaz 'AttackStrategy'. Cada personaje guarda su estrategia y permite
 * cambiarla en tiempo real con 'setAttackStrategy()' sin modificar su clase.
 */
public class Main {
    public static void main(String[] args) {

        // 1. Creación de personajes; cada uno inicia con su estrategia por defecto:
        //    Archer -> BowAttack, Mage -> MagicAttack, Warrior -> MeleeAttack
        GameCharacter archer = new Archer("Legolas");
        GameCharacter mage = new Mage("Gandalf");
        GameCharacter warrior = new Warrior("Auron");

        // 2. Ejecución de ataques iniciales
        archer.attack();
        mage.attack();
        warrior.attack();

        // 3. Cambio de estrategia en plena partida: el arquero ahora lanza magia
        archer.setAttackStrategy(new MagicAttack());
        archer.attack();
    }
}
