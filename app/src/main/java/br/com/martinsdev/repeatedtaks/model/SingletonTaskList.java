package br.com.martinsdev.repeatedtaks.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

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
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(Task.listAll(Task.class));

        return allTasks;
    }

    public static ArrayList<Task> getTodayTasks() {
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(System.currentTimeMillis());

        ArrayList<Task> tempTasks = getAllTasks();
        ArrayList<Task> todayTasks = new ArrayList<>();

        for (Task task : tempTasks) {
            if (today.after(task.getNextRepetition())) {
                todayTasks.add(task);
            }
        }

        return todayTasks;
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

    public static void resetTodayTasks(){
        ArrayList<Task> tempTasks = getTodayTasks();

        for (Task task : tempTasks) {
            task.setChecked(false);
            task.updateNextRepetition();
            task.save();
        }

        // Exibindo as tarefas não concluidas. O valor 1 é considerado 'true' pela orm.
//        tasks = new ArrayList<>(Task.find(Task.class, "checked = ?", "0"));
        tasks = new ArrayList<>(Task.listAll(Task.class));
    }

    public static void notifyDataSetChanged(){
        tasks = new ArrayList<>(Task.listAll(Task.class));
    }
}
