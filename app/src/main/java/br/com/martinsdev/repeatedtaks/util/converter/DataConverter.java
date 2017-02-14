package br.com.martinsdev.repeatedtaks.util.converter;

import android.content.Context;
import android.content.res.Resources;

import java.util.Calendar;

import br.com.martinsdev.repeatedtaks.R;
import br.com.martinsdev.repeatedtaks.model.Task;

/**
 * Created by Gabriel on 13/02/2017.
 */

public class DataConverter {

    public static int stringToWeekDay(String data, Context context){
        Resources res = context.getResources();

        if (data.equals(res.getString(R.string.seg_acron))) {
            return Calendar.MONDAY;
        }

        if (data.equals(res.getString(R.string.ter_acron))) {
            return Calendar.TUESDAY;
        }

        if (data.equals(res.getString(R.string.qua_acron))) {
            return Calendar.WEDNESDAY;
        }

        if (data.equals(res.getString(R.string.qui_acron))) {
            return Calendar.THURSDAY;
        }

        if (data.equals(res.getString(R.string.sex_acron))) {
            return Calendar.FRIDAY;
        }

        if (data.equals(res.getString(R.string.sab_acron))) {
            return Calendar.SATURDAY;
        }

        if (data.equals(res.getString(R.string.dom_acron))) {
            return Calendar.SUNDAY;
        }

        return Calendar.MONDAY;
    }

    public static int stringToTaskType(String data, Context context) {
        Resources res = context.getResources();
        if (data.equals(res.getString(R.string.daily))){
            return Task.DAILY;
        }

        if (data.equals(res.getString(R.string.weekly))) {
            return Task.WEEKLY;
        }

        if (data.equals(res.getString(R.string.monthly))) {
            return Task.MONTHLY;
        }

        return Task.DAILY;
    }
}
