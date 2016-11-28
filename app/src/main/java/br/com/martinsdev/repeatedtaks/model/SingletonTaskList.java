package br.com.martinsdev.repeatedtaks.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by gabriel on 10/14/16.
 */

public class SingletonTaskList {
    private static final SingletonTaskList INSTANCE = new SingletonTaskList();
    private static ArrayList<Task> tasks;

    private SingletonTaskList() {
        // Exibindo as tarefas não concluidas. O valor 0 é considerado 'false' pela orm.
        tasks = new ArrayList<>(Task.find(Task.class, "checked = ?", "0"));
    }

    public static synchronized SingletonTaskList getInstance(){
        return INSTANCE;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void addTask(Task task){
        tasks.add(task);
        task.save();
    }

    public static void resetTasks(){
        ArrayList<Task> tempTasks = new ArrayList<>(Task.listAll(Task.class));

        for (Task task : tempTasks) {
            task.setChecked(false);
            task.save();
        }

        // Exibindo as tarefas não concluidas. O valor 1 é considerado 'false' pela orm.
        tasks = new ArrayList<>(Task.find(Task.class, "checked = ?", "0"));
    }
}
