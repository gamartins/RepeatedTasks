package br.com.martinsdev.repeatedtaks.model;

/**
 * Created by gabriel on 10/4/16.
 */
public class Task {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
