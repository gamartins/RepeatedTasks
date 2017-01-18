package br.com.martinsdev.repeatedtaks.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import br.com.martinsdev.repeatedtaks.model.SingletonTaskList;
import br.com.martinsdev.repeatedtaks.model.Task;

/**
 * Created by Gabriel on 10/01/2017.
 */

public class RemoveTaskDialogFragment extends DialogFragment {
    private RemovedTask callback;

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
                        Task task = SingletonTaskList.getTasks().get(position);
                        SingletonTaskList.removeTask(task);
                        callback.onRemovedTask();
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (RemovedTask) context;
        } catch (ClassCastException e) {
            Log.d("NewSubjectFragment", "Activity doesn't implement the RemovedTask interface");
        }
    }

    public interface RemovedTask {
        void onRemovedTask();
    }
}
