package Main;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Worker implements Callable<ResultObject> {

    private NumberSetList numberSetList;
    private Calculation calculation;
    private int timeIndicator;

    public Worker(Calculation calculation, NumberSetList numberSetList) {
        this.calculation = calculation;
        this.numberSetList = numberSetList;

    }

    @Override
    public ResultObject call() {
        // Worker empfängt Daten zur Ausführung der Calculation

        // Lesen des Calculation-Types und Aufruf des entsprechenden Workers
        ICalculator calculator;
        switch (calculation.getType()){
            case "yoy":
                calculator = new YoYCalculator(calculation, numberSetList);
                System.out.println("YoYCalculator gestartet.");
                break;
            case "maxdd":
                calculator = new MaxDDCalculator(calculation, numberSetList);
                System.out.println("MaxDDCalculator gestartet.");
                break;
            default:
                calculator = new NullCalculator();
        }

        return calculator.calculate();
    }
}
