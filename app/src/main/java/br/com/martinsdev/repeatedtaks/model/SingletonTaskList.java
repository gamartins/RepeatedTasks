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
        Task task01 = new Task("Academia");
        Task task02 = new Task("Pós-Graduação");
        Task task03 = new Task("Livro");
        Task task04 = new Task("Cursos Livres");
        Task task05 = new Task("App");
        Task task06 = new Task("Casa");

        tasks = new ArrayList<>();
        tasks.addAll(Arrays.asList(task01, task02, task03, task04, task05, task06));
    }

    public static synchronized SingletonTaskList getInstance(){
        return INSTANCE;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void setTasks(ArrayList<Task> tasks) {
        SingletonTaskList.tasks = tasks;
    }

    public static void resetTasks(){
        Task task01 = new Task("Academia");
        Task task02 = new Task("Pós-Graduação");
        Task task03 = new Task("Livro");
        Task task04 = new Task("Cursos Livres");
        Task task05 = new Task("App");
        Task task06 = new Task("Casa");

        tasks = new ArrayList<>();
        tasks.addAll(Arrays.asList(task01, task02, task03, task04, task05, task06));
    }
}
