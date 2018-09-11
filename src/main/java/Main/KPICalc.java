package Main;

import java.time.LocalDate;
import java.util.ArrayList;


public class KPICalc {

    private ArrayList<NumberSet> numberSetList;
    private LocalDate targetDate;
    private LocalDate currentNumberSetDate;
    float value2;
    float yield;
    float vola;
    float MaxDD;
    float MaxDD_Final;
	float value_test;
    float value_max_test = 0;
    

    ArrayList<Float> YieldSetList = new ArrayList<>();

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


    public Float calculateYoYProfit(String startDate, int index, int indicator) {

        DateConverter dateConverter = new DateConverter();
        NumberSet startNumberSet = new NumberSet();
        NumberSet yearAgoNumberSet = new NumberSet();
        LocalDate startNumberSetDate;
        targetDate = dateConverter.formatDate(startDate);

        for (NumberSet currentNumberSet : numberSetList) { //
            currentNumberSetDate = currentNumberSet.getDate();

            if (currentNumberSetDate.equals(targetDate)) {
                startNumberSet = currentNumberSet;
                startNumberSet.getDate();
                break;
            }
        }

        for (NumberSet currentNumberSet : numberSetList) {
            currentNumberSetDate = currentNumberSet.getDate();
            targetDate = startNumberSet.getDate();
            targetDate = targetDate.minusYears(indicator);


            if (currentNumberSetDate.equals(targetDate)) {
                yearAgoNumberSet = currentNumberSet;
                System.out.println("Das targetDate ist: " + targetDate);
                break;
            }
        }
        float startwert = startNumberSet.getValues(index);
        System.out.println("Der Startwert ist: " + startwert);
        float endwert = yearAgoNumberSet.getValues(index);
        System.out.println("Der Endwert ist: " + endwert);
        float profitYoY = (startwert / endwert) - 1;
        System.out.println("Die Performance YoY ist: " + profitYoY * 100 + "%.");
        return profitYoY;
    }

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


    public float calculate_MaxDD(String startDate ,int index,int indicator) {
    	
    	
        MaxDD_Final = 0;
        value2 = 0;
    	DateConverter dateConverter = new DateConverter();
    	targetDate = dateConverter.formatDate(startDate);
    	targetDate = targetDate.minusYears(indicator);
    	

  
        for (NumberSet value : numberSetList) {
        	
        	
        	currentNumberSetDate = value.getDate();
        	
        	
        	if(currentNumberSetDate.compareTo(targetDate)>=0) {
        	
        	
        	value_test= value.getValues(index);
        	
        	if (value_test>value_max_test) {
        		
        		value_max_test = value.getValues(index);
        		
        		
        	}
        	
        	
        	MaxDD = (value_test/value_max_test)-1;
    
        	if (MaxDD<MaxDD_Final) {
        		;
        		MaxDD_Final = MaxDD;
        		
        	}
        	}
        }  
        System.out.println("Der Max Wert ist:"+ value_max_test);
        System.out.println("Der Max DD ist:"+ MaxDD_Final);
        System.out.println("Das Zieldatum ist" + targetDate);
        return MaxDD_Final;
        
    }


    // tatsächliche Vola Berechnung (tiefergehende Recherche notwendig)
    public float calculate_Vola() {


        return vola;
    }


//endofclass
}
