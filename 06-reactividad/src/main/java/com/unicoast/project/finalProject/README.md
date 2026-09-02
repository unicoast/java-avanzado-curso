# Consigna de Trabajo Práctico: Sistema Reactivo de Registro de Jugadores con RxJava 3 y el Patrón MVC

**Objetivo:**

Desarrollar una aplicación de consola en Java que implemente el paradigma de programación reactiva utilizando la biblioteca **RxJava 3**, integrando el patrón de diseño **Modelo-Vista-Controlador (MVC)** junto con una **Arquitectura Guiada por Eventos (Event-Driven)** mediante flujos asíncronos y operadores reactivos.

---

## 1. Modelo de Datos:
* Crear la clase de dominio `Player` con los siguientes atributos:
  ```java
  private String name;
  private int age;
  ```
* Implementar encapsulamiento completo con constructores, getters, setters y el método `toString()`.

---

## 2. Bus de Eventos Reactivo (Stream):
* Implementar la clase `PlayerStream` utilizando un `PublishSubject` serializado (`toSerialized()`) para garantizar seguridad en entornos multihilo.
* Métodos requeridos:
  * `publish(Player player)`: Emite un nuevo objeto hacia los suscriptores (`onNext`).
  * `getStream()`: Expone el `Subject` como `Observable<Player>`.
  * `complete()`: Notifica el fin de las emisiones (`onComplete`).
  * `error(Throwable throwable)`: Notifica un error irrecuperable (`onError`).

---

## 3. Lógica de Negocio y Servicio Reactivo (Service):
* Implementar la clase `PlayerService`:
  * Almacenar los jugadores validados en una colección en memoria (`List<Player>`).
  * `subscribeTo(Observable<Player> stream)`: Se suscribe al flujo procesado por el controlador para guardar las entidades y calcular estadísticas al completarse.
  * `verifyPlayer(Player player)`: Retorna un `Observable<Player>` que valida de forma asíncrona que el jugador tenga al menos 18 años.
  * `validateName(Player player)`: Retorna un `Observable<Player>` que valida de forma asíncrona que el nombre tenga al menos 3 caracteres; en caso contrario, emite un error (`onError`).
  * `showStatatics()`: Calcula el total de jugadores registrados y el promedio de edad utilizando la API tradicional de Streams de Java.

---

## 4. Orquestación y Resiliencia (Controller):
* Implementar la clase `PlayerController`:
  * Coordinar el pipeline reactivo conectando `stream.getStream()` con las validaciones del servicio mediante `flatMap()`.
  * Implementar tolerancia a fallos con `onErrorResumeNext()` para capturar excepciones de validación y retornar `Observable.empty()`, evitando la terminación prematura del bus global.
  * `processInput(String name, String ageInput)`: Realiza la conversión sintáctica de tipos y publica la entidad en el stream.
  * `finishInput()`: Envía la señal de completado al stream.

---

## 5. Interfaz de Usuario por Consola (View):
* Implementar la clase `PlayerConsoleView`:
  * Capturar interactivamente los nombres y edades ingresados por el usuario.
  * Enviar el comando de finalización al ingresar `'exit'`.

---

## 6. Dependencias Requeridas:
* **Maven** con la dependencia oficial de **RxJava 3**:
  ```xml
  <dependency>
      <groupId>io.reactivex.rxjava3</groupId>
      <artifactId>rxjava</artifactId>
      <version>3.1.12</version>
  </dependency>
  ```
