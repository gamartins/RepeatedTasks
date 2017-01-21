package br.com.martinsdev.repeatedtaks;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.martinsdev.repeatedtaks.fragments.NewTaskDialogFragment;
import br.com.martinsdev.repeatedtaks.fragments.RemoveTaskDialogFragment;
import br.com.martinsdev.repeatedtaks.fragments.TaskListFragment;

public class MainActivity extends AppCompatActivity
        implements NewTaskDialogFragment.NewTaskDialogListener, RemoveTaskDialogFragment.RemovedTask {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurando a Toolbar para substituir a ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Localizando a DrawerView utilizada
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToogle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        // Adicionando o fragment na Activity
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_principal, new TaskListFragment());
        transaction.commit();
    }

    private ActionBarDrawerToggle setupDrawerToogle(){
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // O botão home/up deve abrir ou fechar o drawer
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
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

        Toast.makeText(this, "Tarefa deletada", Toast.LENGTH_SHORT).show();
    }
}
