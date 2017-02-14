package br.com.martinsdev.repeatedtaks.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.martinsdev.repeatedtaks.R;
import br.com.martinsdev.repeatedtaks.fragments.TaskDetailedFragment;
import br.com.martinsdev.repeatedtaks.model.Task;

/**
 * Created by gabriel on 10/4/16.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    private ArrayList<Task> tasks;
    private AppCompatActivity activity;

    public TaskAdapter(ArrayList<Task> tasks, AppCompatActivity activity){
        this.tasks = tasks;
        this.activity = activity;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_list, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        final int tempPosition = position;
        final long taskId = tasks.get(position).getId();

        holder.taskName.setText(tasks.get(position).getName());

        /* Exibindo a data para teste do software */

        /*SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        tasks.get(position).getNextRepetition().getTime();
        String date = s.format(tasks.get(position).getNextRepetition().getTime());
        holder.taskName.setText(tasks.get(position).getName() + " | Next: " + date);*/

        /* Fim da alteração no código */

        holder.taskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putLong("taskId", taskId);

                TaskDetailedFragment detailedFragment = new TaskDetailedFragment();
                detailedFragment.setArguments(bundle);

                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_principal, detailedFragment);
                transaction.addToBackStack(null);
                transaction.commit();
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

    public void updateTasks(ArrayList<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }
}
