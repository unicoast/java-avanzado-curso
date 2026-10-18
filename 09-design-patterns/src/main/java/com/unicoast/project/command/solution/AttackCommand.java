package com.unicoast.project.command.solution;

/*
 * PATRÓN COMMAND: COMANDO CONCRETO (CONCRETE COMMAND) - ATAQUE
 *
 * Encapsula la solicitud de ataque, manteniendo una referencia al receptor ('Player').
 * Al invocarse 'execute()', delega la ejecución en el método 'player.attack()'.
 * En 'undo()', implementa la lógica necesaria para deshacer la acción.
 */
public class AttackCommand implements Command {
    private Player player;

    public AttackCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.attack();
    }

    @Override
    public void undo() {
        System.out.println("Deshacer ataque.");
    }
}
