package Main;

import java.time.LocalDate;
import java.util.ArrayList;


public class KPICalc {

    private ArrayList<NumberSet> numberSetList;
    private LocalDate targetDate;
    private LocalDate currentNumberSetDate;

    public KPICalc(NumberSetList numberSetList) {
      
        this.numberSetList = numberSetList.getNumberSetList();
    }

    public NumberSet getTargetDate(String startDate) {
        DateConverter dateConverter = new DateConverter();
        NumberSet targetNumberSet = new NumberSet();
        targetDate = dateConverter.formatDate(startDate);

        for (NumberSet currentNumberSet : numberSetList) {
            currentNumberSetDate = currentNumberSet.getDate();

            if (currentNumberSetDate.equals(targetDate)) {
                System.out.println("Das Zieldatum ist: " + currentNumberSetDate);
                targetNumberSet = currentNumberSet;
                break;
            }
        }

        return targetNumberSet;
    }


    public Float calculateYoYProfit(String startDate, int index, int years) {

        DateConverter dateConverter = new DateConverter();
        NumberSet startNumberSet = new NumberSet();
        NumberSet yearAgoNumberSet = new NumberSet();
        LocalDate startNumberSetDate;
        targetDate = dateConverter.formatDate(startDate);

        for (NumberSet currentNumberSet : numberSetList) { //
            currentNumberSetDate = currentNumberSet.getDate();

            if (currentNumberSetDate.equals(targetDate)) {
                startNumberSet = currentNumberSet;
                startNumberSetDate = startNumberSet.getDate();
                break;
            }
        }

        for (NumberSet currentNumberSet : numberSetList) {
            currentNumberSetDate = currentNumberSet.getDate();
            targetDate = startNumberSet.getDate();
            targetDate = targetDate.minusYears(years);


            if (currentNumberSetDate.equals(targetDate)) {
                yearAgoNumberSet = currentNumberSet;
                System.out.println("Das targetDate ist: " + targetDate);
                break;
            }
        }
        float startwert = startNumberSet.getValues(index);
        System.out.println("Der Startwert ist: " +  startwert);
        float endwert = yearAgoNumberSet.getValues(index);
        System.out.println("Der Endwert ist: " + endwert);
        float profitYoY = (startwert / endwert) - 1;
        System.out.println("Die Performance YoY ist: " + profitYoY * 100 + "%.");
        return profitYoY;
    }


//endofclass
}

