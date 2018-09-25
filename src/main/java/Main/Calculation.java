package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Calculation {

    String type;
    List<String> indices;
    int timeIndicator;
    private ResultObject result;

    public Calculation(String type, List<String> indices, int timeIndicator) {
        this.type = type;
        this.indices = indices;
        this.timeIndicator = timeIndicator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getIndices() {
        return indices;
    }

    public void setIndices(List<String> indices) {
        this.indices = indices;
    }

    public int getTimeIndicator() {
        return timeIndicator;
    }

    public void setTimeIndicator(int timeIndicator) {
        this.timeIndicator = timeIndicator;
    }

    public ResultObject getResult() {
        return result;
    }

    public void setResult(ResultObject result) {
        this.result = result;
    }
}
