package com.unicoast.project.task;

import com.unicoast.project.task.controller.TaskController;
import com.unicoast.project.task.model.TaskRepository;
import com.unicoast.project.task.view.TaskView;

public class Main {
    public static void main(String[] args) {
        // Inicialización de componentes
        TaskRepository taskRepository = new TaskRepository();
        TaskController taskController = new TaskController(taskRepository);
        TaskView taskView = new TaskView(taskController);

        // Iniciar la interfaz de usuario
        taskView.showMenu();
    }
}
