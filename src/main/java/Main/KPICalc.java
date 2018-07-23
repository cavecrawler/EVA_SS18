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
        System.out.println("Kein passendes NumberSet vorhanden.");
        return targetNumberSet;
    }


//endofclass
}

