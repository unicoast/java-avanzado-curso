package com.unicoast.project.generics.queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;

// Clase genérica Queue (Cola): estructura de datos FIFO (First In, First Out)
// Analogía: Fila de personas en un banco -> el primero que llega es el primero que sale
/*
Principio FIFO: el primer elemento agregado es el primero en ser removido.

Operaciones principales:
  - enqueue(T) -> agregar al final de la fila (como una persona que llega y se pone al final)
  - dequeue()  -> sacar del frente de la fila (el primero que llegó, sale primero)
  - peek()     -> ver quién es el primero sin sacarlo (espiar el frente)

Internamente usa java.util.LinkedList porque:
  - addLast() y removeFirst() son O(1) en LinkedList (operaciones en los extremos)
  - Un ArrayList tendría removeFirst() en O(n) porque recorre todos los elementos

Visualización:
  FRENTE -> [10] -> [20] -> [30] <- FINAL
  peek/dequeue actúan aquí ^       ^ enqueue agrega aquí

Usos comunes: colas de impresión, BFS (búsqueda en grafos), atención al cliente,
procesamiento de tareas en orden de llegada.
*/
public class Queue<T> {
    // Estructura interna: LinkedList de Java (eficiente para agregar/quitar en extremos)
    private final LinkedList<T> elements = new LinkedList<>();

    // Encolar: agregar un elemento al FINAL de la fila
    public void enqueue(T element){
        elements.addLast(element); // O(1) -> agrega al final de la LinkedList
        System.out.println("Elemento " + element + " agregado a la cola");
    }

    // Espiar: ver el primer elemento SIN sacarlo de la fila
    // Lanza excepción si la cola está vacía (no hay nadie en la fila)
    public T peek(){
        if(isEmpty()){
            throw new NoSuchElementException("La cola está vacía");
        }
        return elements.getFirst(); // O(1) -> accede al primer nodo directamente
    }

    // Desencolar: sacar y retornar el PRIMER elemento (el que llegó primero)
    // Lanza excepción si la cola está vacía
    public T dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException("La cola está vacía");
        }
        return elements.removeFirst(); // O(1) -> remueve el primer nodo
    }

    // Verificar si la cola está vacía
    public boolean isEmpty(){
        return elements.isEmpty();
    }

    // Imprimir todos los elementos de la cola (del frente al final)
    public void print(){
        for (Object o: elements){
            System.out.println(o);
        }
    }
}
