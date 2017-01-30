package br.com.martinsdev.repeatedtaks.fragments;


import android.app.AlarmManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.martinsdev.repeatedtaks.R;
import br.com.martinsdev.repeatedtaks.adapter.TaskAdapter;
import br.com.martinsdev.repeatedtaks.model.SingletonTaskList;
import br.com.martinsdev.repeatedtaks.model.Task;
import br.com.martinsdev.repeatedtaks.util.alarm.Alarm;

public class TaskListFragment extends Fragment {
    private TaskAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.task_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TaskAdapter(getTaskList(), (AppCompatActivity) getActivity());
        recyclerView.setAdapter(adapter);

        if(!Alarm.isSet(getActivity())){
            Alarm.setAlarm(getActivity(), 4, 0, AlarmManager.INTERVAL_DAY);
        }

        // Configuração do botão para adicionar uma nova tarefa
        FloatingActionButton actionButton;
        actionButton = (FloatingActionButton) view.findViewById(R.id.button_add_task);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewTaskDialogFragment taskDialog = new NewTaskDialogFragment();
                taskDialog.show(getActivity().getSupportFragmentManager(), "dialog_new_task");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    protected ArrayList<Task> getTaskList(){
        return SingletonTaskList.getUncheckedTasks();
    }

    public void updateList(){
        adapter.updateTasks(getTaskList());
    }
}
