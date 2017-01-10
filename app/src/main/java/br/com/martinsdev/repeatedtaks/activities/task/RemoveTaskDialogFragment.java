package br.com.martinsdev.repeatedtaks.activities.task;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Gabriel on 10/01/2017.
 */

public class RemoveTaskDialogFragment extends DialogFragment {
    private RemovedTask callback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Deseja deletar essa tarefa ?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.OnRemovedTask(true);
                        Toast.makeText(getActivity(), "Tarefa deletada", Toast.LENGTH_SHORT).show();
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
        void OnRemovedTask(Boolean result);
    }
}
