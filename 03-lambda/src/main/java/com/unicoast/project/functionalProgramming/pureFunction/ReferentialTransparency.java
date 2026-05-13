package com.unicoast.project.functionalProgramming.pureFunction;

/*
 * Principios de la PF: TRANSPARENCIA REFERENCIAL
 * Regla principal: "Mismos valores de entrada, mismos resultados."
 *
 * Es una propiedad matemática que nace de las funciones puras.
 * Al garantizar que para los mismos valores de entrada siempre hay los mismos resultados,
 * cualquier llamada a una función pura puede ser sustituida directamente por su 
 * valor de retorno resultante, sin alterar en absoluto el comportamiento del programa.
 * 
 * Ventaja: Permite razonar sobre el código mucho más fácil y permite a los compiladores
 * optimizar la ejecución (ej. calculando valores constantes en tiempo de compilación).
 */
public class ReferentialTransparency {
    // Esta es una función pura, por lo tanto, tiene Transparencia Referencial.
    public static int triple(int x){
        return x * 3;
    }

    public static void main(String[] args) {
        // Ejecución normal llamando a la función:
        int result = triple(3) + 2; // triple(3) siempre devuelve 9, por lo que result siempre será 11.
        System.out.println("El resultado es: " + result);

        /*
         * DEMOSTRACIÓN DE TRANSPARENCIA REFERENCIAL:
         * Como triple(3) SIEMPRE va a ser 9 y no tiene efectos secundarios,
         * se puede sustituir literalmente la llamada a la función por el número 9.
         * El comportamiento del programa es exactamente el mismo.
         */
        int result2 = 9 + 2;
        System.out.println("El resultado es: " + result2);
    }
}
