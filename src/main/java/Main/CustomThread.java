package Main;

import java.util.ArrayList;

public class CustomThread extends Thread {

    int kennzahl;
    NumberSetList numberSetList;

    public CustomThread(int kennzahl, NumberSetList numberSetList) {

        this.numberSetList = numberSetList;
        this.kennzahl = kennzahl;
    }

    public void run() {
        KPICalc threadKpiCalc = new KPICalc(numberSetList);
        threadKpiCalc.calculateYoYProfit("7/24/18", 1, kennzahl);

    }
}