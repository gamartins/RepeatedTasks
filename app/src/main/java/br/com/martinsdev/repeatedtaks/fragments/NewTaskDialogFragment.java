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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.martinsdev.repeatedtaks.R;
import br.com.martinsdev.repeatedtaks.model.SingletonTaskList;
import br.com.martinsdev.repeatedtaks.model.Task;
import br.com.martinsdev.repeatedtaks.util.converter.DataConverter;

/**
 * Created by gabriel on 11/21/16.
 */

public class NewTaskDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {
    private NewTaskDialogListener callback;
    private EditText taskName;
    private Spinner repetitionsSpinner, startDateSpinner;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_task, null);
        taskName = (EditText) view.findViewById(R.id.input_subject_name);

        startDateSpinner = (Spinner) view.findViewById(R.id.spinner_start_date);
        repetitionsSpinner = (Spinner) view.findViewById(R.id.spinner_repetition);

        repetitionsSpinner.setOnItemSelectedListener(this);

        builder.setView(view)
                .setTitle("Nova Tarefa")
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String taskNameString = taskName.getText().toString();
                        String repetition = repetitionsSpinner.getSelectedItem().toString();
                        int typeRepetition = DataConverter.stringToTaskType(repetition, getContext());
                        int calendarStartDay = 0;

                        // Preenchendo com valores diferentes de acordo com o tipo de repetição
                        switch (typeRepetition) {
                            case Task.DAILY:
                                calendarStartDay = startDateSpinner.getSelectedItemPosition();
                                break;
                            case Task.WEEKLY:
                                calendarStartDay = DataConverter
                                        .stringToWeekDay(startDateSpinner
                                                .getSelectedItem().toString(), getContext());
                                break;
                            case Task.MONTHLY:
                                calendarStartDay = startDateSpinner.getSelectedItemPosition() + 1;
                                break;
                        }

                        String msg = "Nome: " + taskNameString + " | Tipo: " + typeRepetition
                                + " | Inicio: " + String.valueOf(calendarStartDay);
                        Log.d("RepeatedTasks", msg);

                        // Atualizando a lista de tarefas
                         SingletonTaskList.addTask(new Task(taskNameString, typeRepetition, calendarStartDay));
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startDateSpinner.setEnabled(false);
                ArrayAdapter<CharSequence> daily = ArrayAdapter.createFromResource(getActivity(),
                        R.array.starter_date_daily, android.R.layout.simple_spinner_item);
                startDateSpinner.setAdapter(daily);
                break;
            case 1:
                startDateSpinner.setEnabled(true);
                ArrayAdapter<CharSequence> weekly = ArrayAdapter.createFromResource(getActivity(),
                        R.array.starter_date_weekly, android.R.layout.simple_spinner_item);
                startDateSpinner.setAdapter(weekly);
                break;
            case 2:
                startDateSpinner.setEnabled(true);
                ArrayAdapter<CharSequence> monthly = ArrayAdapter.createFromResource(getActivity(),
                        R.array.starter_date_monthly, android.R.layout.simple_spinner_item);
                startDateSpinner.setAdapter(monthly);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    public interface NewTaskDialogListener {
        void onCreatedTask(String taskName);
    }

}
