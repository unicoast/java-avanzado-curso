package com.unicoast.project.generics.list;

public class Main {
    public static void main(String[] args) {
        // Crear una lista enlazada de Strings (personas)
        LinkedList<String> listaNombres = new LinkedList<>();

        // Agregar personas a la lista
        listaNombres.add("Juan");
        listaNombres.add("María");
        listaNombres.add("Ana");
        listaNombres.add("Ale");

        // Imprimir la lista: Juan -> María -> Ana -> Ale -> null
        System.out.println("Lista inicial:");
        listaNombres.printList();

        // Actualizar: cambiar "Ana" por "Carla"
        System.out.println("\nActualizar Ana por Carla:");
        listaNombres.update("Ana", "Carla");
        listaNombres.printList();

        // Eliminar: sacar "María" de la cadena
        System.out.println("\nEliminar María:");
        listaNombres.delete("María");
        listaNombres.printList();

        // Eliminar la primera persona (cabeza)
        System.out.println("\nEliminar Juan (cabeza):");
        listaNombres.delete("Juan");
        listaNombres.printList();

        System.out.println("\n--- Prueba con números ---");
        // Crear una lista enlazada de Integers
        LinkedList<Integer> listaNumeros = new LinkedList<>();

        // Agregar números
        listaNumeros.add(10);
        listaNumeros.add(20);
        listaNumeros.add(30);
        listaNumeros.add(40);

        System.out.println("Lista de números:");
        listaNumeros.printList();

        // Actualizar número
        System.out.println("\nActualizar 30 por 35:");
        listaNumeros.update(30, 35);
        listaNumeros.printList();

        // Eliminar número
        System.out.println("\nEliminar 20:");
        listaNumeros.delete(20);
        listaNumeros.printList();
    }
}

