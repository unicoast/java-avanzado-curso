# Consigna de Trabajo Práctico: Gestión de Productos con Optional, Stream, Lombok y el patrón MVC

**Objetivo:**

Desarrollar una aplicación de consola en Java que permita gestionar productos utilizando colecciones en memoria, aplicando el patrón de diseño MVC, la biblioteca Lombok y conceptos de programación funcional como `Optional` y `Stream`.

**Requisitos:**

1. **Modelo de Datos:**
   * Crear la clase `Product` con los siguientes atributos:
     ```java
     private Long id;
     private String name;
     private double price;
     private int stock;
     private ProductCategory category;
     ```
   * Utilizar la biblioteca **Lombok** para generar automáticamente los getters, setters, constructores y el método `toString()`.
   * Crear un enum `ProductCategory` con al menos 3 categorías (por ejemplo: ELECTRONICS, CLOTHING, FOOD).

2. **Persistencia en Memoria:**
   * Los productos deberán almacenarse en una lista en memoria ( `List<Product>` ), sin uso de base de datos.

3. **Programación Funcional:**
   * Utilizar `Optional` para el manejo seguro de productos buscados por ID o nombre.
   * Utilizar `Stream` para realizar operaciones como:
     * Filtrar productos por categoría o por precio mayor a un valor determinado.
     * Ordenar productos por precio o por nombre.
     * Calcular promedios o estadísticas simples sobre los productos.

4. **Arquitectura:**
   * Aplicar el patrón **Modelo-Vista-Controlador (MVC)**:
     * **Modelo**: clase `Product` y la lógica de negocio relacionada.
     * **Vista**: interacción mediante consola para cargar y consultar productos.
     * **Controlador**: coordina las operaciones entre la vista y el modelo.

5. **Dependencias:**
   * Usar **Maven** para la gestión de dependencias.
   * Incluir al menos:
     * Lombok

6. **Funcionamiento esperado:**
   * Al ejecutar el programa, el usuario debe poder:
     * Cargar nuevos productos por consola.
     * Buscar productos por ID o nombre (usando `Optional`).
     * Listar productos filtrados u ordenados (usando `Stream`).
     * Ver estadísticas como cantidad total de productos o promedio de precios.
