package Main;

import java.util.ArrayList;

public class Worker implements Runnable {

    private int id;
    private int kennzahl;
    private NumberSetList numberSetList;


    public Worker(int id, int kennzahl, NumberSetList numberSetList) {
        this.id = id;
        this.kennzahl = kennzahl;
        this.numberSetList = numberSetList;
    }

    @Override
    public void run() {

        System.out.println("Starting Thread: " + id);
        KPICalc threadKpiCalc = new KPICalc(numberSetList);
        threadKpiCalc.calculateYoYProfit("7/24/18", 1, kennzahl);
        System.out.println("Closing Thread: " + id);
    }
}
