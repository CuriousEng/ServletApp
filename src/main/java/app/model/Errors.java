package app.model;

import app.entities.FailCause;

import java.util.ArrayList;

public class Errors {
    private int count = 0;
    private ArrayList <FailCause> causes = new ArrayList<>();

    private static Errors instance = new Errors();

    private Errors() {
    }

    public static Errors getInstance(){
        return instance;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<FailCause> getCauses() {
        return causes;
    }

    public void setCauses(FailCause fail) {
        this.causes.add(fail);
    }

    public void clearCauses() {
        this.causes.clear();
    }
}
