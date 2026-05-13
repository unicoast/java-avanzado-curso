package com.unicoast.project.predicate;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/*
 * PREDICATE (Predicado)
 * A diferencia del Consumer (que no devuelve nada y genera efectos secundarios),
 * el Predicate es una función que:
 * 1. RECIBE un argumento de entrada (de tipo genérico T).
 * 2. SIEMPRE DEVUELVE un BOOLEAN (true o false).
 *
 * Casos de uso ideales: Validaciones de datos, comprobación de reglas de negocio, 
 * y especialmente para hacer FILTROS en la API de Streams.
 */
public class PredicateExample {
    public static void main(String[] args) {
        // Predicate simple: Recibe un Integer y evalúa una condición matemática.
        Predicate<Integer> isEven = x -> x % 2 == 0;
        
        // Para ejecutar un Predicate, se usa su único método abstracto: 'test()'
        boolean result = isEven.test(6);
        System.out.println("¿Es par? : " + result);

        /*
         * USO DE CUERPO EN LAMBDAS (Llaves {})
         * Si se requieren múltiples líneas, se deben utilizar llaves {}. 
         * A diferencia del Consumer, dado que el Predicate SÍ debe devolver un valor, 
         * es OBLIGATORIO utilizar la palabra reservada 'return' dentro de las llaves.
         * 
         * BIPREDICATE: Recibe dos argumentos y devuelve un boolean.
         */
        BiPredicate<String, Integer> checkLength = (str, len) -> {
            boolean isSameLength = str.length() == len;
            return isSameLength;
        };
        result = checkLength.test("Hola", 4);
        System.out.println("¿Es igual? : " + result);
    }
}
