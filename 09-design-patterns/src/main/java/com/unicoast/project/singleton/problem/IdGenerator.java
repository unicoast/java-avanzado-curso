package com.unicoast.project.singleton.problem;

/*
 * EL PROBLEMA (SIN SINGLETON):
 *
 * Cualquiera puede crear un 'new IdGenerator()'. Como cada generador empieza
 * su conteo desde cero, dos generadores distintos van a dar el mismo ID
 * a dos personajes diferentes, provocando colisión de identificadores.
 */
public class IdGenerator {
    private int currentId = 0;

    public int generateId() {
        return ++currentId;
    }
}
