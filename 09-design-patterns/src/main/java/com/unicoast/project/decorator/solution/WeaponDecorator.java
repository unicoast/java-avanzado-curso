package com.unicoast.project.decorator.solution;

/*
 * PATRÓN DE DISEÑO: DECORATOR
 * TIPO DE PATRÓN: ESTRUCTURAL
 *
 * ROL: DECORADOR ABSTRACTO BASE (WeaponDecorator)
 *
 * ¿Por qué esta clase es abstracta?
 * 1. Sirve como molde base para los decoradores concretos (fuego, veneno, etc.).
 *    No tiene sentido instanciar un "decorador genérico", sino efectos concretos.
 * 2. Mantiene la referencia al arma que envuelve ('decoratedWeapon'),
 *    sea el arma básica o un arma ya decorada.
 */
public abstract class WeaponDecorator implements Weapon {

    protected Weapon decoratedWeapon;

    public WeaponDecorator(Weapon decoratedWeapon) {
        this.decoratedWeapon = decoratedWeapon;
    }

    /*
     * ¿Por qué implementar este método si la clase es abstracta?
     *
     * Al ser una clase abstracta, Java NO obliga a implementar el método
     * de la interfaz 'Weapon'. Se podría dejar sin implementar.
     *
     * Sin embargo, se implementa aquí a propósito para DELEGAR la llamada:
     * al ejecutar 'decoratedWeapon.attack()', define el comportamiento por defecto
     * de pasar la acción al objeto envuelto.
     *
     * Así, los decoradores hijos (FireDecorator, PoisonDecorator) solo tienen que
     * llamar a 'super.attack()' y luego agregar su propio daño, sin repetir la lógica
     * de delegación en cada uno.
     */
    @Override
    public void attack() {
        decoratedWeapon.attack();
    }
}



