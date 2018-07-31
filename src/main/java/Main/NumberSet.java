package Main;

import java.time.LocalDate;
import java.util.ArrayList;

public class NumberSet {
    /*
    Ein Datums-Objekt, in dem alle Werte gespeichert sind
    */
    private LocalDate numberSetDate = null;
    private ArrayList<Float> numberSetValues = new ArrayList<>();

    public void setDate(LocalDate date) {
        this.numberSetDate = date;
    }

    public void setValues(float i) {
        numberSetValues.add(i);
    }

    public LocalDate getDate() {
        if (numberSetDate != null) {

            return numberSetDate;
        }

        return null;
    }

    public float getValues(int j) {
        return numberSetValues.get(j);
    }

    public void printDate() {
        System.out.println("\nDas Datum dieses NumberSets ist: " + numberSetDate);
    }

    public boolean checkArrayIntegrity() {
        boolean check = numberSetValues.isEmpty();
        return check;
    }
}

