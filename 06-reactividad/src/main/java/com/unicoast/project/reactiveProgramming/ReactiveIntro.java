package com.unicoast.project.reactiveProgramming;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

import java.util.Scanner;

/*
 * INTRODUCCIÓN A LA PROGRAMACIÓN REACTIVA: OBSERVABLES Y EMISIÓN
 *
 * ¿Qué es un Observable en RxJava?
 * Es la fuente de datos reactiva que emite una secuencia de elementos hacia los observadores suscritos.
 *
 * Formas de Creación de Observables:
 * 1. Observable.just(T... items):
 *    - Creación estática y directa con valores ya existentes en memoria.
 *    - Emite los elementos secuencialmente y finaliza inmediatamente llamando a 'onComplete()'.
 *
 * 2. Observable.create(ObservableOnSubscribe<T>):
 *    - Creación dinámica y personalizada mediante un emisor ('ObservableEmitter').
 *    - Permite conectar fuentes externas de eventos (como entradas por consola) y emitir datos con 'emitter.onNext()'.
 *
 * Suscripción basada en Lambdas:
 * El método 'subscribe()' ofrece sobrecargas que aceptan funciones lambda para:
 * - onNext: Recepción de cada elemento emitido.
 * - onError: Manejo de errores en el flujo.
 * - onComplete: Acción al finalizar la emisión.
 */
public class ReactiveIntro {
    public static void main(String[] args) {
        // Primer Ejemplo: Creación estática con Observable.just()
        Observable<String> courseStream = Observable.just("HTML", "CSS", "JS", "JAVA");

        courseStream.subscribe(
                item -> System.out.println("Recibido: " + item),
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("Finalizado")
        );

        // Segundo Ejemplo: Creación dinámica con Observable.create() mediante Scanner
        ObservableOnSubscribe subscribe = emitter -> {
            Scanner scanner = new Scanner(System.in);
            String input;
            while(true){
                System.out.println("Ingrese un nombre o 'salir' para cerrar el programa");
                input = scanner.nextLine();
                if("salir".equalsIgnoreCase(input)){
                    break;
                }
                emitter.onNext(input);
            }
        };

        Observable<String> names = Observable.create(subscribe);

        names.subscribe(
                item -> System.out.println("Name: " + item)
        );
    }
}
