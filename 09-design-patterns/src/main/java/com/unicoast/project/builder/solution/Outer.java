package com.unicoast.project.builder.solution;

/*
 * EJEMPLO CONCEPTUAL: CLASES ANIDADAS ESTÁTICAS (STATIC NESTED CLASSES)
 *
 * Este archivo demuestra la base técnica que utiliza el patrón Builder:
 * una clase interna estática ('Inner') que se puede instanciar sin requerir
 * una instancia previa de la clase contenedora ('Outer').
 */
public class Outer {
    /*
     * ¿Por qué 'static'?
     * 1. Independencia: Permite instanciar 'Inner' directamente con 'new Outer.Inner()'
     *    sin necesidad de crear previamente una instancia de la clase contenedora ('Outer').
     * 2. Uso en el patrón Builder: El constructor necesita existir antes que el objeto
     *    final para poder configurarlo paso a paso, manteniendo a la vez una relación
     *    de pertenencia lógica con la clase que construye.
     */
    static class Inner {
        void show() {
            System.out.println("Soy una clase interna y estática");
        }
    }

    public static void main(String[] args) {
        Outer.Inner obj =  new Outer.Inner();
        obj.show();
    }
}
