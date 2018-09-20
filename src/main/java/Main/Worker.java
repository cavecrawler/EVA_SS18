package Main;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Worker implements Callable<Float> {

    private int workerID;
    private int indicator;
    private NumberSetList numberSetList;
    private LocalDate targetDate;

    public Worker(int id, int indicator, NumberSetList numberSetList) {
        this.workerID = id;
        this.indicator = indicator;
        this.numberSetList = numberSetList;

    }

    @Override
    public Float call() {


    }
}
