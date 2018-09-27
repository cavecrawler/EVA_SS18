package Main.Calculators;

import Main.ResultObject;

public class NullCalculator implements ICalculator {

    @Override
    public ResultObject calculate() {
        return new ResultObject();

    }
}
