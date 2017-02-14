package br.com.martinsdev.repeatedtaks;

import android.app.AlarmManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.martinsdev.repeatedtaks.fragments.NewTaskDialogFragment;
import br.com.martinsdev.repeatedtaks.fragments.TaskListAllFragment;
import br.com.martinsdev.repeatedtaks.fragments.TaskListFragment;
import br.com.martinsdev.repeatedtaks.util.alarm.Alarm;

public class MainActivity extends AppCompatActivity implements NewTaskDialogFragment.NewTaskDialogListener {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView nvDrawer;
    private TaskListFragment taskFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurando o alarme

        if(!Alarm.isSet(this)){
            Alarm.setAlarm(this, 4, 0, AlarmManager.INTERVAL_DAY);
        }

        // Configurando a Toolbar para substituir a ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Localizando a DrawerView utilizada
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToogle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        // Utilizado para navegação entre os itens
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        // Adicionando o fragment na Activity
        taskFragment = new TaskListFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_principal, taskFragment);
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

    // Navegação entre os itens
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        FragmentManager manager;

        switch (menuItem.getItemId()) {
            case R.id.today_tasks:
                // Configurando o fragment e inserindo no fragment principal
                taskFragment = new TaskListFragment();
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_principal, taskFragment).commit();
                break;

            case R.id.all_tasks:
                // Configurando o fragment e inserindo no fragment principal
                TaskListAllFragment listAllFragment = new TaskListAllFragment();
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_principal, listAllFragment).commit();
                break;

            default:
                // Configurando o fragment e inserindo no fragment principal
                taskFragment = new TaskListFragment();
                manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_principal, taskFragment).commit();
                break;
        }

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }
}
