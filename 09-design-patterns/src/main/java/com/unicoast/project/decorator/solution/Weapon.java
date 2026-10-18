package com.unicoast.project.decorator.solution;

/*
 * PATRÓN DE DISEÑO: DECORATOR (DECORADOR / ENVOLTORIO)
 * TIPO DE PATRÓN: ESTRUCTURAL
 *
 * ROL: COMPONENTE (COMPONENT)
 *
 * ¿Qué es este componente?
 * Es la interfaz común tanto para el objeto base ('Sword') como para todos los
 * decoradores que lo envuelven ('WeaponDecorator', 'FireDecorator', 'PoisonDecorator').
 *
 * Propósito:
 * Permite que los clientes traten a un arma básica y a un arma decorada exactamente
 * de la misma forma (polimorfismo y transparencia). El cliente solo sabe que interactúa
 * con una 'Weapon' y llama a su método attack().
 */
public interface Weapon {
    void attack();
}

