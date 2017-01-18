package br.com.martinsdev.repeatedtaks.activities.main;

import android.app.AlarmManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.martinsdev.repeatedtaks.R;
import br.com.martinsdev.repeatedtaks.adapter.TaskAdapter;
import br.com.martinsdev.repeatedtaks.model.SingletonTaskList;
import br.com.martinsdev.repeatedtaks.model.Task;
import br.com.martinsdev.repeatedtaks.util.alarm.Alarm;

//public class MainActivity extends AppCompatActivity implements NewTaskDialog.NewTaskDialogListener {
public class MainActivity extends AppCompatActivity {
    private TaskAdapter adapter;
    private ArrayList<Task> taskList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.task_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        taskList = SingletonTaskList.getTasks();
        adapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(adapter);

        if(!Alarm.isSet(MainActivity.this)){
            Alarm.setAlarm(MainActivity.this, 4, 0, AlarmManager.INTERVAL_DAY);
        }

        // Configuração do botão para adicionar uma nova tarefa
        FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.button_add_task);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewTaskDialog taskDialog = new NewTaskDialog();
                taskDialog.show(getSupportFragmentManager(), "dialog_new_task");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    /*@Override
    public void onCreatedTask(Boolean result, String taskName) {
        if (result) {
            // Adicionamos uma nova tarefa ao sistema
            SingletonTaskList.addTask(new Task(taskName));
            adapter.notifyDataSetChanged();

            // Noficação de tarefa criada com sucesso
            String msg = "Tarefa '" + taskName + "' criada.";
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }*/
}
