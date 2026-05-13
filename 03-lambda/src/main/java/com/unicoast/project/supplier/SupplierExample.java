package com.unicoast.project.supplier;

import java.util.function.Supplier;

/*
 * SUPPLIER (Proveedor / Fábrica)
 * A diferencia de todas las demás interfaces funcionales, el Supplier:
 * 1. NO RECIBE NINGÚN ARGUMENTO (los paréntesis siempre están vacíos '() ->').
 * 2. SIEMPRE DEVUELVE un resultado (de tipo genérico T).
 *
 * Es ideal para crear "Fábricas" de objetos (Factory pattern), generar valores aleatorios,
 * o inicializar datos pesados solo cuando realmente se necesitan (Lazy initialization).
 */
public class SupplierExample {
    public static void main(String[] args) {
        // SUPPLIER: No recibe nada, pero cada vez que se ejecute "fabricará" un nuevo Person.
        // NOTA: Esto también podría escribirse usando referencia a método: Person::new
        Supplier<Person> personFactory = () -> new Person();
        
        // Para ejecutar un Supplier y obtener el objeto que fabrica, se usa el método: 'get()'
        Person p1 = personFactory.get();
        p1.setName("Nicolás");
        
        System.out.println("Persona generada por el Supplier: " + p1.getName());
    }
}
