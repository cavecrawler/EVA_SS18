package Main;

import java.time.LocalDate;

public class Worker implements Runnable {

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
    public void run() {

        System.out.println("Worker " + workerID + ": Starting");

        // StartDatum überprüfen
        if (targetDate == null) {
            targetDate = numberSetList.getLastNumberSet().getDate();
        }

        KPICalc calc = new KPICalc(numberSetList);
        // TODO: Bondindex dynamisch gestalten.
        calc.calculateYoYProfit(workerID,targetDate, 0, indicator);
        calc.calculate_MaxDD(workerID, targetDate, 0, indicator);

        System.out.println("Worker " + workerID + ": Closing");
    }
}
