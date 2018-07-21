package Main;

import java.time.LocalDate;
import java.util.ArrayList;

public class NumberSet {
    /*
    Ein Datums-Objekt, in dem alle Werte gespeichert sind
    */
    private LocalDate numberSetDate = null;
    private ArrayList<Float> numberSetValues = new ArrayList<>();

    public void setNumberSetDate(LocalDate date) {
        this.numberSetDate = date;
    }

    public void setNumberSetValues(float i) {
        numberSetValues.add(i);
    }

    public LocalDate getNumberSetDate() {
        if (numberSetDate != null) {
            return numberSetDate;
        } return null;
    }

    public float getNumberSetValues(int j) {
        return numberSetValues.get(j);
    }
}

