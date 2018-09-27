package Main;

public class NullCalculator implements ICalculator {

    @Override
    public ResultObject calculate() {
        return new ResultObject();

    }
}
