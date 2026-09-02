package com.unicoast.project.observer;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/*
 * EL PATRÓN OBSERVER EN RXJAVA 3
 *
 * ¿Qué es el Patrón Observer?
 * Es un patrón de diseño donde un objeto emisor (Observable) mantiene una lista de
 * observadores (Observers) suscritos a él, y les notifica automáticamente cualquier
 * emisión de datos, error o finalización del flujo.
 *
 * Los 4 Métodos Fundamentales de la Interfaz 'Observer<T>':
 * 1. onSubscribe(@NonNull Disposable d):
 *    Se ejecuta al suscribirse. Entrega el objeto 'Disposable' para gestionar y cancelar la suscripción.
 *
 * 2. onNext(T item):
 *    Se ejecuta cada vez que el Observable emite un nuevo dato exitosamente.
 *
 * 3. onError(@NonNull Throwable e):
 *    Se ejecuta si ocurre un error en el flujo. Notifica el fallo y termina el stream.
 *
 * 4. onComplete():
 *    Se ejecuta una sola vez cuando el Observable termina de emitir todos los datos con éxito.
 *
 * Cancelación con 'Disposable':
 * El método 'disposable.dispose()' cancela la suscripción activa para dejar de recibir eventos y liberar recursos.
 */
public class ObserverExample01 {
    public static void main(String[] args) {
        // Creación del flujo reactivo a partir de una colección filtrando valores nulos
        Observable<String> courseStream = Observable
                .fromIterable(
                        Arrays.asList("HTML", "CSS", "JS", null, "JAVA", "C").stream().filter(Objects::nonNull).collect(Collectors.toSet())
                );

        // clase anónima: Primer Observer con lógica de cancelación condicional
        Observer<String> observer = new Observer<>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                this.disposable = d;
                System.out.println("Estoy suscrito al curso!");
            }

            @Override
            public void onNext(String s) {
                if ("c".equalsIgnoreCase(s)) {
                    // cancelar la suscripción voluntariamente
                    disposable.dispose();
                } else {
                    System.out.println("Recibí: " + s);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("Error: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Fin del Stream");
            }
        };

        // Segundo Observer: Demuestra que múltiples observadores pueden escuchar el mismo Observable
        Observer<String> observer2 = new Observer<>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Observer2. Estoy suscrito al curso !");
            }

            @Override
            public void onNext(String s) {
                System.out.println("Observer2. Recibí: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("Observer2. Error: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Observer2. Fin del Stream");
            }
        };

        // Suscripción de ambos observadores al flujo
        courseStream.subscribe(observer);
        courseStream.subscribe(observer2);
    }
}
