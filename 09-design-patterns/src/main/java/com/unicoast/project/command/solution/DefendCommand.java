package com.unicoast.project.command.solution;

/*
 * PATRÓN COMMAND: COMANDO CONCRETO (CONCRETE COMMAND) - DEFENSA
 *
 * Encapsula la solicitud de defensa, delegando en 'player.defend()' al ejecutarse
 * y definiendo la reversión en 'undo()'.
 */
public class DefendCommand implements Command {
    private Player player;

    public DefendCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.defend();
    }

    @Override
    public void undo() {
        System.out.println("Deshacer defensa.");
    }
}
