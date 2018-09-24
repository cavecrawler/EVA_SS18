package Main;

import java.time.LocalDate;
import java.util.ArrayList;

public class YoYCalculator implements ICalculator {

    private Calculation calculation;
    private ArrayList<NumberSet> numberSetList;
    private LocalDate currentNumberSetDate;
    private LocalDate targetDate;
    private LocalDate baseDate;
    private int timeIndicator;
    private int workerID;
    private int bondIndex;
    private NumberSet currentNumberSet;


    public YoYCalculator(Calculation calculation, NumberSetList numberSetList) {
        this.calculation = calculation;
        this.numberSetList = numberSetList;
        ResultObject results = new ResultObject();
    }

    @Override
    public ResultObject calculate() {

        // LocalDate targetDate aus Calculation auslesen
        // int bondIndex aus Calculation lesen
        // int timeIndicator aus Calculation lesen

        NumberSet baseNumberSet = new NumberSet();
        NumberSet yoyNumberSet = new NumberSet();
        this.baseDate = targetDate;
        this.workerID = workerID;
        this.timeIndicator = timeIndicator;

        for (NumberSet currentNumberSet : numberSetList) {
            currentNumberSetDate = currentNumberSet.getDate(); // Datum des aktuellen Numbersets speichern

            if (currentNumberSetDate.equals(targetDate)) { // jedes NumberSet wird mit Zieldatum verglichen
                baseNumberSet = currentNumberSet; // bei erfolg wird das aktuelle NumberSet zum StartNumberSet
                break;
            }
        }

        targetDate = baseNumberSet.getDate().minusYears(timeIndicator); // yoy Datum bestimmen

        if (targetDate.isAfter(lastPossibleCalculationDate())) { // Sicherung, dass yoyDate > LPC Date
            for (NumberSet currentNumberSet : numberSetList) { // NumberSet mit yoyDatum finden
                currentNumberSetDate = currentNumberSet.getDate();

                if (currentNumberSetDate.equals(targetDate)) {
                    yoyNumberSet = currentNumberSet;
                    System.out.println("Worker " + workerID + ": YOY: Das targetDate ist: " + targetDate);
                    break;
                }
            }
        }

        float startwert = baseNumberSet.getValues(bondIndex);
        System.out.println("Worker " + workerID + ": YOY: Der Startwert ist: " + startwert);
        float endwert = yoyNumberSet.getValues(bondIndex);
        System.out.println("Worker " + workerID + ": YOY: Der Endwert ist: " + endwert);
        float profitYoY = (startwert / endwert) - 1;
        // TODO: Ausgabe auf 3 Stellen nach dem Komma begrenzen
        System.out.println("Worker " + workerID + ": YOY: Die Performance YoY ist: " + profitYoY * 100 + " %.");

        // TODO: ADD FLOAT TO RESULT ARRAY
        return profitYoY;
    }
    //yoyBerechnung


}
