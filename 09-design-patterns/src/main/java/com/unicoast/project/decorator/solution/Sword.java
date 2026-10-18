package com.unicoast.project.decorator.solution;

/*
 * PATRÓN DE DISEÑO: DECORATOR
 *
 * ROL: COMPONENTE CONCRETO (CONCRETE COMPONENT)
 *
 * ¿Qué representa esta clase?
 * Es la implementación base del componente 'Weapon'. Proporciona el comportamiento
 * por defecto o núcleo que puede ser envuelto y enriquecido por uno o más decoradores.
 *
 * Características clave:
 * 1. Implementa la interfaz 'Weapon'.
 * 2. No tiene conocimiento alguno de los decoradores ('FireDecorator', 'PoisonDecorator'),
 *    manteniendo una alta cohesión y bajo acoplamiento.
 * 3. Ejecuta el ataque básico que constituye el núcleo de la cadena de llamadas.
 */
public class Sword implements Weapon {

    @Override
    public void attack() {
        System.out.println("Ataque básico con espada.");
    }
}
