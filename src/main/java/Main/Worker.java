package Main;

import Main.Calculators.ICalculator;
import Main.Calculators.MaxDDCalculator;
import Main.Calculators.NullCalculator;
import Main.Calculators.YoYCalculator;
import java.util.concurrent.Callable;

public class Worker implements Callable<ResultObject> {

    private NumberSetList numberSetList;
    private Calculation calculation;

    public Worker(Calculation calculation, NumberSetList numberSetList) {
        // Worker empfängt Daten zur Ausführung der Calculation
        this.calculation = calculation;
        this.numberSetList = numberSetList;
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }


    @Override
    public ResultObject call() {
        // Lesen des Calculation-Types und Aufruf des entsprechenden Workers
        ICalculator calculator;

        switch (calculation.getType()) {
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
