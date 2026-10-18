package com.unicoast.project.command.solution;

/*
 * PATRÓN DE DISEÑO: COMMAND (INTERFAZ COMANDO)
 * TIPO DE PATRÓN: DE COMPORTAMIENTO (BEHAVIORAL)
 *
 * Propósito:
 * Convierte una petición en un objeto con dos métodos clave:
 * - 'execute()': ejecuta la acción sobre el receptor ('Player').
 * - 'undo()': deshace o revierte la acción.
 */
public interface Command {

    /*
     * Ejecuta la acción encapsulada.
     */
    void execute();

    /*
     * Revierte la acción previamente ejecutada.
     */
    void undo();
}
