package com.unicoast.project.builder.solution;

/*
 * IMPLEMENTACIÓN AUTOMÁTICA DEL PATRÓN BUILDER CON LOMBOK (@Builder)
 *
 * ¿Qué hace la anotación @Builder?
 * En tiempo de compilación, Project Lombok genera automáticamente:
 * 1. La clase estática interna 'CharacterBuilder'.
 * 2. Un método fluido por cada atributo (name, type, level, etc.).
 * 3. El método 'build()' que entrega la instancia lista de 'Character'.
 * 4. El método estático 'Character.builder()' para iniciar la construcción.
 *
 * Ventaja:
 * Resuelve el constructor sobrecargado de 'problem.GameCharacter' con una sola
 * anotación, eliminando todo el código repetitivo (boilerplate).
 */
import lombok.Builder;

@Builder
public class Character {
    private String name;
    private String type;
    private int level;
    private int strength;
    private int defense;
    private int magic;
    private int speed;
    private int agility;
    private int experience;

    public void showStats() {
        System.out.println("Personaje: " + name);
        System.out.println("Clase: " + type);
        System.out.println("Nivel: " + level);
        System.out.println("Fuerza: " + strength);
        System.out.println("Defensa: " + defense);
        System.out.println("Magia: " + magic);
        System.out.println("Velocidad: " + speed);
        System.out.println("Agilidad: " + agility);
        System.out.println("Experiencia: " + experience);
    }
}
