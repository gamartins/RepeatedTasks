package br.com.martinsdev.repeatedtaks.model;

import com.orm.SugarRecord;

/**
 * Created by gabriel on 10/4/16.
 */
public class Task extends SugarRecord {
    private String name;
    private String details;
    private boolean checked;

    public Task(){

    }

    public Task(String name) {
        this.name = name;
        this.checked = false;
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
}
