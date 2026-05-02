package com.unicoast.project.generics.datastore;

import java.util.List;

// Clase Main: demuestra el uso de DataStore<T, K> con Users
public class Main {
    public static void main(String[] args) {
        // Crear un DataStore donde T=User y K=String (el ID del usuario es la clave)
        // Esto significa: almacén de Users indexados por String
        DataStore<User, String> userStore = new DataStore<>();

        try {
            System.out.println("Añadiendo usuarios...");

            // Agregar usuarios con sus claves (IDs)
            // add() retorna true si se agregó, false si la clave ya existía
            userStore.add("123", new User("123", "Nicolás"));
            userStore.add("1234", new User("1234", "Novak"));
            userStore.add("12345", new User("12345", "Roger"));
            // userStore.add(null, new User("12345", "Test")); // Lanzaría NullPointerException

            // find() busca por clave en O(1) usando el HashMap interno
            System.out.println("Encontrando usuario: " + userStore.find("123"));
            // remove() elimina de ambas estructuras (Map + List) y retorna el eliminado
            System.out.println("Eliminando usuario: " + userStore.remove("12345"));
        } catch (NullPointerException e) {
            // Captura si se pasa null como clave o item (Objects.requireNonNull)
            System.out.println(e.getMessage());
        }

        // getAll() retorna lista inmutable -> no se puede modificar desde afuera
        List<User> users = userStore.getAll();

        // Recorrer e imprimir los usuarios restantes
        for (User user : users) {
            System.out.println(user);
        }
    }
}
