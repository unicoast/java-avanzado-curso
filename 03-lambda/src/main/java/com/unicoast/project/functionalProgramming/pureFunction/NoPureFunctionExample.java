package com.unicoast.project.functionalProgramming.pureFunction;

/*
 * Ejemplo de una Función IMPURA (No-Pura)
 * Una función es impura si rompe alguna de las dos reglas de oro de la Programación Funcional.
 * En este caso, rompe ambas:
 * 1. Tiene efectos secundarios (Side-effects): Modifica una variable externa ('counter').
 * 2. No es determinista: Si se pasa el mismo valor de entrada, puede devolver resultados diferentes.
 */
public class NoPureFunctionExample {

    // Variable de estado externo. Su existencia fuera de la función es lo que permite el efecto secundario.
    static int counter = 0;

    public static int incrementCounter(int valor) {
        counter += valor; // EFECTO SECUNDARIO: Modifica el estado global (la variable estática 'counter').
        return counter;   // NO DETERMINISTA: El valor devuelto depende del estado global, no solo de 'valor'.
    }

    public static void main(String[] args) {
        System.out.println("Resultado 1: " + incrementCounter(5)); // Imprime 5
        System.out.println("Resultado 2: " + incrementCounter(5)); // Imprime 10 (diferente resultado para la misma entrada)
    }
}
