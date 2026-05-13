package com.unicoast.project.function;

import java.util.function.BiFunction;
import java.util.function.Function;

/*
 * FUNCTION (Función)
 * Es una interfaz funcional de Java (java.util.function) que representa una operación de TRANSFORMACIÓN:
 * 1. RECIBE un argumento de entrada (de tipo genérico T).
 * 2. DEVUELVE un resultado (de tipo genérico R).
 *
 * Es ideal para "mapear" o convertir datos de un tipo a otro (ej. de String a Integer).
 */
public class FunctionExample {
    public static void main(String[] args) {
        /*
         * FUNCTION: Recibe un String (T) y devuelve un Integer (R).
         * NOTA: "String::length" es una REFERENCIA A MÉTODO, que es la forma ultra-corta 
         * de escribir la expresión lambda: s -> s.length()
         */
        Function<String, Integer> stringLength = String::length;
        
        // Para ejecutar un Function, se usa su único método abstracto: 'apply()'
        int length = stringLength.apply("Programación");
        System.out.println("La longitud es: " + length);

        /*
         * BIFUNCTION: Exactamente igual, pero recibe DOS argumentos de entrada (T, U)
         * y devuelve un resultado (R).
         * Aquí T=Integer, U=Integer, y R=String.
         */
        BiFunction<Integer, Integer, String> sumToString = (a, b) -> "La suma es: " + (a + b);
        String result = sumToString.apply(5, 10);
        System.out.println(result);
    }
}
