package com.unicoast.project.generics.queue;

// Clase Main: demuestra el uso de Queue<T> con Integer y String
public class Main {
    public static void main(String[] args) {
        // === Cola de números (FIFO) ===
        // Queue<Integer> -> cola que solo acepta enteros
        Queue<Integer> numbers = new Queue<>();

        // Encolar: los elementos entran por el final -> [10] -> [20] -> [30]
        numbers.enqueue(10);
        numbers.enqueue(20);
        numbers.enqueue(30);

        // Imprimir cola: muestra 10, 20, 30 (en orden de llegada)
        numbers.print();

        // peek() -> ve el primero (10) sin sacarlo
        System.out.println("Primer elemento de la fila: " + numbers.peek());
        // dequeue() -> saca el primero (10), la cola queda [20] -> [30]
        System.out.println("Primer elemento eliminado de la fila: " + numbers.dequeue());

        // Imprimir cola después de dequeue: muestra 20, 30
        numbers.print();

        // === Cola de nombres (mismo comportamiento, distinto tipo) ===
        // Gracias a los genéricos, reutilizamos la misma clase Queue con String
        Queue<String> names = new Queue<>();
        names.enqueue("Novak");   // Primero en la fila
        names.enqueue("Rafael");  // Segundo
        names.enqueue("Roger");   // Tercero (último en llegar)

        names.print();

        // peek() -> "Novak" (el primero que llegó)
        System.out.println("Primer elemento de la fila: " + names.peek());
        // dequeue() -> saca "Novak", la fila queda ["Rafael"] -> ["Roger"]
        System.out.println("Primer elemento eliminado de la fila: " + names.dequeue());

        names.print();
    }
}
