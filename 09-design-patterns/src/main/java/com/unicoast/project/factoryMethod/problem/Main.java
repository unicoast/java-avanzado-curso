package com.unicoast.project.factoryMethod.problem;

/*
 * DEMOSTRACIÓN DEL PROBLEMA:
 *
 * Crea enemigos con 'new Enemy(...)', pero todos atacan igual ("ataca ferozmente")
 * porque no hay clases ni fábricas que los diferencien.
 */
public class Main {
    public static void main(String[] args) {
        // Instanciación directa y manual con cadenas de texto
        Enemy goblin = new Enemy("Esqueleto");
        Enemy troll = new Enemy("Zombi");

        // Ejecución genérica: no hay comportamientos diferenciados
        goblin.attack(); // Salida: Esqueleto ataca ferozmente.
        troll.attack();  // Salida: Zombi ataca ferozmente.
    }
}
