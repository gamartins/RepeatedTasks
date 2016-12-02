package br.com.martinsdev.repeatedtaks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.martinsdev.repeatedtaks.model.SingletonTaskList;
import br.com.martinsdev.repeatedtaks.model.Task;

public class DetailedTaskActivity extends AppCompatActivity {
    private EditText taskName, taskDetails;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_task);

        // Recebendo o elemento na posição definida em TaskAdapter
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        task = SingletonTaskList.getTasks().get(position);

        taskName = (EditText) findViewById(R.id.task_name);
        taskName.setText(task.getName());
        taskDetails = (EditText) findViewById(R.id.task_details);
        taskDetails.setText(task.getDetails());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detailed_task, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_button:
                // Salvando as alterações no banco de dados e definindo mensagem da notificação
                Toast.makeText(this, R.string.task_saved, Toast.LENGTH_LONG).show();
                task.setName(taskName.getText().toString());
                task.setDetails(taskDetails.getText().toString());
                task.save();
                finish();

                return true;

            case R.id.delete:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
