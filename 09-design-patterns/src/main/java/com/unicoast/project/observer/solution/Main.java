package com.unicoast.project.observer.solution;

/*
 * PATRÓN DE DISEÑO: OBSERVER (OBSERVADOR)
 * TIPO DE PATRÓN: DE COMPORTAMIENTO (BEHAVIORAL)
 *
 * ¿Qué es?
 * Es un patrón de suscripción donde un objeto (el sujeto) notifica de forma
 * automática a múltiples objetos (los observadores) cada vez que cambia su estado.
 *
 * ¿Qué problema soluciona?
 * Evita el acoplamiento monolítico: el jugador ya no tiene que encargarse de actualizar
 * la barra de vida, escribir en el log y revisar los logros directamente.
 *
 * ¿Cómo lo soluciona?
 * El jugador ('Player') solo mantiene una lista de observadores. Al recibir daño
 * mediante 'takeDamage()', avisa a todos con 'notifyObservers()'. Cada observador
 * reacciona por su cuenta y permite agregar nuevos observadores sin tocar la clase Player.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Creación del jugador (sujeto observable)
        Player player = new Player();

        // 2. Suscripción de los observadores interesados
        player.addObserver(new HealthBar());
        player.addObserver(new GameLogger());
        player.addObserver(new AchievementTracker());

        // 3. Al recibir daño, el jugador notifica automáticamente a todos sus observadores
        player.takeDamage(20);
        player.takeDamage(40);
    }
}
