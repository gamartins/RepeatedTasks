package br.com.martinsdev.repeatedtaks.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.martinsdev.repeatedtaks.R;
import br.com.martinsdev.repeatedtaks.model.Task;

/**
 * Created by gabriel on 10/4/16.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    private ArrayList<Task> tasks;

    public TaskAdapter(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_list, parent, false);
        TaskViewHolder viewHolder = new TaskViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.nome_tarefa.setText(tasks.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
