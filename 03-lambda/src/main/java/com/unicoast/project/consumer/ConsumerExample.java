package com.unicoast.project.consumer;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/*
 * CONSUMER (Consumidor)
 * Es una interfaz funcional de Java (java.util.function) que representa una operación que:
 * 1. RECIBE un argumento de entrada (de tipo genérico T).
 * 2. NO DEVUELVE NADA (su tipo de retorno es 'void').
 *
 * NOTA sobre Funciones Puras: Dado que un Consumer no retorna nada, su única forma de
 * ser útil es generando un EFECTO SECUNDARIO (ej. imprimir en consola, escribir en un archivo,
 * guardar en base de datos). Son el destino final de los datos.
 */
public class ConsumerExample {
    public static void main(String[] args) {
        // Consumer simple: Recibe un String, lo transforma y lo imprime (efecto secundario).
        Consumer<String> printUpper = s -> System.out.println(s.toUpperCase());
        
        // Para ejecutar un Consumer, se usa su único método abstracto: 'accept()'
        printUpper.accept("Buenas tardes");

        /*
         * USO DE CUERPO EN LAMBDAS (Llaves {})
         * Si la lógica de la función requiere múltiples líneas, se puede utilizar un "cuerpo"
         * encerrando el código entre llaves {}. Al tratarse de un Consumer, simplemente 
         * ejecuta el proceso y termina; NO necesita la palabra reservada 'return'.
         *
         * BICONSUMER: Igual que el anterior, pero recibe DOS argumentos.
         */
        BiConsumer<String, Integer> repeat = (word, times) -> {
            for (int i = 0; i < times; i++) {
                System.out.println(word);
            }
        };

        repeat.accept("Hola", 3);
    }
}
