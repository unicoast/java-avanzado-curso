package com.unicoast.project.reactiveProgramming;

import io.reactivex.rxjava3.subjects.PublishSubject;

import java.util.Scanner;

/*
 * PUENTE ENTRE PROGRAMACIÓN IMPERATIVA Y REACTIVA: PUBLISHSUBJECT
 *
 * ¿Qué es un Subject en RxJava?
 * Es un puente especial que actúa simultáneamente como Observable y como Observer:
 * 1. Como Observable: Permite que otros observadores se suscriban a él.
 * 2. Como Observer: Permite emitir eventos llamando a sus métodos 'onNext()', 'onError()' y 'onComplete()'.
 *
 * Características de un PublishSubject:
 * - Es un 'Hot Observable': No almacena eventos anteriores en memoria.
 * - Los suscriptores reciben únicamente las emisiones producidas a partir del momento de su suscripción.
 * - Es ideal para convertir entradas interactivas (como consola o teclado) en flujos reactivos.
 */
public class ReactiveScanner {
    public static void main(String[] args) {
        PublishSubject<String> inputStream = PublishSubject.create();

        inputStream.subscribe(
                item -> System.out.println("Recibido: " + item.toUpperCase()),
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("Finalizado")
        );

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa un texto o 'salir' para cerrar el programa");

        while(true){
            String input = scanner.nextLine();
            if("salir".equalsIgnoreCase(input)){
                inputStream.onComplete();
                break;
            }

            inputStream.onNext(input);
        }

        scanner.close();
    }
}
