package com.unicoast.project.task.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.unicoast.project.task.model.Task;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// Persistencia: guarda y carga las tareas en un archivo JSON usando la librería Gson
// Gson convierte objetos Java a JSON (serialización) y JSON a objetos Java (deserialización)
// Los métodos son static porque no necesitan estado propio, solo leen/escriben el archivo
public class TaskPersistence {
    // Ruta del archivo donde se guardan las tareas
    private final static String FILE_PATH = "tasks.json";
    // Gson con formato legible (setPrettyPrinting -> JSON indentado, no todo en una línea)
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Guardar: convierte la lista de tareas a JSON y la escribe en el archivo
    // try-with-resources: el Writer se cierra automáticamente al salir del bloque
    public static void saveTasks(List<Task> tasks) {
        try(Writer writer = new FileWriter(FILE_PATH) ) {
            gson.toJson(tasks, writer); // Serialización: List<Task> -> JSON
            System.out.println("Tareas guardadas exitosamente");
        } catch (IOException e) {
            System.out.println("Error al guardar las tareas: " + e.getMessage());
        }
    }

    // Cargar: lee el archivo JSON y lo convierte de vuelta a una lista de tareas
    // Si el archivo no existe (primera vez), retorna lista vacía
    public static List<Task> loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>(); // Primera ejecución: no hay archivo todavía
        }

        try (Reader reader = new FileReader(FILE_PATH)) {
            // TypeToken: necesario porque Gson no puede inferir List<Task> en tiempo de ejecución
            // Java borra los genéricos en compilación (type erasure), TypeToken los preserva
           Type listType = new TypeToken<List<Task>>() {}.getType();
           return gson.fromJson(reader, listType); // Deserialización: JSON -> List<Task>
        } catch (IOException e) {
            System.out.println("Error al cargar las tareas: " + e.getMessage());
            return new ArrayList<>(); // Si falla la lectura, retorna lista vacía (no crashea)
        }
    }
}
