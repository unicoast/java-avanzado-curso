package com.unicoast.project.functionalProgramming.pureFunction;

import java.util.function.Function;

/*
 * Ejemplo de una Función PURA
 * Una función pura es el pilar de la Programación Funcional y debe cumplir dos reglas inquebrantables:
 *
 * 1. DETERMINISMO (Principio PF: "Siempre devuelve el mismo resultado"):
 *    Dado el mismo argumento de entrada (ej. 10), SIEMPRE devolverá el mismo resultado (ej. 20).
 *
 * 2. SIN EFECTOS SECUNDARIOS (No Side-effects):
 *    No modifica variables externas, no escribe en bases de datos, ni interactúa con el mundo exterior. 
 *    El único propósito es tomar datos, procesarlos y devolver datos nuevos.
 *
 * Ventaja: Las funciones puras son increíblemente fáciles de testear (Unit Testing) y no causan problemas en concurrencia.
 */
public class PureFunction {

    /*
     * ENFOQUE IMPERATIVO (Tradicional) vs DECLARATIVO (Funcional):
     * - Imperativo: Describe el "CÓMO", dando instrucciones paso a paso (crear variables, usar bucles).
     * - Declarativo: Describe el "QUÉ" se desea lograr. Se delega el comportamiento al lenguaje para que gestione el flujo.
     */

    public static int multiplyByTwo(int number) {
        return number * 2;
    }

    public static void main(String[] args) {
        int result = multiplyByTwo(10);
        System.out.println("El resultado es: " + result);

        PureFunction pureFunction = new PureFunction();
        pureFunction.functionalProgramming(10);
    }

    /*
     * ENFOQUE DECLARATIVO (Programación Funcional en Java)
     * Aquí se usa la interfaz funcional estándar de Java 'Function<T, R>'.
     * T = Tipo del parámetro de entrada (Integer), R = Tipo del valor de retorno (Integer).
     */
    public void functionalProgramming(int number) {
        
        // Se almacena un COMPORTAMIENTO puro en una variable usando una expresión Lambda.
        // Es declarativo: "multiply es algo que recibe 'n' y retorna 'n * 2'".
        Function<Integer, Integer> multiply = n -> n * 2;

        // Para ejecutar ese comportamiento, se llama al único método abstracto de la interfaz Function: 'apply()'.
        // Se pasa el 'number', ejecuta la lambda y retorna el cálculo.
        Integer result = multiply.apply(number);

        System.out.println("El resultado es: " + result);
    }
}
