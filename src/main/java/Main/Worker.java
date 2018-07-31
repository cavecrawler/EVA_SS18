package Main;

public class Worker implements Runnable {

    private int id;
    private int indicator;
    private NumberSetList numberSetList;


    public Worker(int id, int indicator, NumberSetList numberSetList) {
        this.id = id;
        this.indicator = indicator;
        this.numberSetList = numberSetList;
    }

    @Override
    public void run() {

        System.out.println("Starting Thread: " + id);
        KPICalc calc = new KPICalc(numberSetList);
        calc.calculateYoYProfit("7/24/18", 1, indicator);
        System.out.println("Closing Thread: " + id);
    }
}
