package Main;

import java.util.ArrayList;
import java.util.concurrent.*;

public class TaskMaster {

    private NumberSetList numberSetList;
    private XMLConfig config;


    public TaskMaster(NumberSetList numberSetList, XMLConfig config) {

        this.numberSetList = numberSetList;
        this.config = config;
    }

    public ArrayList<Float> startThreads() {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Zeitraum für Berechnungen
        // erstes datum + yoyIndicator == letztes Datum

        //
        //
        //

    }
}
