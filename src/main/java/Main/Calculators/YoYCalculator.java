package Main.Calculators;

import Main.Calculation;
import Main.NumberSet;
import Main.NumberSetList;
import Main.ResultObject;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class YoYCalculator implements ICalculator {

    private Calculation calculation;
    private NumberSetList numberSetList;
    private List<NumberSet> numberSets;
    private LocalDate baseNumberSetDate;
    private int timeIndicator;
    private NumberSet currentNumberSet;



    public YoYCalculator(Calculation calculation, NumberSetList numberSetList) {
        this.calculation = calculation;
        this.numberSetList = numberSetList;
    }

    @Override
    public ResultObject calculate() {

        numberSets = numberSetList.getNumberSetList();
        // TODO LocalDate targetDate aus Calculation auslesen
        // int bondIndizes aus Calculation lesen
        List<String> indices = calculation.getIndices();
        // int timeIndicator aus Calculation lesen
        timeIndicator = calculation.getTimeIndicator();
        Map<String, Integer> map = numberSets.get(0).getIndices();  // Indices aus erstem NumberSet lesen
        ResultObject results = new ResultObject();
        for (String indexName : indices) {
            int bondIndex = map.get(indexName);
            NumberSet baseNumberSet = numberSets.get(numberSets.size() - 1); // letztes Datum holen
            NumberSet yoyNumberSet = null;
            baseNumberSetDate = baseNumberSet.getDate();

            // ist das benötigte Zieldatum in der Setlist vorhanden?
            //if (baseNumberSetDate.minusYears(timeIndicator).compareTo(lastPossibleCalculationDate()) >=0 ) {
                for (NumberSet currentNumberSet : numberSets) {
                    if (currentNumberSet.getDate().isEqual(baseNumberSetDate.minusYears(timeIndicator))) {
                        yoyNumberSet = currentNumberSet;
                    }
                }
           // }

            if (yoyNumberSet != null) {
                float startwert = baseNumberSet.getValues(bondIndex);
                float endwert = yoyNumberSet.getValues(bondIndex);
                float profitYoY = (startwert / endwert) - 1;
                results.putResults(indexName, profitYoY);
            }
        }

        // Console Reporting
//        System.out.println("YOY: Das targetDate ist: " + targetDate);
//        System.out.println("YOY: Der Startwert ist: " + startwert);
//        System.out.println("YOY: Der Endwert ist: " + endwert);
//        System.out.println("YOY: Die Performance YoY ist: " + profitYoY * 100 + " %.");

        return results;
    }

    private LocalDate lastPossibleCalculationDate() {

        // erstes datum + time indicator = letztes mögliches berechnungsdatum
        LocalDate lastPossibleCalcDate = numberSets.get(0).getDate().plusYears(timeIndicator);
        return lastPossibleCalcDate;
    }


}
