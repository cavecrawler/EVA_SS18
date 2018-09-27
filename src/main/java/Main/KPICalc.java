package Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class KPICalc {

    private List<NumberSet> numberSetList;
    private LocalDate baseDate;
    private LocalDate currentNumberSetDate;
    private int timeIndicator;
    private float value2;
    private float yield;
    private float vola;
    private float MaxDD;
    private float MaxDD_Final;
    private float value_test;
    private float value_max_test = 0;
    private int workerID;

    ArrayList<Float> YieldSetList = new ArrayList<>();

    public KPICalc(NumberSetList numberSetList) {

        this.numberSetList = numberSetList.getNumberSetList();
    }

    // NumberSet nach TargetDate zurückgeben
    public NumberSet getTargetDate(String startDate) {
        DateConverter dateConverter = new DateConverter();
        NumberSet targetNumberSet = new NumberSet();
        baseDate = dateConverter.formatDate(startDate);

        for (NumberSet currentNumberSet : numberSetList) {
            currentNumberSetDate = currentNumberSet.getDate();

            if (currentNumberSetDate.equals(baseDate)) {
                System.out.println("Das Zieldatum ist: " + currentNumberSetDate);
                targetNumberSet = currentNumberSet;
                break;
            }
        }

        return targetNumberSet;
    }


//    public float calculateYoYProfit(int workerID, LocalDate targetDate, int bondIndex, int timeIndicator) {
//
//        NumberSet baseNumberSet = new NumberSet();
//        NumberSet yoyNumberSet = new NumberSet();
//        this.baseDate = targetDate;
//        this.workerID = workerID;
//        this.timeIndicator = timeIndicator;
//
//        for (NumberSet currentNumberSet : numberSetList) {
//            currentNumberSetDate = currentNumberSet.getDate(); // Datum des aktuellen Numbersets speichern
//
//            if (currentNumberSetDate.equals(targetDate)) { // jedes NumberSet wird mit Zieldatum verglichen
//                baseNumberSet = currentNumberSet; // bei erfolg wird das aktuelle NumberSet zum StartNumberSet
//                break;
//            }
//        }
//
//        targetDate = baseNumberSet.getDate().minusYears(timeIndicator); // yoy Datum bestimmen
//
//        if (targetDate.isAfter(lastPossibleCalculationDate())) { // Sicherung, dass yoyDate > LPC Date
//            for (NumberSet currentNumberSet : numberSetList) { // NumberSet mit yoyDatum finden
//                currentNumberSetDate = currentNumberSet.getDate();
//
//                if (currentNumberSetDate.equals(targetDate)) {
//                    yoyNumberSet = currentNumberSet;
//                    System.out.println("Worker " + workerID + ": YOY: Das targetDate ist: " + targetDate);
//                    break;
//                }
//            }
//        }
//
//        float startwert = baseNumberSet.getValues(bondIndex);
//        System.out.println("Worker " + workerID + ": YOY: Der Startwert ist: " + startwert);
//        float endwert = yoyNumberSet.getValues(bondIndex);
//        System.out.println("Worker " + workerID + ": YOY: Der Endwert ist: " + endwert);
//        float profitYoY = (startwert / endwert) - 1;
//
//        System.out.println("Worker " + workerID + ": YOY: Die Performance YoY ist: " + profitYoY * 100 + " %.");
//        return profitYoY;
//    }

// neuer Commit FM


    // Funktion die einen Array (FLOAT) für die Tagesrenditen einer Spalte ausgibt

    public ArrayList<Float> caculate_daily_yield() {

        for (NumberSet value : numberSetList) {

            if (value2 != 0.0) {
                yield = (value.getValues(0) / value2) - 1;
                YieldSetList.add(yield);
            }

            value2 = value.getValues(0);
        }

        return YieldSetList;
    }

//    public float calculate_MaxDD(int workerID, LocalDate baseDate, int bondIndex, int timeIndicator) {
//
//        this.workerID = workerID;
//        MaxDD_Final = 0;
//        value2 = 0;
//        this.baseDate = baseDate;
//        this.baseDate = this.baseDate.minusYears(timeIndicator);
//
//        for (NumberSet value : numberSetList) {
//
//            currentNumberSetDate = value.getDate();
//
//            if (currentNumberSetDate.compareTo(this.baseDate) >= 0) {
//                value_test = value.getValues(bondIndex);
//
//                if (value_test > value_max_test) {
//                    value_max_test = value.getValues(bondIndex);
//                }
//
//                MaxDD = (value_test / value_max_test) - 1;
//
//                if (MaxDD < MaxDD_Final) {
//                    MaxDD_Final = MaxDD;
//                }
//            }
//        }
//
//        System.out.println("Worker " + workerID + ": MAX: Das Zieldatum ist: " + this.baseDate);
//        System.out.println("Worker " + workerID + ": MAX: Der Max Wert ist: " + value_max_test);
//        System.out.println("Worker " + workerID + ": MAX: Der Max DD ist: " + MaxDD_Final);
//
//        return MaxDD_Final;
//    }


//    public float calculate_Vola() {
//        return vola;
//    }

    private LocalDate lastPossibleCalculationDate() {

        // erstes datum + time indicator = letztes mögliches berechnungsdatum
        LocalDate lastPossibleCalcDate = numberSetList.get(0).getDate().plusYears(timeIndicator);
        return lastPossibleCalcDate;
    }

}//endofclass

