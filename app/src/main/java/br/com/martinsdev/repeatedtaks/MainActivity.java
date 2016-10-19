package br.com.martinsdev.repeatedtaks;

import android.app.AlarmManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import br.com.martinsdev.repeatedtaks.adapter.TaskAdapter;
import br.com.martinsdev.repeatedtaks.model.SingletonTaskList;
import br.com.martinsdev.repeatedtaks.util.alarm.Alarm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.task_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        TaskAdapter adapter = new TaskAdapter(SingletonTaskList.getTasks());
        recyclerView.setAdapter(adapter);

        if(!Alarm.isSet(MainActivity.this)){
            Alarm.setAlarm(MainActivity.this, 4, 0, AlarmManager.INTERVAL_DAY);
        }

    }
}
