package br.com.martinsdev.repeatedtaks.fragments;

import java.util.ArrayList;

import br.com.martinsdev.repeatedtaks.model.SingletonTaskList;
import br.com.martinsdev.repeatedtaks.model.Task;

/**
 * Created by Gabriel on 26/01/2017.
 */

public class TaskListAllFragment extends TaskListFragment {
    @Override
    protected ArrayList<Task> getTaskList() {
        return SingletonTaskList.getAllTasks();
    }
}
