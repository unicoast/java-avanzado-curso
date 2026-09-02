package com.unicoast.project.schedulers;

import io.reactivex.rxjava3.core.Observable;
import java.util.Arrays;
import java.util.List;

/*
 * BÚSQUEDA Y FUSIÓN DE FLUJOS EN EL HILO PRINCIPAL (LÍNEA BASE SÍNCRONA)
 *
 * Propósito:
 * Demostrar la combinación de dos fuentes de datos con 'Observable.merge()'
 * cuando NO se configuran Schedulers explícitos (se ejecuta en el hilo actual de forma síncrona).
 */
public class SchedulerExample {
    public static void main(String[] args) throws InterruptedException {
        List<String> students = Arrays.asList("Ana", "Luis", "Carlos", "Sofía");
        List<String> teachers = Arrays.asList("Prof. Gómez", "Prof. Díaz", "Prof. Luis");

        String searchQuery = "Luis";

        Observable<String> studentSearch = Observable.fromIterable(students)
                .filter(name -> name.contains(searchQuery))
                .doOnNext(s -> System.out.println("Estudiante encontrado: " + s +
                        " en hilo: " + Thread.currentThread().getName()));

        Observable<String> teacherSearch = Observable.fromIterable(teachers)
                .filter(name -> name.contains(searchQuery))
                .doOnNext(t -> System.out.println("Profesor encontrado: " + t +
                        " en hilo: " + Thread.currentThread().getName()));

        Observable.merge(studentSearch, teacherSearch)
                .subscribe(result -> System.out.println("Resultado final: " + result +
                        " en hilo: " + Thread.currentThread().getName()));
    }
}
