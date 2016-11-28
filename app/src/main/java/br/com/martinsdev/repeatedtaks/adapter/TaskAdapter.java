package br.com.martinsdev.repeatedtaks.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;

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
        final int tempPosition = position;
        holder.taskName.setText(tasks.get(position).getName());
        holder.checkBoxName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checkBoxStatus) {
                if (checkBoxStatus){
                    tasks.remove(tempPosition);

                    Task task = Task.listAll(Task.class).get(tempPosition);
                    task.setChecked(true);
                    task.save();

                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
