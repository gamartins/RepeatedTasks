package br.com.martinsdev.repeatedtaks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.martinsdev.repeatedtaks.adapter.TaskAdapter;
import br.com.martinsdev.repeatedtaks.model.Task;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Task task01 = new Task("Academia");
        Task task02 = new Task("Pós-Graduação");
        Task task03 = new Task("Livro");

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task01);
        tasks.add(task02);
        tasks.add(task03);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.task_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        TaskAdapter adapter = new TaskAdapter(tasks);
        recyclerView.setAdapter(adapter);

    }
}
