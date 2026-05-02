package com.unicoast.project.generics;

public class Utility {
    // Se debe declarar el <> en un método si la clase no es genérica
    // Método genérico que imprime un valor de cualquier tipo T
    public static <T> void printItem(T value){
        System.out.println(value);
    }

    // Método genérico sobrecargado que imprime un valor T y una clave K, concatenados con un espacio
    public static <T, K> void printItem(T value, K key){
        System.out.println(value + " " + key);
    }
}
