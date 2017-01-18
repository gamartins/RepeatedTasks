package br.com.martinsdev.repeatedtaks.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import br.com.martinsdev.repeatedtaks.R;
import br.com.martinsdev.repeatedtaks.model.SingletonTaskList;
import br.com.martinsdev.repeatedtaks.model.Task;

public class TaskDetailedFragment extends Fragment {
    private EditText taskName, taskDetails;
    private Task task;
    private int position;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Poder adicionar itens ao menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed_task, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Recebendo o elemento na posição definida em TaskAdapter
        Bundle bundle = this.getArguments();
        position = bundle.getInt("position");

        // Lendo a tarefa no banco de dados
        task = SingletonTaskList.getTasks().get(position);

        // Inflando os elementos do layout
        taskName = (EditText) view.findViewById(R.id.task_name);
        taskName.setText(task.getName());
        taskDetails = (EditText) view.findViewById(R.id.task_details);
        taskDetails.setText(task.getDetails());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detailed_task, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_button:
                // Salvando as alterações no banco de dados e definindo mensagem da notificação
                Toast.makeText(getActivity(), R.string.task_saved, Toast.LENGTH_LONG).show();
                task.setName(taskName.getText().toString());
                task.setDetails(taskDetails.getText().toString());
                task.save();

                // Fechando o fragment
                getActivity().getSupportFragmentManager().popBackStack();

                return true;

            case R.id.delete:
                RemoveTaskDialogFragment removeTaskDialog = new RemoveTaskDialogFragment();
                Bundle bundle = new Bundle();

                bundle.putInt("position", position);
                removeTaskDialog.setArguments(bundle);
                removeTaskDialog.show(getActivity().getSupportFragmentManager(), "dialog_remove_task");

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
