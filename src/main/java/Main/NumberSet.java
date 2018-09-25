package Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class NumberSet {
    /*
    Ein Datums-Objekt, in dem alle Werte gespeichert sind
    */
    private LocalDate numberSetDate = null;
    private ArrayList<Float> numberSetValues = new ArrayList<>();
    private Map<String, Integer> indices;

    public NumberSet(Map<String,Integer> indices) {
        this.indices = indices;
    }

    public NumberSet() {
    }

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

    public Map<String, Integer> getIndices() {
        return indices;
    }

    public void setIndices(Map<String, Integer> indices) {
        this.indices = indices;
    }
}

