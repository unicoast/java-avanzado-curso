package com.unicoast.project.singleton.solution;

/*
 * PATRÓN DE DISEÑO: SINGLETON (INSTANCIA ÚNICA)
 * TIPO DE PATRÓN: CREACIONAL
 *
 * ¿Qué es?
 * Es un patrón que garantiza que una clase tenga una única instancia en toda
 * la aplicación y provee un punto de acceso global a ella.
 *
 * ¿Qué problema soluciona?
 * Evita la colisión de datos al impedir que existan múltiples generadores independientes
 * que repitan los mismos identificadores numéricos en diferentes entidades del juego.
 *
 * ¿Cómo lo soluciona?
 * Asegura que exista UNA SOLA instancia de 'IdGenerator' en todo el juego:
 * 1. Define el constructor como privado para evitar instanciación externa con 'new'.
 * 2. Almacena la única instancia en una variable estática ('instance').
 * 3. Provee un método público 'getInstance()' que devuelve siempre esa misma instancia.
 *
 * Así todas las entidades comparten el mismo contador y los IDs nunca se repiten.
 */
public class IdGenerator {

    // 1. Única instancia compartida de tipo estática
    private static IdGenerator instance;
    private int currentId = 0;

    // 2. Constructor privado para evitar instanciación externa con 'new'
    private IdGenerator() {
    }

    // 3. Método de acceso global con inicialización perezosa (Lazy Initialization)
    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    /*
     * Genera un identificador numérico único e incremental.
     */
    public int generateId() {
        return ++currentId;
    }
}
