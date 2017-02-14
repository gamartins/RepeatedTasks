package br.com.martinsdev.repeatedtaks.util.alarm;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import br.com.martinsdev.repeatedtaks.model.SingletonTaskList;

/**
 * Created by gabriel on 10/14/16.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())){
            Alarm.setAlarm(context,4,0,AlarmManager.INTERVAL_DAY);
        }

        SingletonTaskList.resetTodayTasks();

        Toast.makeText(context, "Tarefas resetadas", Toast.LENGTH_SHORT).show();
    }
}
