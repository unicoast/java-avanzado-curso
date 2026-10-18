package com.unicoast.project.decorator.solution;

/*
 * PATRÓN DE DISEÑO: DECORATOR (DECORADOR / ENVOLTORIO)
 * TIPO DE PATRÓN: ESTRUCTURAL
 *
 * ¿Qué es?
 * Es un patrón que permite añadir nuevas responsabilidades y funcionalidades
 * a un objeto de forma dinámica, envolviéndolo dentro de un objeto decorador.
 *
 * ¿Qué problema soluciona?
 * Resuelve la explosión combinatoria de clases que ocurre cuando se usa herencia
 * para cada posible combinación de efectos (fuego, veneno, fuego y veneno, etc.).
 *
 * ¿Cómo lo soluciona?
 * En lugar de crear subclases, utiliza composición: toma una espada básica ('Sword')
 * y la envuelve dinámicamente dentro de decoradores ('FireDecorator', 'PoisonDecorator')
 * como muñecas rusas, permitiendo apilar mejoras en plena partida.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Componente básico: espada estándar
        System.out.println("--- 1. Espada básica ---");
        Weapon sword = new Sword();
        sword.attack();

        System.out.println("-------------------------");

        // 2. Decoración simple: envuelve la espada en un decorador de fuego
        System.out.println("--- 2. Espada decorada con Fuego ---");
        Weapon fireSword = new FireDecorator(sword);
        fireSword.attack();

        System.out.println("-------------------------");

        // 3. Decoración múltiple en cadena: envuelve la espada de fuego en veneno
        System.out.println("--- 3. Espada decorada con Fuego Y Veneno ---");
        Weapon deadlySword = new PoisonDecorator(fireSword);
        deadlySword.attack();
    }
}
