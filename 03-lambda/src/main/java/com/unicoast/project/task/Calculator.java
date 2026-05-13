package com.unicoast.project.task;

/*
 * EJERCICIO PRÁCTICO: Calculadora con Lambda
 * Ejemplo de cómo una función de orden superior (operateAndPrint) recibe
 * distintos COMPORTAMIENTOS como argumento, permitiendo reutilizar
 * el mismo método para suma, resta, multiplicación y división.
 */
public class Calculator {
    // Función de orden superior: recibe una Operation (lambda) y la ejecuta.
    public void operateAndPrint(int a, int b, Operation operation){
        int result = operation.operate(a,b);
        System.out.println("Resultado: " + result);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // Cada llamada pasa un comportamiento diferente al MISMO método.
        calculator.operateAndPrint(5, 5, (a, b) -> a + b );  // Suma -> 10
        calculator.operateAndPrint(10, 5, (a, b) -> a - b ); // Resta -> 5
        calculator.operateAndPrint(5, 5, (a, b) -> a * b );  // Multiplicación -> 25
        calculator.operateAndPrint(10, 5, (a, b) -> a / b ); // División -> 2
    }
}

// Interfaz funcional propia (misma idea que las de java.util.function, pero personalizada).
@FunctionalInterface
interface Operation{
    int operate(int a, int b);
}
