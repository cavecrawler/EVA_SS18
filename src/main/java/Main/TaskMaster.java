package Main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TaskMaster {

    private NumberSetList numberSetList;
    private XMLConfig config;


    public TaskMaster(NumberSetList numberSetList, XMLConfig config) {

        this.numberSetList = numberSetList;
        this.config = config;
    }

    public void getTaskConfiguration() {
        // TODO yoyindicators lesen und Array erstellen
        // TODO maxDDindicators lesen und in Array schreiben

        config.getYoYIndicators();
    }

    public void startThreads() {

        System.out.println("TaskMaster: Starting Threads...");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        int[] yearOverYearIndicators = config.getYoYIndicators();

        for (int yoyIndicator : yearOverYearIndicators) { // für jeden yoyIndicator wird eine Berechnung angestellt
            for (int id = 1; id <=3; id++) {

                if (yoyIndicator != 0) {
                    executor.submit(new Worker(id, yoyIndicator, numberSetList));
                    int test2 = 1;
                }
            }
        }
        int test = 1;
        executor.shutdown();
    }

}
