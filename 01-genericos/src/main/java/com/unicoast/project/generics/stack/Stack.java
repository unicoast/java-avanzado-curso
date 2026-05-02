package com.unicoast.project.generics.stack;

import java.util.ArrayList;
import java.util.List;

// Clase genérica Stack (Pila): estructura de datos LIFO (Last In, First Out)
// Analogía: Pila de platos -> el último plato que pones es el primero que sacas
/*
Principio LIFO: el último elemento agregado es el primero en ser removido.
Es el OPUESTO a la Cola (FIFO).

Operaciones principales:
  - push(T) -> poner un plato encima de la pila (agregar arriba)
  - pop()   -> sacar el plato de arriba (remover el último que se puso)
  - peek()  -> mirar qué plato está arriba sin sacarlo

Internamente usa ArrayList porque:
  - add() al final es O(1) -> agregar arriba
  - removeLast() es O(1) -> sacar de arriba
  - Ambas operaciones trabajan con el FINAL del ArrayList (la parte de arriba de la pila)

Visualización:
  ARRIBA ->  [30]  <- peek/pop actúan aquí (el último que entró)
              [20]
  ABAJO  ->  [10]  <- el primero que entró, push agrega encima de todo

Usos comunes: Ctrl+Z (deshacer), historial del navegador (botón atrás),
evaluación de expresiones, llamadas a funciones (call stack), DFS en grafos.
*/
public class Stack<T> {
    // Estructura interna: ArrayList que simula la pila
    // El FINAL del ArrayList representa la parte de ARRIBA de la pila
    private final List<T> elements = new ArrayList<>();

    // Apilar: agregar un elemento arriba de todo (final del ArrayList)
    public void push(T element) {
        elements.add(element); // O(1) -> agrega al final
        System.out.println("Elemento " + element + " agregado a la pila.");
    }

    // Verificar si la pila está vacía
    public boolean isEmpty(){
        return elements.isEmpty();
    }

    // Espiar: ver el elemento de ARRIBA sin sacarlo
    // Lanza excepción si la pila está vacía (no hay platos)
    public T peek(){
        if(isEmpty()){
            throw new IllegalStateException("La pila está vacía.");
        }

//        return elements.get(elements.size() - 1); // Forma clásica: último índice
        return elements.getLast(); // Java 21+: método más legible para obtener el último
    }

    // Desapilar: sacar y retornar el elemento de ARRIBA (el último que se agregó)
    // Lanza excepción si la pila está vacía
    public T pop(){
        if(isEmpty()){
            throw new IllegalStateException("La pila está vacía.");
        }

//        return elements.remove(elements.size() - 1); // Forma clásica
        return elements.removeLast(); // Java 21+: remueve y retorna el último elemento
    }

    // Imprimir todos los elementos (de abajo hacia arriba)
    public void print(){
        for (Object o: elements){
            System.out.println(o);
        }
    }
}
