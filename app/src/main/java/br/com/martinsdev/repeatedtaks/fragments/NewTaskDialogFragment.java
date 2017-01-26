package br.com.martinsdev.repeatedtaks.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import br.com.martinsdev.repeatedtaks.R;
import br.com.martinsdev.repeatedtaks.model.SingletonTaskList;
import br.com.martinsdev.repeatedtaks.model.Task;

/**
 * Created by gabriel on 11/21/16.
 */

public class NewTaskDialogFragment extends DialogFragment {
    private NewTaskDialogListener callback;
    private EditText taskName;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_task, null);
        taskName = (EditText) view.findViewById(R.id.input_subject_name);

        builder.setView(view)
                .setTitle("Nova Tarefa")
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String taskNameString = taskName.getText().toString();

                        // Atualizando a lista de tarefas
                        SingletonTaskList.addTask(new Task(taskNameString));

                        callback.onCreatedTask(taskNameString);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NewTaskDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (NewTaskDialogListener) context;
        } catch (ClassCastException e) {
            Log.d("NewSubjectFragment", "Activity doesn't implement the NewTaskDialogListener interface");
        }
    }

    public interface NewTaskDialogListener {
        void onCreatedTask(String taskName);
    }

}
