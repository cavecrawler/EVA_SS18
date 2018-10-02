package Main;

import java.util.ArrayList;
import java.util.List;

public class SingleThreadMaster {

    private NumberSetList numberSetList;
    private List<Calculation> calculations;

    public SingleThreadMaster(NumberSetList numberSetList, ArrayList<Calculation> calculations) {
        this.numberSetList = numberSetList;
        this.calculations = calculations;
    }

    public void runCalculationsInSingleThread() {

        for (Calculation currentCalculation : calculations) {

            Worker worker = new Worker(currentCalculation, numberSetList);
            ResultObject result = worker.call();
            currentCalculation.setResult(result);
            int i=1;
        }

        int j = 1; //todo deletedebug
    }
}
