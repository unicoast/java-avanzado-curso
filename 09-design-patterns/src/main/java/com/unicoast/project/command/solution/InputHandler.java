package com.unicoast.project.command.solution;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * PATRÓN COMMAND: INVOCADOR (INVOKER)
 *
 * Propósito:
 * Es responsable de iniciar las solicitudes. Mantiene una asociación configurable
 * entre entradas del usuario (teclas/comandos) y objetos 'Command'.
 *
 * Mecánica y Beneficios:
 * 1. Mapeo Dinámico ('bind'): Asocia cualquier tecla a un comando en tiempo de ejecución,
 *    permitiendo reconfigurar controles sin modificar clases de lógica.
 * 2. Desacoplamiento: No conoce qué hace el receptor ni cómo se ejecuta; solo llama a 'command.execute()'.
 * 3. Soporte para Deshacer (Undo): Almacena los comandos ejecutados en una pila ('Stack<Command>')
 *    para revertirlos en orden inverso con 'undoLastCommand()'.
 */
public class InputHandler {
    private Map<String, Command> commands = new HashMap<>();
    private Stack<Command> history = new Stack<>();

    /*
     * Asocia una tecla o identificador a un comando específico.
     *
     * @param key Identificador de entrada (ej: "A", "D").
     * @param command Objeto Command a asociar.
     */
    public void bind(String key, Command command) {
        commands.put(key, command);
    }

    /*
     * Procesa la entrada del usuario, ejecuta el comando asociado y lo apila en el historial.
     *
     * @param input Tecla o comando ingresado por el usuario.
     */
    public void handleInput(String input) {
        Command command = commands.get(input);

        if (command != null) {
            command.execute();
            history.push(command);
        } else {
            System.out.println("Comando no reconocido.");
        }
    }

    /*
     * Deshace la última acción ejecutada extrayéndola de la pila de historial.
     */
    public void undoLastCommand() {
        if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            System.out.println("InputHandler -> Deshaciendo acción: " + lastCommand.getClass().getSimpleName());
            lastCommand.undo();
        } else {
            System.out.println("Historial vacío, no hay acciones para deshacer.");
        }
    }
}
