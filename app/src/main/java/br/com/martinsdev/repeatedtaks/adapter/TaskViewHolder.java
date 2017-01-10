package br.com.martinsdev.repeatedtaks.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import br.com.martinsdev.repeatedtaks.R;

/**
 * Created by gabriel on 10/4/16.
 */
public class TaskViewHolder extends RecyclerView.ViewHolder {
    public TextView taskName;
    public CheckBox checkBoxName;

    public TaskViewHolder(View itemView) {
        super(itemView);

        taskName = (TextView) itemView.findViewById(R.id.item_nome);
        checkBoxName = (CheckBox) itemView.findViewById(R.id.item_checkbox);
        checkBoxName.setChecked(false);
    }
}
