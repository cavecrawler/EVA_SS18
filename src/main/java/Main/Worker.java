package Main;

import Main.Calculators.ICalculator;
import Main.Calculators.MaxDDCalculator;
import Main.Calculators.NullCalculator;
import Main.Calculators.YoYCalculator;

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
//        try {
//        Thread.sleep(1000);}
//        catch (Exception e) {}

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
