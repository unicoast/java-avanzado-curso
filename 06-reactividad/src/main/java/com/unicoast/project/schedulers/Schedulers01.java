package com.unicoast.project.schedulers;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/*
 * CONCURRENCIA Y MANEJO DE HILOS: SCHEDULERS EN RXJAVA 3
 *
 * ¿Qué es un Scheduler?
 * Es el componente de RxJava encargado de asignar y planificar la ejecución
 * del trabajo en hilos específicos (thread pools).
 *
 * Diferencia fundamental entre subscribeOn() y observeOn():
 * 1. subscribeOn(Scheduler):
 *    - Determina en qué hilo se origina y ejecuta la emisión inicial del Observable.
 *    - Afecta hacia arriba en la cadena (upstream).
 *
 * 2. observeOn(Scheduler):
 *    - Cambia el hilo de ejecución para los operadores y suscriptores que se encuentren por debajo de él (downstream).
 *    - Se puede invocar varias veces en la misma cadena para alternar entre diferentes hilos.
 *
 * Operador doOnNext():
 * Permite ejecutar acciones secundarias (como depuración o registro en logs) sin alterar los datos del flujo.
 */
public class Schedulers01 {
    public static void main(String[] args) throws InterruptedException {
        Observable.just("Hola")
                .subscribeOn(Schedulers.io())
                // do sirve para depurar
                .doOnNext(string -> System.out.println(string + " Just: " + Thread.currentThread().getName()))
                .observeOn(Schedulers.computation())
                .map(s -> s + " mundo")
                .doOnNext(string -> System.out.println(" Map: " + Thread.currentThread().getName()))
                .subscribe(s -> System.out.println("Resultado: " + s + " " + Thread.currentThread().getName()));

        Thread.sleep(1000);
    }
}
