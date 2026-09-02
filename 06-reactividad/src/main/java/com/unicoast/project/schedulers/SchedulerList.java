package com.unicoast.project.schedulers;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * ARQUITECTURA MULTI-HILO AVANZADA CON SCHEDULERS DIVERGENTES
 *
 * Tipos de Schedulers utilizados:
 * 1. Schedulers.io(): Pool elástico para operaciones de Entrada/Salida (lectura, APIs, base de datos).
 * 2. Schedulers.computation(): Pool de tamaño fijo para operaciones intensivas de CPU y temporizadores.
 * 3. Schedulers.single(): Hilo único compartido para serializar la entrega de resultados ordenados.
 */
public class SchedulerList {
    public static void main(String[] args) throws InterruptedException {
        List<String> students = Arrays.asList("Ana", "Luis", "Carlos", "Sofía");
        List<String> teachers = Arrays.asList("Prof. Gómez", "Prof. Díaz", "Prof. Luis");

        String searchQuery = "Luis";

        Observable<String> studentSearch = Observable.fromIterable(students)
                .filter(name -> name.contains(searchQuery))
                .delay(500, TimeUnit.MILLISECONDS) // simula búsqueda lenta
                .subscribeOn(Schedulers.io()) // hilo para tareas de IO
                .doOnNext(s -> System.out.println("Estudiante encontrado: " + s +
                        " en " + Thread.currentThread().getName()));

        Observable<String> teacherSearch = Observable.fromIterable(teachers)
                .filter(name -> name.contains(searchQuery))
                .delay(700, TimeUnit.MILLISECONDS) // simula búsqueda un poco más lenta
                .subscribeOn(Schedulers.computation()) // hilo para tareas computacionales
                .doOnNext(t -> System.out.println("Profesor encontrado: " + t +
                        " en " + Thread.currentThread().getName()));

        Observable.merge(studentSearch, teacherSearch)
                .observeOn(Schedulers.single()) // Mostrar resultados ordenados
                .subscribe(result -> System.out.println("Resultado final: " + result +
                        " en " + Thread.currentThread().getName()));

        // Pausa para permitir la finalización de los hilos asíncronos
        Thread.sleep(2000);
    }
}
