package com.unicoast.project.finalProject.stream;

import com.unicoast.project.finalProject.model.player.Player;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

/*
 * BUS DE EVENTOS REACTIVO: PLAYER STREAM
 *
 * ¿Qué es un Bus de Eventos Reactivo?
 * Es un canal centralizado donde los componentes pueden emitir y escuchar eventos en tiempo real.
 *
 * toSerialized():
 * Garantiza que las llamadas a onNext(), onError() y onComplete() sean thread-safe (seguras en multihilo).
 */
public class PlayerStream {
    // ¿Subject qué es? Un Observable como un Observer, emite datos pero también deja que otros escuchen
    // PublishSubject puede recibir y emitir datos pero no es una lista, no se almacenan
    // si se suscribe tarde los datos emitidos anteriores se pierden
    private final Subject<Player> studentSubject = PublishSubject.<Player>create().toSerialized();

    public void publish(Player player){
        studentSubject.onNext(player);
    }

    public Subject<Player> getStream(){
        return studentSubject;
    }

    public void complete(){
        studentSubject.onComplete();
    }

    public void error(Throwable throwable){
        studentSubject.onError(throwable);
    }
}
