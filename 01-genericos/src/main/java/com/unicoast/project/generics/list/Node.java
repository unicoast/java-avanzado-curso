package com.unicoast.project.generics.list;

// Clase Node: representa un nodo en la lista enlazada
// Analogía: Una persona con dato y referencia a la siguiente persona
public class Node<T> {
    // Datos almacenados en esta persona (genérico T)
    T data;
    // Referencia a la siguiente persona (null si es la última persona)
    Node<T> next;

    // Constructor: crea una persona con datos, referencia inicia en null
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}
