package com.unicoast.project.example04;

import java.util.List;
import java.util.Optional;

/*
 * INTEGRACIÓN DE STREAMS CON OPTIONAL (findFirst)
 * Diversas operaciones terminales de la API de Streams (tales como findFirst, findAny, max, min o reduce sin identidad)
 * retornan un Optional dado que la colección sobre la que se opera podría estar vacía.
 */
public class Main {
    public static void main(String[] args) {
        // Ejemplo 1: findFirst() sobre una colección con elementos
        List<String> names = List.of("Novak", "Rafael", "Roger", "Andy", "Stan");

        /*
         * findFirst() se utiliza para obtener el primer elemento que cumpla con el flujo del Stream.
         * Retorna un Optional<String> dado que la lista de origen podría estar vacía.
         */
        Optional<String> first = names.stream().findFirst();

        first.ifPresent(name -> System.out.println("El primero es: " + name));

        // Ejemplo 2: findFirst() sobre una colección vacía
        List<String> emptyList = List.of();

        /*
         * Al operar sobre una lista vacía, findFirst() no genera excepciones,
         * sino que se obtiene un Optional.empty() de manera segura.
         */
        Optional<String> firstEmpty = emptyList.stream().findFirst();

        System.out.println("¿Está presente en lista vacía? " + firstEmpty.isPresent());

        // Ejemplo 3: Uso de Record local + Búsqueda y valores por defecto (Fallbacks)
        record Product(String name, double price) {}

        List<Product> products = List.of(
                new Product("TV", 200.0),
                new Product("Netbook", 400.0)
        );

        /*
         * Se filtra el flujo buscando un producto cuyo nombre sea "TV" sin distinguir mayúsculas/minúsculas.
         * De este modo, findFirst() permite obtener el primer elemento que cumpla esta condición, el cual se encapsula en un Optional.
         */
        Optional<Product> maybeTV = products.stream()
                .filter(product -> product.name().equalsIgnoreCase("tv"))
                .findFirst();

        /*
         * orElse(...) se utiliza como red de seguridad (fallback) del sistema.
         * En caso de no encontrarse ningún producto con ese nombre, se instancia un producto genérico por defecto.
         */
        Product result = maybeTV.orElse(new Product("Genérico", 0.0));

        System.out.println("Producto procesado: " + result.name() + " con precio $" + result.price());
    }
}

