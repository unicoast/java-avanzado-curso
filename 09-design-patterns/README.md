# Patrones de Diseño en Java (GoF)

Módulo formativo centrado en los patrones de diseño clásicos (GoF), aplicados a un dominio común de videojuego para facilitar la comprensión práctica.

Cada patrón cuenta con una estructura simétrica de tres elementos:
1. `problem/Main.java`: Demuestra el dolor, la rigidez o el acoplamiento del diseño sin el patrón.
2. `solution/Main.java`: Demuestra la arquitectura limpia y desacoplada aplicando el patrón GoF.
3. `[Patron]_UML.png`: Diagrama visual que consolida la arquitectura y relaciones de clases.

---

## Flujo de estudio recomendado

Para cada patrón se sugiere seguir este orden:

```text
1. problem/Main.java  --> Identificar el problema de diseño en código y consola.
2. solution/Main.java --> Analizar y ejecutar la solución desacoplada.
3. [Patron]_UML.png   --> Consolidar visualmente la arquitectura y relaciones de clases.
```

---

## Resumen de los 7 patrones

### 1. Strategy
- **Tipo**: De Comportamiento (*Behavioral*).
- **¿Qué es?**: Define una familia de algoritmos, los encapsula en clases independientes y los hace intercambiables en tiempo de ejecución.
- **¿Qué resuelve?**: Evita tener comportamientos soldados a las clases mediante condicionales `switch/if-else`. Permite cambiar armas o ataques en plena partida mediante composición e interfaces funcionales (`AttackStrategy`).

### 2. Singleton
- **Tipo**: Creacional (*Creational*).
- **¿Qué es?**: Garantiza que una clase posea una única instancia en toda la aplicación y provee un punto de acceso global a ella.
- **¿Qué resuelve?**: Evita la colisión o inconsistencia de datos al impedir que existan múltiples generadores independientes (`IdGenerator`) que repitan identificadores numéricos en distintas entidades.

### 3. Factory Method
- **Tipo**: Creacional (*Creational*).
- **¿Qué es?**: Define una interfaz o método abstracto para crear objetos, delegando en las subclases la decisión de qué clase concreta instanciar.
- **¿Qué resuelve?**: Desacopla al cliente de las clases derivadas. En lugar de crear enemigos con `new` y condicionales genéricos, cada fábrica concreta (`DragonFactory`, `SkeletonFactory`) se encarga de su propio producto.

### 4. Observer
- **Tipo**: De Comportamiento (*Behavioral*).
- **¿Qué es?**: Patrón de suscripción donde un sujeto notifica automáticamente a múltiples observadores registrados cuando cambia su estado.
- **¿Qué resuelve?**: Elimina el acoplamiento monolítico. La clase `Player` ya no actualiza directamente la interfaz, el registro ni los logros; solo emite el evento y cada suscriptor reacciona por su cuenta.

### 5. Command
- **Tipo**: De Comportamiento (*Behavioral*).
- **¿Qué es?**: Encapsula una solicitud o acción como un objeto independiente con sus métodos `execute()` y `undo()`.
- **¿Qué resuelve?**: Desacopla el invocador (`InputHandler`) del receptor (`Player`). Permite mapear teclas dinámicamente y almacenar un historial en una pila (`Stack`) para deshacer acciones (*Undo* con la tecla `'U'`).

### 6. Decorator
- **Tipo**: Estructural (*Structural*).
- **¿Qué es?**: Añade responsabilidades y mejoras a un objeto de forma dinámica envolviéndolo dentro de objetos decoradores compatibles con su interfaz base.
- **¿Qué resuelve?**: Soluciona la explosión combinatoria de subclases por herencia múltiple (`Sword`, `FireSword`, `PoisonSword`, `FirePoisonSword`). Permite apilar efectos en cadena en tiempo de ejecución.

### 7. Builder
- **Tipo**: Creacional (*Creational*).
- **¿Qué es?**: Separa la construcción de un objeto complejo de su representación final mediante pasos configurables y métodos encadenados.
- **¿Qué resuelve?**: Elimina el antipatrón del constructor telescópico (constructores con 9 o más parámetros posicionales propensos a errores). Incluye implementación manual (`GameCharacter.Builder`) y moderna con Lombok (`@Builder` en `Character`).

---

## Tabla comparativa rápida

| Patrón | Categoría | Problema principal | Solución GoF |
| :--- | :--- | :--- | :--- |
| **Strategy** | Comportamiento | Ataques fijos en cada clase. | Composición con interfaz `AttackStrategy`. |
| **Singleton** | Creacional | IDs duplicados por múltiples generadores. | Instancia única con `getInstance()`. |
| **Factory Method** | Creacional | Acoplamiento a clases concretas de enemigos. | Fábricas especializadas por entidad. |
| **Observer** | Comportamiento | `Player` acoplado a UI, logs y logros. | Suscripción dinámica con `Subject` y `Observer`. |
| **Command** | Comportamiento | Cadenas `if-else` sin historial ni Undo. | Objetos comando ejecutables y reversibles. |
| **Decorator** | Estructural | Explosión de subclases por combinaciones. | Envoltura recursiva con `WeaponDecorator`. |
| **Builder** | Creacional | Constructores gigantes y desordenados. | Construcción paso a paso con métodos encadenados. |
