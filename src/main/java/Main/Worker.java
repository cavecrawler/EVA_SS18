package Main;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Worker implements Callable<ResultObject> {

    private NumberSetList numberSetList;
    private Calculation calculation;
    private int timeIndicator;
    private ResultObject results;

    public Worker(Calculation calculation, NumberSetList numberSetList) {
        this.calculation = calculation;
        this.numberSetList = numberSetList;

    }

    @Override
    public ResultObject call() {


        // Worker empfängt Daten zur Ausführung der Calculation

        // Lesen des Calculation-Types und Aufruf des entsprechenden Workers
        String calculatorType = calculation.getType();

        switch (calculatorType) {
            case "yoy":
                YoYCalculator yoyCalc = new YoYCalculator(calculation, numberSetList);
                results = yoyCalc.calculate();
                break;
            case "maxdd":
                MaxDDCalculator maxDDCalc = new MaxDDCalculator(calculation, numberSetList);
                results = maxDDCalc.calculate();
                break;
        }
        return results;
    }
}
