package com.unicoast.project.builder.problem;

/*
 * EL PROBLEMA (SIN BUILDER) - CONSTRUCTOR TELESCÓPICO:
 *
 * La clase 'GameCharacter' tiene muchos atributos (nombre, clase, nivel, fuerza,
 * defensa, magia, velocidad, agilidad, experiencia).
 *
 * El problema de este diseño:
 * 1. Constructor sobrecargado: Obliga a pasar 9 parámetros en un orden estricto.
 * 2. Propenso a errores: Es muy fácil confundir el orden de parámetros del mismo tipo
 *    (por ejemplo, intercambiar fuerza con defensa o velocidad con agilidad).
 * 3. Parámetros innecesarios: Si un personaje no usa magia (como un guerrero),
 *    igual obliga a pasarle un 0 o null en ese campo.
 */
public class GameCharacter {
    private String name;
    private String type;
    private int level;
    private int strength;
    private int defense;
    private int magic;
    private int speed;
    private int agility;
    private int experience;

    public GameCharacter(String name, String type, int level, int strength, int defense,
                         int magic, int speed, int agility, int experience) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.strength = strength;
        this.defense = defense;
        this.magic = magic;
        this.speed = speed;
        this.agility = agility;
        this.experience = experience;
    }

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
