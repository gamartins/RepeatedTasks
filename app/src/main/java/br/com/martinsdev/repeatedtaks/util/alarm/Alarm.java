package br.com.martinsdev.repeatedtaks.util.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by gabriel on 10/14/16.
 */

public class Alarm {
    private static Intent intent;
    private static AlarmManager alarmManager;
    private static PendingIntent alarmIntent;

    public static void setAlarm(Context context, int hour_of_day, int minute, long repetition){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() );
        calendar.set(Calendar.HOUR_OF_DAY, hour_of_day);
        calendar.set(Calendar.MINUTE, minute);

        intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC,
        calendar.getTimeInMillis(), repetition, alarmIntent);
    }

    public static boolean isSet(Context context){
        intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE);

        if (alarmIntent == null){
            return false;
        } else {
            return true;
        }
    }
}
