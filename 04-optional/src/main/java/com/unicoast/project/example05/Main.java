package com.unicoast.project.example05;

import java.util.List;
import java.util.Optional;

/*
 * MÉTODOS AVANZADOS Y FLUJOS CON OPTIONAL (Java 9+)
 * Se analiza el método ifPresentOrElse() y la forma de desempaquetar y procesar colecciones
 * que contienen elementos del tipo Optional<T> usando Streams de manera moderna.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Uso de ifPresentOrElse(Consumer, Runnable) - Introducido en Java 9
        String maybeName = null;

        /*
         * ifPresentOrElse() permite definir dos flujos alternativos en una única expresión declarativa:
         * - 1er argumento (Consumer): Se ejecuta si el valor está presente (recibe el valor por parámetro).
         * - 2do argumento (Runnable): Se ejecuta si el valor está ausente (no recibe parámetros).
         * De este modo, se reemplaza por completo la clásica estructura imperativa 'if (opt.isPresent()) { ... } else { ... }'.
         */
        Optional.ofNullable(maybeName)
                .ifPresentOrElse(
                        name -> System.out.println("El nombre es: " + name),
                        () -> System.out.println("No se proporcionó un nombre")
                );

        // 2. Procesamiento de colecciones de Optionals
        List<Optional<String>> optionals = List.of(
                Optional.of("Hola"),
                Optional.empty(),
                Optional.of("Chau")
        );

        /*
         * Enfoque A: Clásico (Java 8)
         * - filter(Optional::isPresent): Filtrado para mantener únicamente los contenedores que contienen un valor.
         * - map(Optional::get): Extracción del valor String interno para continuar con la impresión de los textos.
         */
        System.out.println("--- Enfoque A: filter + map (Java 8) ---");
        optionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);

        /*
         * Enfoque B: Moderno con flatMap y Optional::stream (Java 9+)
         * - Optional::stream: Permite la conversión de un Optional en un Stream de un elemento (si está presente)
         *   o de cero elementos (si está vacío).
         * - flatMap(): Realiza la unión de todos estos sub-streams en un único Stream unificado de Strings.
         * Representa una alternativa sumamente compacta y elegante para filtrar y desempaquetar en un solo paso.
         */
        System.out.println("--- Enfoque B: flatMap con Optional::stream (Java 9+) ---");
        optionals.stream()
                .flatMap(Optional::stream)
                .forEach(System.out::println);
    }
}

