package com.unicoast.project.decorator.problem;

/*
 * DEMOSTRACIÓN DEL PROBLEMA:
 *
 * Para cada combinación de efectos se requiere una clase diferente,
 * provocando una explosión combinatoria rígida de subclases.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- 1. Espada básica ---");
        Sword sword = new Sword();
        sword.attack();

        System.out.println("-------------------------");

        System.out.println("--- 2. Espada con fuego ---");
        FireSword fireSword = new FireSword();
        fireSword.attack();

        System.out.println("-------------------------");

        System.out.println("--- 3. Espada con fuego y veneno ---");
        FirePoisonSword firePoisonSword = new FirePoisonSword();
        firePoisonSword.attack();
    }
}
