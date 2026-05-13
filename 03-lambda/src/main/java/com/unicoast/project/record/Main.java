package com.unicoast.project.record;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * RECORDS + STREAMS: APLICACIÓN PRÁCTICA
 * Combina Records (DTOs inmutables) con Streams para transformaciones,
 * agrupaciones, reducciones y conversiones entre colecciones.
 */
public class Main {
    public static void main(String[] args) {
        /*
         * Record vs Clase Tradicional:
         * Product -> product.getName()    (getter clásico)
         * ProductDto -> productDto.name() (método de componente del Record)
         */

        List<ProductDto> products = List.of(
                new ProductDto("Notebook", 1200.0),
                new ProductDto("Mouse", 25.5),
                new ProductDto("Keyboard", 55.0),
                new ProductDto("Keyboard", 89.0)
        );

        /*
         * 1. Transformación encadenada: map() + map() + toList()
         * Primer map(): Crea un NUEVO ProductDto con descuento del 10% (Records son inmutables).
         * Segundo map(): Formatea cada ProductDto como String "nombre: $precio".
         * Resultado: [Notebook: $1080.00, Mouse: $22.95, Keyboard: $49.50, Keyboard: $80.10]
         */
        List<String> discountedProducts = products.stream()
                .map(productDto -> new ProductDto(productDto.name(), productDto.price() * 0.9))
                .map(productDto -> String.format("%s: $%.2f", productDto.name(), productDto.price()))
                .toList();

        System.out.println(discountedProducts);

        /*
         * 2. Agrupación con Collectors.groupingBy()
         * groupingBy() toma una lista y la SEPARA en grupos dentro de un Map.
         * Recibe una función que decide la CLAVE del grupo (aquí: "Caro" o "Barato").
         * Cada clave apunta a una lista con los elementos que cayeron en ese grupo.
         * Resultado: {Caro=[Notebook(1200), Keyboard(55), Keyboard(89)], Barato=[Mouse(25.5)]}
         */
        Map<String, List<ProductDto>> groupedByPriceRange = products.stream()
                .collect(Collectors.groupingBy(
                        productDto -> productDto.price() > 50.0 ? "Caro" : "Barato"
                ));

        /*
         * groupingBy() con segundo argumento (operación adicional por grupo):
         * En vez de agrupar en listas, aplica Collectors.counting() a cada grupo.
         * Resultado: {Caro=3, Barato=1}
         */
        Map<String, Long> countByRange = products.stream()
                .collect(Collectors.groupingBy(
                        productDto -> productDto.price() > 50.0 ? "Caro" : "Barato",
                        Collectors.counting()
                ));

        // System.out.println(groupedByPriceRange);
        // System.out.println(countByRange);

        /*
         * 3. Reducción matemática con reduce()
         * Transforma toda la colección en un ÚNICO valor. Recibe un valor inicial (0.0)
         * y un acumulador que suma cada precio al resultado parcial.
         *
         * Reducción (funcional) vs Acumulación (imperativa): mismo resultado,
         * pero la reducción es declarativa y sin variables mutables intermedias.
         * Resultado: 1369.5
         */
        Double total = products.stream()
                .map(productDto -> productDto.price())
                .reduce(0.0, (sum, price) -> sum + price);

        System.out.println("Precio total: " + total);

        /*
         * 4. Reducción a String con reduce()
         * reduce() no solo sirve para números; reduce cualquier tipo a un solo valor.
         * Aquí concatena todos los productos en un String único separado por " | ".
         * El if evita que el separador aparezca al inicio (cuando s1 es el valor inicial "").
         * Resultado: Notebook( $1200.0 ) | Mouse( $25.5 ) | Keyboard( $55.0 ) | Keyboard( $89.0 )
         */
        String productSummary = products.stream()
                .map(p -> p.name() + "( $" + p.price() + " )")
                .reduce("", (s1, s2) -> {
                    if (s1.isEmpty()) {
                        return s2;
                    } else {
                        return s1 + " | " + s2;
                    }
        });

        System.out.println(productSummary);

        /*
         * 5. Conversión List -> Set con Collectors.toSet()
         * Un Set descarta duplicados por definición. Si dos productos tienen el mismo precio,
         * solo aparece una vez. Útil para obtener valores únicos de una propiedad.
         * Resultado: [1200.0, 25.5, 89.0, 55.0] (sin orden garantizado)
         */
        Set<Double> uniquePrices = products.stream()
                .map(productDto -> productDto.price())
                .collect(Collectors.toSet());

        System.out.println(uniquePrices);

        /*
         * 6. Conversión List -> Map con Collectors.toMap()
         * toMap() transforma una lista en un Map (diccionario clave-valor).
         * Recibe 3 argumentos:
         * - 1ro: Función que extrae la CLAVE (el nombre del producto).
         * - 2do: Función que extrae el VALOR (el precio).
         * - 3ro: Qué hacer si hay claves repetidas. Aquí (oldValue, newValue) -> newValue
         *        significa: si "Keyboard" aparece dos veces, quedarse con el último precio (89.0).
         * Resultado: {Notebook=1200.0, Mouse=25.5, Keyboard=89.0}
         */
        Map<String, Double> productMap = products.stream()
                .collect(Collectors.toMap(
                        ProductDto::name,   // Referencia a método: equivale a p -> p.name()
                        ProductDto::price,  // Referencia a método: equivale a p -> p.price()
                        (oldValue, newValue) -> newValue
                ));

        System.out.println(productMap);

        /*
         * 7. Recorrido de Map con entrySet().stream()
         * Un Map no tiene .stream() directo. Para recorrerlo como Stream, se usa entrySet()
         * que devuelve un conjunto de "entradas" (pares clave-valor).
         * Cada entrada tiene getKey() (nombre) y getValue() (precio).
         * Una vez convertido a Stream, se pueden aplicar filter, map, etc. normalmente.
         * Resultado: [ProductDto[name=Notebook, price=1200.0], ProductDto[name=Keyboard, price=89.0]]
         */
        List<ProductDto> expensiveProducts = productMap.entrySet().stream()
                .filter(e -> e.getValue() > 50.0)
                .map(e -> new ProductDto(e.getKey(), e.getValue()))
                .toList();

        System.out.println(expensiveProducts);
    }
}
