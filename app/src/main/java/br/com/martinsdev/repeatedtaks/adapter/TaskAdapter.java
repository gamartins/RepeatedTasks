package br.com.martinsdev.repeatedtaks.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;

import br.com.martinsdev.repeatedtaks.activities.task.DetailedTaskActivity;
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

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        final int tempPosition = position;
        holder.taskName.setText(tasks.get(position).getName());
        holder.taskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailedTaskActivity.class);
                intent.putExtra("position", tempPosition);
                view.getContext().startActivity(intent);
            }
        });
        holder.checkBoxName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checkBoxStatus) {
                if (checkBoxStatus){
                    // Atualizando a tarefa no banco de dados
                    Task task = tasks.get(tempPosition);
                    task.setChecked(true);
                    task.save();

                    // Removendo a tarefa da lista exibida no adapter
                    tasks.remove(tempPosition);
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
