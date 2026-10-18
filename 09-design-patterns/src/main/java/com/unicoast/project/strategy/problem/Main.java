package com.unicoast.project.strategy.problem;

/*
 * DEMOSTRACIÓN DEL PROBLEMA:
 *
 * Cada personaje tiene su ataque fijado en código. No es posible cambiarles
 * el arma o estilo de combate en tiempo de ejecución.
 */
public class Main {
    public static void main(String[] args) {
        Archer archer = new Archer("Legolas");
        Mage mage = new Mage("Gandalf");
        Warrior warrior = new Warrior("Auron");

        archer.attack();
        mage.attack();
        warrior.attack();

        // Limitación: no existe forma de cambiar de arma en tiempo de ejecución.
        // La siguiente línea daría error de compilación porque el ataque quedó soldado en la clase:
        // archer.setAttackStrategy(new MagicAttack());
    }
}
