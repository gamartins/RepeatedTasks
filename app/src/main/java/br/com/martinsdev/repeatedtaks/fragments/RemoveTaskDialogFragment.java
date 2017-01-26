package br.com.martinsdev.repeatedtaks.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import br.com.martinsdev.repeatedtaks.model.SingletonTaskList;
import br.com.martinsdev.repeatedtaks.model.Task;

/**
 * Created by Gabriel on 10/01/2017.
 */

public class RemoveTaskDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle bundle = getArguments();
        final int position = bundle.getInt("position");

        builder.setMessage("Deseja deletar essa tarefa ?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Task task = SingletonTaskList.getAllTasks().get(position);
                        SingletonTaskList.removeTask(task);
                        Toast.makeText(getActivity(), "Tarefa deletada", Toast.LENGTH_SHORT).show();
                        getFragmentManager().popBackStack();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RemoveTaskDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
