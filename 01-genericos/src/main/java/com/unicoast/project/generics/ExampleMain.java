package com.unicoast.project.generics;

public class ExampleMain {
    public static void main(String[] args) {
        // Object desde la versión 1.0
        Object number = Integer.valueOf(10);
        number = "Hola!";

        // var (disponible desde versión 10)
        // solo funciona para variables locales, no funciona con parámetros ni con campos, atributos de clases o retornos de métodos
        var name = "Nicolás";

        System.out.println(number);
        System.out.println(name);
    }
}
