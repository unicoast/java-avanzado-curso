package com.unicoast.project.decorator.problem;

/*
 * Subclase para agregar fuego a la espada.
 * Problema: el efecto queda atado solo a esta espada y no se puede reutilizar
 * en otras armas como un arco o un hacha.
 */
public class FireSword extends Sword {

    @Override
    public void attack() {
        super.attack();
        System.out.println("Daño de fuego adicional.");
    }
}
