package br.com.martinsdev.repeatedtaks.model;

import java.util.ArrayList;

/**
 * Created by gabriel on 10/14/16.
 */

public class SingletonTaskList {
    private static final SingletonTaskList INSTANCE = new SingletonTaskList();
    private static ArrayList<Task> tasks;

    private SingletonTaskList() {
        // Exibindo as tarefas não concluidas. O valor 0 é considerado 'false' pela orm.
        // tasks = new ArrayList<>(Task.find(Task.class, "checked = ?", "0"));
        tasks = new ArrayList<>(Task.listAll(Task.class));
    }

    public static synchronized SingletonTaskList getInstance(){
        return INSTANCE;
    }

    public static ArrayList<Task> getUncheckedTasks() {
        ArrayList<Task> uncheckedTasks = new ArrayList<Task>();

        for (Task task: tasks){
            if (!task.isChecked()){
                uncheckedTasks.add(task);
            }
        }

        return uncheckedTasks;
    }

    public static ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public static Task getTaskById(long id) {
        return Task.findById(Task.class, id);
    }

    public static void addTask(Task task){
        tasks.add(task);
        task.save();
        notifyDataSetChanged();
    }

    public static void removeTask(Task task) {
        tasks.remove(task);
        task.delete();
        notifyDataSetChanged();
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

    public static void notifyDataSetChanged(){
        tasks = new ArrayList<>(Task.listAll(Task.class));
    }
}
