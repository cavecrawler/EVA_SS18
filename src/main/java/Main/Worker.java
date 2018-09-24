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

        // Lesen der Calculation
        // lastPossibleCalculationDate() in Calculation schreiben
        // aufruf des entsprechenden Workers
        //



    }

    private void createCalculator() {

        // TODO Switch Case zur entscheidung welcher Worker erzeugt wird

    }

    private LocalDate lastPossibleCalculationDate() {

        // erstes datum + time indicator = letztes mögliches berechnungsdatum
        LocalDate lastPossibleCalcDate = numberSetList.getNumberSetList().get(0).getDate().plusYears(timeIndicator);
        return lastPossibleCalcDate;
    }
}
