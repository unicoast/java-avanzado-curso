package com.unicoast.project.example01;

import java.util.Optional;

/*
 * INTRODUCCIÓN A OPTIONAL (Java 8)
 * ¿Qué es Optional? Es un contenedor que puede o no contener un valor no nulo.
 * Diseñado principalmente para ser utilizado como tipo de retorno de métodos
 * donde la ausencia de un valor es un resultado posible y común.
 * Su objetivo principal es evitar el temido NullPointerException (NPE) y
 * hacer que las APIs sean más expresivas y seguras de consumir.
 */
public class Main {
    public static void main(String[] args) {
        /*
         * 1. Creación con Optional.of(value)
         * Se utiliza cuando existe certeza absoluta de que el valor NO es nulo.
         * Si se intenta pasar un valor nulo a Optional.of(), se lanza un NullPointerException de inmediato.
         */
        Optional<String> name = Optional.of("Nicolás");

        /*
         * 2. Comprobación y Extracción Clásica (Estilo Imperativo):
         * isPresent() -> Retorna true en caso de que el contenedor tenga un valor.
         * get()       -> Extracción del valor. ¡Ojo! Si el Optional está vacío, se produce un NoSuchElementException.
         *                Por lo tanto, es necesario verificar con isPresent() antes de proceder con get().
         */
        if (name.isPresent()) {
            System.out.println("El nombre está presente: " + name.get());
        }

        String value = null;

        /*
         * 3. Creación con Optional.ofNullable(value)
         * Se utiliza cuando el valor puede ser nulo o no (por ejemplo, datos que vienen de bases de datos, APIs o entrada de usuario).
         * Si el valor es nulo, se obtiene un Optional.empty() (contenedor vacío) en lugar de producirse una excepción.
         */
        Optional<String> nick = Optional.ofNullable(value);

        /*
         * 4. Creación con Optional.empty()
         * Creación explícita de un contenedor vacío que representa la ausencia de valor.
         */
        Optional<String> empty = Optional.empty();

        if (nick.isPresent()) {
            System.out.println("El nick está presente: " + nick.get());
        } else {
            System.out.println("El nick no está presente (es un Optional vacío)");
        }

        if (empty.isPresent()) {
            System.out.println("El empty está presente: " + empty.get());
        } else {
            System.out.println("El empty no está presente");
        }

        /*
         * 5. Enfoque Funcional con ifPresent(Consumer)
         * Evita el uso manual de estructuras condicionales como 'if-else' e 'isPresent()'.
         * Acepta un Consumer (expresión lambda) que se ejecuta ÚNICAMENTE si el valor está presente.
         * Si el Optional está vacío, la instrucción se omite de forma segura.
         */
        Optional<String> greeting = Optional.of("Hola Mundo");

        greeting.ifPresent(message -> System.out.println("Soy Nicolás: " + message));
    }
}

