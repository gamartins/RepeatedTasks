package br.com.martinsdev.repeatedtaks.model;

import com.orm.SugarRecord;

import java.util.Calendar;

/**
 * Created by gabriel on 10/4/16.
 */
public class Task extends SugarRecord {
    public static final int DAILY = 0, WEEKLY = 1, MONTHLY = 2;

    private String name;
    private String details;
    private boolean checked;
    private Calendar nextRepetition;
    private int typeRepetition, calendarStartDay;

    public Task(){

    }

    public Task(String name, int typeRepetition, int calendarStartDay) {
        this.name = name;
        this.checked = false;
        this.typeRepetition = typeRepetition;
        this.calendarStartDay = calendarStartDay;
        this.nextRepetition = getNextRepetition();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void updateNextRepetition() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);

        if (calendar.after(nextRepetition) || nextRepetition == null) {
            if (typeRepetition == DAILY) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                this.nextRepetition = calendar;
                return;
            }

            if (typeRepetition == WEEKLY) {

                calendar.add(Calendar.WEEK_OF_YEAR, 1);
                calendar.set(Calendar.DAY_OF_WEEK, this.calendarStartDay);
                this.nextRepetition = calendar;
                return;
            }

            if (typeRepetition == MONTHLY) {
                calendar.set(Calendar.DAY_OF_MONTH, this.calendarStartDay);
                calendar.add(Calendar.MONTH, 1);
                this.nextRepetition = calendar;
            }
        }

    }

    public Calendar getNextRepetition() {
        if (nextRepetition == null) {
            updateNextRepetition();
        }

        return nextRepetition;
    }
}
