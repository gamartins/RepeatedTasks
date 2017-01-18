package br.com.martinsdev.repeatedtaks;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.com.martinsdev.repeatedtaks.fragments.NewTaskDialogFragment;
import br.com.martinsdev.repeatedtaks.fragments.RemoveTaskDialogFragment;
import br.com.martinsdev.repeatedtaks.fragments.TaskListFragment;

public class NewMainActivity extends AppCompatActivity
        implements NewTaskDialogFragment.NewTaskDialogListener, RemoveTaskDialogFragment.RemovedTask {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);

        // Adicionando o fragment na Activity
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_principal, new TaskListFragment());
        transaction.commit();
    }

    @Override
    public void onCreatedTask(String taskName) {
        TaskListFragment taskListFragment;
        taskListFragment = (TaskListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_principal);

        // Noficação de tarefa criada com sucesso
        String msg = "Tarefa '" + taskName + "' criada.";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        taskListFragment.updateList();
    }

    @Override
    public void onRemovedTask() {
        // Trocando o fragment
        TaskListFragment taskListFragment = new TaskListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_principal, taskListFragment);
        transaction.commit();

        // Atualizando a lista
//        taskListFragment = (TaskListFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.fragment_principal);
//        taskListFragment.updateList();

        Toast.makeText(this, "Tarefa deletada", Toast.LENGTH_SHORT).show();
    }
}
