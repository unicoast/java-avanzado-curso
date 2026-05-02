package com.unicoast.project.generics.stack;

// Clase Main: demuestra el uso de Stack<T> con Integer
public class Main {
    public static void main(String[] args) {
        // Crear una pila de enteros -> Stack<Integer>
        Stack<Integer> integerStack = new Stack<>();

        // push: apilar elementos -> [1] abajo, [2] medio, [3] arriba
        integerStack.push(1); // Pila: [1]
        integerStack.push(2); // Pila: [1, 2]
        integerStack.push(3); // Pila: [1, 2, 3] <- 3 está arriba

        // Stack<String> names = new Stack<>();
        // names.push("Novak");
        // names.push("Rafael");
        // names.push("Roger");

        // Imprimir: muestra 1, 2, 3 (de abajo hacia arriba)
        integerStack.print();

        // peek() -> 3 (el de arriba, el último que se agregó), NO lo saca
        System.out.println("Elemento en la cima: " + integerStack.peek());

        // pop() -> saca el 3 (LIFO: último en entrar, primero en salir)
        // La pila queda: [1, 2]
        System.out.println("Elemento eliminado: " + integerStack.pop());

        // Imprimir después del pop: muestra 1, 2
        integerStack.print();
    }
}
