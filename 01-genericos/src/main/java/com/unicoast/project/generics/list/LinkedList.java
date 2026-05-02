package com.unicoast.project.generics.list;

// Clase genérica LinkedList: lista enlazada simple
// Analogía: Cadena de personas (nodos) enlazadas por referencias
/*
Cada nodo es una persona con dato y referencia a la siguiente. No anidadas, enlazadas por referencias.

En memoria: Persona A (data=10, next->B) -> Persona B (data=20, next->C) -> Persona C (data=30, next=null)

persona1.next = persona2 apunta a la referencia de persona2, no la mete dentro.

En LinkedList<T>: newNode = new Node<>(data) crea persona nueva; current.next = newNode conecta referencia.
*/
public class LinkedList<T> {
    // Cabeza: primer nodo (null si la lista está vacía)
    private Node<T> head;

    // Agregar al final
    public void add(T data){
        // Construir una nueva persona (nodo) con los datos
        Node<T> newNode = new Node<>(data);
        // Si no hay personas (lista vacía), esta es la primera persona (cabeza)
        if (head == null) {
            head = newNode;
        } else {
            // Empezar desde la primera persona para encontrar la última
            Node<T> current = head;
            // Caminar de persona en persona hasta la última (sin referencia siguiente)
            while (current.next != null) {
                // Ir a la siguiente persona
                current = current.next;
            }
            // Dar la referencia de la nueva persona a la última persona
            current.next = newNode;
        }
    }

    // Método para imprimir la lista (para debug)
    public void printList() {
        // Recorre desde la primera persona hasta el final
        Node<T> current = head;
        while (current != null) {
            System.out.print("Elemento: " + current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Método para actualizar: busca un dato y lo reemplaza por otro
    // Analogía: encontrar una persona y cambiar su contenido
    public void update(T oldData, T newData) {
        Node<T> current = head;
        // Camina por las personas hasta encontrar la que tiene el dato antiguo
        while (current != null) {
            if(current.data.equals(oldData)){
                current.data = newData; // Cambiar contenido de la persona
                return;
            }
            current = current.next;
        }
    }

    // Método para eliminar: busca un dato y saca la persona de la cadena
    // Analogía: encontrar una persona y desconectarla de la cadena
    public void delete(T data){
        if (head == null) return; // Lista vacía, nada que eliminar

        // Si el nodo a eliminar es la cabeza (primera persona)
        if (head.data.equals(data)) {
            head = head.next; // La siguiente persona se convierte en cabeza
            return;
        }

        // Buscar el nodo a eliminar y el anterior
        Node<T> current = head.next;
        Node<T> previous = head;

        // Caminar hasta encontrar el nodo o llegar al final
        while (current != null && !current.data.equals(data)) {
            previous = current;
            current = current.next;
        }

        // Si encontramos el nodo, desconectarlo
        if (current != null) {
            previous.next = current.next; // La persona anterior apunta a la siguiente, saltando la del medio
        }
    }
}
