package com.unicoast.project.reactiveProgramming;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

/*
 * FLUJOS ASÍNCRONOS BASADOS EN TIEMPO: OBSERVABLE.INTERVAL
 *
 * ¿Qué es Observable.interval()?
 * Es un operador que crea un flujo infinito que emite números secuenciales (0, 1, 2, 3...)
 * a intervalos regulares de tiempo especificados (por ejemplo, cada 1 segundo).
 *
 * Ejecución en Hilos de Fondo:
 * Por defecto, 'Observable.interval()' opera en el Scheduler 'Schedulers.computation()'.
 * Dado que el hilo principal 'main' de Java no espera a hilos de fondo por defecto,
 * se utiliza 'Thread.sleep()' para mantener la aplicación activa y observar las lecturas del sensor.
 *
 * Pipeline Reactivo:
 * 1. Generación: Intervalo temporal de 1 segundo.
 * 2. Transformación (map): Convierte cada pulso en un valor de temperatura simulado.
 * 3. Filtrado (filter): Deja pasar solo temperaturas críticas (> 30°C).
 * 4. Suscripción (subscribe): Muestra la alerta en consola.
 */
public class TemperatureSensor {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);

        Observable<Double> temperatureStream = interval.map(
                t -> {
                    double temp = 20 + Math.random() * 15;
                    System.out.println("Temperatura actual: " + temp);
                    return temp;
                }
        );

        temperatureStream
                .filter(temp -> temp > 30)
                .subscribe(
                item -> System.out.println("¡Alerta! Temperatura Alta: " + item),
                throwable -> System.out.println(throwable.getMessage()),
                () -> System.out.println("Fin")
        );

        Thread.sleep(10000);
    }
}
