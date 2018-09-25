package Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MaxDDCalculator implements ICalculator {

    private Calculation calculation;
    private ArrayList<NumberSet> numberSets;


    private LocalDate currentNumberSetDate;


    private float MaxDD;
    private float value_test;
    private float value_max_test = 0;


    public MaxDDCalculator(Calculation calculation, NumberSetList numberSetList) {
        this.calculation = calculation;
        this.numberSets = numberSetList.getNumberSetList();
    }

    @Override
    public ResultObject calculate() {

        List<String> indices = calculation.getIndices();
        Map<String, Integer> map = numberSets.get(0).getIndices();
        NumberSet baseNumberSet = numberSets.get(numberSets.size() - 1); // letztes Datum holen
        LocalDate baseDate = baseNumberSet.getDate();
        ResultObject resultObject = new ResultObject();
        float MaxDD_Final = 0;
        for (String indexName : indices) {
            int bondIndex = map.get(indexName);

            for (NumberSet value : numberSets) {

                currentNumberSetDate = value.getDate();

                if (currentNumberSetDate.compareTo(baseDate) >= 0) {
                    value_test = value.getValues(bondIndex);

                    if (value_test > value_max_test) {
                        value_max_test = value.getValues(bondIndex);
                    }

                    MaxDD = (value_test / value_max_test) - 1;

                    if (MaxDD < MaxDD_Final) {
                        MaxDD_Final = MaxDD;
                    }
                }
            }
//        System.out.println("Worker " + workerID + ": MAX: Das Zieldatum ist: " + this.baseDate);
//        System.out.println("Worker " + workerID + ": MAX: Der Max Wert ist: " + value_max_test);
//        System.out.println("Worker " + workerID + ": MAX: Der Max DD ist: " + MaxDD_Final);
            resultObject.putResults(indexName, MaxDD_Final);
        }
        return resultObject;
    }
}



