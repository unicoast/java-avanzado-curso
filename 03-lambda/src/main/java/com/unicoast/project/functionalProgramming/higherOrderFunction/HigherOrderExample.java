package com.unicoast.project.functionalProgramming.higherOrderFunction;

/*
 * FUNCIONES DE ORDEN SUPERIOR (Higher-Order Functions)
 * En la Programación Funcional, una Función de Orden Superior es aquella que hace al menos una de estas dos cosas:
 * 1. Recibe una o más funciones como argumentos/parámetros.
 * 2. Devuelve una función como resultado.
 *
 * Esto es posible porque en este paradigma las funciones son "Ciudadanos de Primera Clase" 
 * (se tratan como si fueran cualquier otro valor o variable).
 */
public class HigherOrderExample {
    public static void main(String[] args) {
        // En lugar de pasar un simple dato, se pasa un COMPORTAMIENTO directamente como argumento.
        // Significa: "Aplica el comportamiento 'multiplicar' a los números 6 y 2".
        applyOperation(6, 2, (x, y) -> x * y);
        
        // La gran ventaja es que la función original es súper reutilizable:
        // applyOperation(6, 2, (x, y) -> x + y); // Ahora suma!
    }

    /*
     * ESTA ES LA FUNCIÓN DE ORDEN SUPERIOR:
     * ¿Por qué? Porque su tercer parámetro ('Operation') actúa como un receptor de funciones/lambdas.
     */
    static void applyOperation(int x, int y, Operation operation) {
        // Ejecuta la función (el comportamiento) recibida por parámetro.
        int result = operation.compute(x, y);
        System.out.println("El resultado de la operación es: " + result);
    }
}

// Interfaz Funcional estándar para poder recibir expresiones Lambda (comportamientos)
@FunctionalInterface
interface Operation {
    int compute(int a, int b);
}
