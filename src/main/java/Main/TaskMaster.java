package Main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskMaster {

    private NumberSetList numberSetList;
    private ConfigReader config;


    public TaskMaster(NumberSetList numberSetList, ConfigReader config) {

        this.numberSetList = numberSetList;
        this.config = config;
    }

    public void startThreads() {

        System.out.println("TaskMaster: Starting Threads...");
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int id = 1; id < 6; id++) {


            executor.submit(new Worker(id, config.getYearOverYearIndicator(1), numberSetList));
        }

        executor.shutdown();
    }
    
}
