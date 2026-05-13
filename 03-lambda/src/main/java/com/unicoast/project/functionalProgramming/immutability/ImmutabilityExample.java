package com.unicoast.project.functionalProgramming.immutability;

/*
 * INMUTABILIDAD (Immutability)
 * En la Programación Funcional, los datos son inmutables. 
 * Una vez que se crea un valor u objeto, su estado interno NO puede ser modificado.
 * Si se necesita hacer un cambio (por ejemplo, actualizar la edad de un usuario), 
 * en lugar de modificar el objeto original, se crea un NUEVO objeto con el dato actualizado.
 *
 * Ventajas: 
 * 1. Evita bugs por efectos secundarios (side-effects).
 * 2. Es 100% seguro para trabajar con múltiples hilos (concurrencia/Thread-safe).
 */
public class ImmutabilityExample {

    public static void main(String[] args) {
        // Objeto inmutable
        Person originalPerson = new Person("Nicolás", 25);
        
        // ENFOQUE IMPERATIVO (Mutable - INCORRECTO en Funcional):
        // originalPerson.setAge(26); 
        
        // ENFOQUE FUNCIONAL (Inmutable):
        // El método no modifica el objeto original, sino que devuelve uno nuevo.
        Person olderPerson = originalPerson.withAge(26);

        System.out.println("Persona Original: " + originalPerson.getName() + " - " + originalPerson.getAge() + " años.");
        System.out.println("Persona Nueva   : " + olderPerson.getName() + " - " + olderPerson.getAge() + " años.");
        
        // Comprobación de que el objeto original sigue intacto y son referencias distintas
        System.out.println("¿Son el mismo objeto en memoria? " + (originalPerson == olderPerson));
    }
}

/*
 * ¿Cómo crear una clase inmutable en Java clásico?
 * 1. Clase 'final' para evitar herencia que altere el comportamiento.
 * 2. Variables 'private final' para que se asignen una sola vez en el constructor.
 * 3. Cero 'setters'.
 */
final class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /*
     * Este es el truco de la inmutabilidad:
     * En lugar de un 'setAge', se tiene un método que devuelve una NUEVA instancia
     * conservando los datos anteriores y aplicando el cambio deseado.
     */
    public Person withAge(int newAge) {
        return new Person(this.name, newAge);
    }
}
