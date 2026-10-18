package com.unicoast.project.decorator.problem;

/*
 * EL PROBLEMA (SIN DECORATOR):
 *
 * Se tiene una espada básica. Para agregarle efectos (como fuego o veneno)
 * usando herencia, es necesario crear una clase para cada combinación:
 * - FireSword para fuego
 * - PoisonSword para veneno
 * - FirePoisonSword para fuego y veneno juntos
 *
 * El problema es que cuantas más mejoras haya, más clases hay que crear,
 * y no se pueden agregar o quitar efectos al arma en plena partida.
 */
public class Sword {

    public void attack() {
        System.out.println("Ataque básico con espada.");
    }
}
