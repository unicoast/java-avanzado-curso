package com.unicoast.project.example02;

import java.util.Optional;
import java.util.Scanner;

/*
 * MANEJO DE VALORES Y ESTRATEGIAS DE DEFAULT (orElse, orElseGet, orElseThrow)
 * En este ejemplo se muestra cómo reaccionar ante la ausencia de un valor,
 * comparando un enfoque híbrido/imperativo con un flujo 100% declarativo y funcional.
 */
public class Main {
    public static void main(String[] args) {
        /*
         * 1. Enfoque Híbrido / Imperativo (Comentado):
         * Aquí se mezcla el paradigma clásico con Optional de manera innecesaria.
         * Se crea un Optional vacío y luego se usa una condicional clásica 'if'
         * para rellenar la variable usando 'orElse()'.
         *
         * ¿Qué hace orElse(T other)?
         * Si el valor está presente, se retorna. Si está vacío, se obtiene el valor por defecto provisto.
         * *IMPORTANTE*: El valor dentro de orElse("...") se evalúa SIEMPRE, incluso si el Optional
         * tiene un valor en su interior. Si fuera una llamada a un método pesado, se degradaría el rendimiento.
         */
        /*
        Scanner scanner =  new Scanner(System.in);

        System.out.println("Ingrese su nombre: ");
        String name = scanner.nextLine();

        Optional<String> optionalName = Optional.empty();

        if (name.isEmpty()){
            name = optionalName.orElse("Invitado");
        }

        System.out.println("Hola " + name);
        */

        // 2. Enfoque 100% Declarativo y Funcional
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su nombre: ");
        String nameInput = scanner.nextLine();

        /*
         * Filtrado y preparación con filter():
         * Si la condición del filter se cumple (!name.isEmpty()), se mantiene el Optional con su valor.
         * Si no se cumple (cadena vacía), se realiza una transición segura hacia un Optional.empty().
         */
        Optional<String> optional = Optional.of(nameInput)
                .filter(name -> !name.isEmpty());

        /*
         * ¿Qué hace orElseGet(Supplier<? extends T> other)?
         * De forma similar a orElse(), pero acepta una Expresión Lambda (Supplier).
         * La gran ventaja radica en la evaluación perezosa (lazy evaluation): el código de la lambda
         * se ejecuta ÚNICAMENTE si el Optional está vacío.
         * Es ideal para llamadas a bases de datos o servicios externos que solo se requiere consultar en caso de ausencia.
         */
        String name = optional.orElseGet(() -> "Invitado");

        System.out.println("Hola " + name);

        /*
         * ¿Qué hace orElseThrow(Supplier<? extends X> exceptionSupplier)?
         * En lugar de retornar un valor por defecto, se lanza una excepción personalizada en caso de que el Optional esté vacío.
         * De este modo, se simplifica la validación de argumentos en servicios o controladores,
         * evitando múltiples condicionales del tipo 'if (valor == null) throw ...'.
         */
        String name2 = optional.orElseThrow(() -> new IllegalStateException("El nombre no puede estar vacío"));
        System.out.println("Nombre validado con éxito: " + name2);

        scanner.close();
    }
}

