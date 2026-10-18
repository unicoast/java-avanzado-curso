package com.unicoast.project.strategy.solution.strategies;

/*
 * PATRÓN STRATEGY (GOF): CONTRATO DE ESTRATEGIA (STRATEGY INTERFACE)
 *
 * Propósito:
 * Define una interfaz común para todos los algoritmos de ataque soportados.
 * El contexto ('GameCharacter') utiliza esta interfaz para invocar el algoritmo
 * definido por una 'Estrategia Concreta' (Concrete Strategy).
 *
 * Principios de Diseño Aplicados:
 * 1. Principio de Inversión de Dependencias (DIP - Dependency Inversion Principle):
 *    Los módulos de alto nivel (personajes) no dependen de módulos de bajo nivel (ataques específicos),
 *    sino que ambos dependen de esta abstracción ('AttackStrategy').
 * 2. Principio Abierto/Cerrado (OCP - Open/Closed Principle):
 *    El sistema queda abierto a la extensión (se pueden crear nuevas estrategias de ataque
 *    creando clases que implementen esta interfaz) y cerrado a la modificación (no es necesario
 *    tocar las clases de personajes existentes).
 * 3. Interfaz Funcional (Java 8+):
 *    Al poseer un único método abstracto (SAM - Single Abstract Method), esta interfaz
 *    puede ser implementada directamente mediante expresiones lambda o method references
 *    (técnica aprendida en la sección 03-lambda), otorgando máxima flexibilidad en tiempo de ejecución.
 */
@FunctionalInterface
public interface AttackStrategy {

    /*
     * Ejecuta el algoritmo de ataque para el personaje especificado.
     *
     * @param characterName Nombre del personaje que ejecuta el ataque.
     */
    void attack(String characterName);
}
