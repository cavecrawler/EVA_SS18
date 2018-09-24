package Main;

import java.util.ArrayList;
import java.util.concurrent.*;

public class TaskMaster {

    private NumberSetList numberSetList;
    private ArrayList<Calculation> calculations;
    private Calculation currentCalculation;
    private ArrayList<Future<ResultObject>> results;

    public TaskMaster(NumberSetList numberSetList, ArrayList<Calculation> calculations) {

        this.numberSetList = numberSetList;
        this.calculations = calculations;
    }

    public ArrayList<Float> startThreads() {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (currentCalculation : calculations) {

            // für jede Calculation wird ein Worker gestartet; das Ergebnis wird in einem Result Objekt gespeichert
            Future<ResultObject> future = executor.submit(new Worker(currentCalculation, numberSetList));

            // Future Objekt wird in Results-Array geschrieben, die Ergebnisse werden gesammelt
            results.add(future);

        }

    }
}
