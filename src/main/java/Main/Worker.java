package Main;

import java.lang.reflect.Array;
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

        synchronized (this.getClass()) {
            System.out.println("Worker " + workerID + ": Starting");

            // StartDatum überprüfen
            if (targetDate == null) {
                // TODO derzeit hardgecoded, letzter Listeneintrag wird als startDatum ermittelt
                targetDate = numberSetList.getLastNumberSet().getDate();
                //TODO: getFirstNumberSet() erstellen
            }

            KPICalc calc = new KPICalc(numberSetList);
            // TODO: bondIndex dynamisch aus Mapper Klasse gestalten.

            calc.calculateYoYProfit(workerID, targetDate, 0, indicator);
            //calc.calculate_MaxDD(workerID, targetDate, 0, indicator);

            // TODO: alle MaxDD berechnen


            System.out.println("Worker " + workerID + ": Closing");
        }
    }
}
