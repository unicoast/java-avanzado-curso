package com.unicoast.project.generics.datastore;

import java.util.*;

/*
Tarea: Implementación de un DataStore<T, K> Genérico

Objetivo: Implementar una estructura de datos genérica llamada DataStore<T, K>, que
permita almacenar, buscar y eliminar elementos de manera eficiente.

Requisitos:
1. Debe ser una clase genérica, donde T representa el tipo de los elementos y K representa
   la clave única de cada elemento.
2. Debe usar una lista (List<T>) y un mapa (Map<K, T>) internamente para optimizar la
   búsqueda y el almacenamiento.
3. Debe implementar los siguientes métodos:
   - void add(K key, T item) : Agrega un elemento si la clave no existe.
   - void remove(K key) : Elimina un elemento por su clave.
   - T find(K key) : Devuelve el elemento correspondiente a la clave o null si no existe.
   - List<T> getAll() : Retorna una lista con todos los elementos almacenados.
4. Crear una clase User con atributos id (String) y name (String), que se usará como
   ejemplo para probar el DataStore<T, K>.
5. Implementar una clase Main para probar la estructura, agregando, buscando y eliminando usuarios.
*/

// Clase genérica DataStore: almacén de datos con acceso por clave y por lista
// Usa DOS parámetros de tipo: T (tipo del dato) y K (tipo de la clave)
// Analogía: Un almacén donde cada producto (T) tiene una etiqueta con código (K)
/*
Combina dos estructuras internas:
  - List<T>   -> mantiene el orden de inserción (como estantes en orden)
  - Map<K, T> -> permite búsqueda rápida por clave (como un catálogo indexado)

Ejemplo: DataStore<User, String> -> almacena Users identificados por String (ID)

¿Por qué dos estructuras? Cada una tiene su fortaleza:
  - List: recorrer todos los elementos en orden -> getAll()
  - Map:  buscar/eliminar un elemento específico en O(1) -> find(), remove()

Las claves K deben implementar hashCode() y equals() correctamente
(String, Integer, Long ya lo hacen por defecto).
*/
public class DataStore<T, K> {
    // Lista interna: mantiene los datos en orden de inserción
    private final List<T> dataList;
    // Mapa interno: permite acceso directo por clave (clave -> dato)
    private final Map<K, T> dataMap;

    // Constructor: inicializa ambas estructuras vacías
    public DataStore() {
        this.dataList = new ArrayList<>();
        this.dataMap = new HashMap<>();
    }

    // Agregar un dato con su clave al almacén
    // Retorna true si se agregó, false si la clave ya existía (no permite duplicados)
    public boolean add(K key, T item){
        // Validación: ni la clave ni el item pueden ser null (falla rápido con mensaje claro)
        Objects.requireNonNull(key, "La clave no puede ser nula");
        Objects.requireNonNull(item, "El item no puede ser nulo");

        // Si la clave ya existe en el catálogo, rechazar (evita duplicados)
        if(dataMap.containsKey(key)) return false;

        // Agregar a ambas estructuras para mantenerlas sincronizadas
        dataList.add(item);     // Al estante (orden)
        dataMap.put(key, item); // Al catálogo (acceso rápido)

        return true;
    }

    // Eliminar un dato por su clave
    // Retorna el dato eliminado, o null si la clave no existía
    public T remove(K key){
        Objects.requireNonNull(key, "La clave no puede ser nula");

        // Primero eliminar del mapa (retorna el valor eliminado o null)
        T removedItem = dataMap.remove(key);
        if(removedItem != null){
            // Si existía, también sacarlo de la lista para mantener sincronía
            dataList.remove(removedItem);
        }
        return removedItem;
    }

    // Buscar un dato por su clave -> acceso O(1) gracias al HashMap
    public T find(K key){
        Objects.requireNonNull(key, "La clave no puede ser nula");
        return dataMap.get(key); // Retorna el dato o null si no existe
    }

    // Obtener todos los datos en orden de inserción
    // Retorna una lista NO modificable (protege los datos internos)
    // Collections.unmodifiableList() -> si alguien intenta .add() o .remove() lanza excepción
    public List<T> getAll(){
        return Collections.unmodifiableList(dataList);
    }
}
