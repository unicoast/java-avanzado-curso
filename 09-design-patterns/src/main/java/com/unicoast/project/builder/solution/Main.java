package com.unicoast.project.builder.solution;

/*
 * PATRÓN DE DISEÑO: BUILDER (CONSTRUCTOR)
 * TIPO DE PATRÓN: CREACIONAL
 *
 * ¿Qué es?
 * Es un patrón que permite construir objetos complejos paso a paso, separando
 * la configuración de sus atributos de la representación final del objeto.
 *
 * ¿Qué problema soluciona?
 * Elimina el anti-patrón del constructor telescópico (constructores sobrecargados con muchos
 * parámetros confusos) y evita tener que pasar valores nulos o ceros para campos no deseados.
 *
 * ¿Cómo lo soluciona?
 * Muestra dos alternativas equivalentes:
 * 1. Implementación manual ('GameCharacter.Builder'): clase interna estática con métodos fluidos.
 * 2. Implementación con Lombok ('Character'): la anotación '@Builder' autogenera el Builder
 *    en tiempo de compilación sin escribir código repetitivo.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Construcción manual con Builder propio (GameCharacter)
        GameCharacter mage = new GameCharacter
                .Builder()
                .withName("Gandalf")
                .withType("Mago")
                .build();

        mage.showStats();

        System.out.println("-------------------------");

        // 2. Construcción automática con Lombok (@Builder en Character)
        Character character = new Character.CharacterBuilder().name("Nicolás").build();
        character.showStats();
    }
}
