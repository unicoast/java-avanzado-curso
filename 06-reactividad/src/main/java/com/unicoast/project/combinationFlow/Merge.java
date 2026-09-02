package com.unicoast.project.combinationFlow;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

/*
 * OPERADORES DE COMBINACIÓN DE FLUJOS: MERGE, CONCAT Y ZIP
 *
 * Comparativa de los 3 operadores:
 * 1. Observable.merge():
 *    - Combina flujos en tiempo real de forma entrelazada (interleaved).
 *    - Emite los elementos tan pronto como llegan, sin esperar que el flujo anterior termine.
 *
 * 2. Observable.concat():
 *    - Combina flujos de forma estrictamente secuencial.
 *    - Espera a que el primer flujo emita 'onComplete()' antes de escuchar y emitir los datos del segundo.
 *
 * 3. Observable.zip():
 *    - Empareja los elementos posición a posición (1° con 1°, 2° con 2°).
 *    - Aplica una función combinadora para producir un nuevo elemento unificado.
 */
public class Merge {
    public static void main(String[] args) throws InterruptedException {
        /*
         * EJEMPLO 1 (Comentado): Observable.merge()
         * Los flujos se combinan en tiempo real según el retardo de cada uno.
         *
        Observable<String> players = Observable.just("Novak", "Rafael", "Roger").delay(1, TimeUnit.SECONDS);
        Observable<String> grandSlams = Observable.just("Serbia", "España", "Suiza");

        Observable.merge(players, grandSlams).subscribe(System.out::println);

        Thread.sleep(2000);
         */

        /*
         * EJEMPLO 2 (Comentado): Observable.concat()
         * El flujo de países espera a que el flujo con retardo de jugadores finalice por completo.
         *
        Observable<String> players = Observable.just("Novak", "Rafael", "Roger").delay(2, TimeUnit.SECONDS);
        Observable<String> grandSlams = Observable.just("Serbia", "España", "Suiza");

        Observable.concat(players, grandSlams).subscribe(System.out::println);

        Thread.sleep(3000);
         */

        // EJEMPLO 3 (Activo): Observable.zip()
        Observable<String> players = Observable.just("Novak", "Rafael", "Roger");
        Observable<Integer> ages = Observable.just(39, 40, 45);

        // Empareja cada nombre con su edad correspondiente
        Observable.zip(players, ages,
                        (name, age) -> name + " - " + age + " años")
                .subscribe(System.out::println);
    }
}
