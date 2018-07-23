package Main;

import java.time.LocalDate;
import java.util.ArrayList;


public class KPICalc {

    private ArrayList<NumberSet> NumberSetList;
    private LocalDate targetDate;
    private LocalDate currentNumberSetDate;


    public KPICalc(ArrayList<NumberSet> NumberSetList) {
        this.NumberSetList = NumberSetList;
    }

    public NumberSet getTargetDate(String startDate) {
        DateConverter dateConverter = new DateConverter();
        NumberSet targetNumberSet = new NumberSet();
        targetDate = dateConverter.formatDate(startDate);

        for (NumberSet currentNumberSet : NumberSetList) {
            currentNumberSetDate = currentNumberSet.getDate();
            if (currentNumberSetDate.equals(targetDate)) {
                System.out.println("Das Zieldatum ist: " + currentNumberSetDate);
                targetNumberSet = currentNumberSet;
                break;
            }
        }
        return targetNumberSet;
    }

    public Float calculateYoYProfit(String startDate, int i) {
        DateConverter dateConverter = new DateConverter();
        NumberSet startNumberSet = new NumberSet();
        NumberSet yearAgoNumberSet = new NumberSet();
        LocalDate startNumberSetDate;
        targetDate = dateConverter.formatDate(startDate);
        for (NumberSet currentNumberSet : NumberSetList) {
            currentNumberSetDate = currentNumberSet.getDate();
            if (currentNumberSetDate.equals(targetDate)) {
                startNumberSet = currentNumberSet;
                startNumberSetDate = startNumberSet.getDate();
                break;
            }
        }
        for (NumberSet currentNumberSet : NumberSetList) {
            currentNumberSetDate = currentNumberSet.getDate();
            targetDate = startNumberSet.getDate();
            targetDate = targetDate.minusYears(1); //ersetzen durch j, sobald mehr daten vorliegen
            if (currentNumberSetDate.equals(targetDate)) {
                yearAgoNumberSet = currentNumberSet;
                break;
            }
        }
        float startwert = startNumberSet.getValues(i);
        float endwert = yearAgoNumberSet.getValues(i);
        float profitYoY = (startwert / endwert) - 1;
        return profitYoY;
    }


//endofclass
}

