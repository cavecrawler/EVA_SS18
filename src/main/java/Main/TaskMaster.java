package Main;

import java.util.ArrayList;
import java.util.concurrent.*;

public class TaskMaster {

    private NumberSetList numberSetList;
    private ArrayList<Calculation> calculations;

    public TaskMaster(NumberSetList numberSetList, ArrayList<Calculation> calculations) {

        this.numberSetList = numberSetList;
        this.calculations = calculations;
    }

    public void startThreads() throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        ArrayList<Future<ResultObject>> futureResults = new ArrayList<Future<ResultObject>>();

        for (Calculation currentCalculation : calculations) {

            // für jede Calculation wird ein Worker gestartet; das Ergebnis wird in einem Result Objekt gespeichert
            Future<ResultObject> future = executor.submit(new Worker(currentCalculation, numberSetList));

            // Future Objekt wird in Results-Array geschrieben, die Ergebnisse werden gesammelt
            futureResults.add(future);

        }

        executor.shutdown();
        executor.awaitTermination(20, TimeUnit.SECONDS);

        for (int i = 0; i < calculations.size(); i++) {

            if (futureResults.get(i).isDone()) {
                try {
                    calculations.get(i).setResult(futureResults.get(i).get());
                } catch (Exception e) {
                }
            }
        }
    }
}
