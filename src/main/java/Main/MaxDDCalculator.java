package Main;

import java.time.LocalDate;
import java.util.ArrayList;

public class MaxDDCalculator implements ICalculator {

    private Calculation calculation;
    private ArrayList<NumberSet> numberSetList;

    private LocalDate currentNumberSetDate;
    private LocalDate baseDate;
    private LocalDate targetDate;
    private NumberSet currentNumberSet;

    private int workerID;
    private int bondIndex;
    private int timeIndicator;

    private float value2;
    private float yield;
    private float vola;

    private float MaxDD;
    private float MaxDD_Final;
    private float value_test;
    private float value_max_test = 0;


    public MaxDDCalculator(Calculation calculation, NumberSetList numberSetList) {
        this.calculation = calculation;
        this.numberSetList = numberSetList;
    }

    @Override
    public ResultObject calculate() {


        // get Variables out of Calculation

        this.workerID = workerID;
        MaxDD_Final = 0;
        value2 = 0;
        this.baseDate = baseDate;
        this.baseDate = this.baseDate.minusYears(timeIndicator);

        for (NumberSet value : numberSetList) {

            currentNumberSetDate = value.getDate();

            if (currentNumberSetDate.compareTo(this.baseDate) >= 0) {
                value_test = value.getValues(bondIndex);

                if (value_test > value_max_test) {
                    value_max_test = value.getValues(bondIndex);
                }

                MaxDD = (value_test / value_max_test) - 1;

                if (MaxDD < MaxDD_Final) {
                    MaxDD_Final = MaxDD;
                }
            }
        }

        System.out.println("Worker " + workerID + ": MAX: Das Zieldatum ist: " + this.baseDate);
        System.out.println("Worker " + workerID + ": MAX: Der Max Wert ist: " + value_max_test);
        System.out.println("Worker " + workerID + ": MAX: Der Max DD ist: " + MaxDD_Final);

        return MaxDD_Final;
    }

    private LocalDate lastPossibleCalculationDate() {

        // erstes datum + time indicator = letztes mögliches berechnungsdatum
        LocalDate lastPossibleCalcDate = numberSetList.get(0).getDate().plusYears(timeIndicator);
        numberSetList.
        return lastPossibleCalcDate;
    }
}



