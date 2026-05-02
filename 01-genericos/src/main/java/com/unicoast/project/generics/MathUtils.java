package com.unicoast.project.generics;

// Clase utilitaria para operaciones matemáticas con genéricos
public class MathUtils {
    // Método genérico que suma dos valores numéricos.
    // T debe extender Number, permitiendo tipos como Integer, Double, etc.
    // Convierte ambos valores a double y los suma
    public static <T extends Number> double sum(T a, T b){
        return a.doubleValue() + b.doubleValue();
    }
}
