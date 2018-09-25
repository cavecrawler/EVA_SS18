package Main;

import java.util.HashMap;
import java.util.Map;

public class ResultObject {

    Map<String, Float> resultMap;

    public ResultObject(Map<String, Float> resultMap) {
        this.resultMap = resultMap;
    }

    public ResultObject() {

    }

    public Map<String, Float> getResultMap() {
        return resultMap;
    }

    public void putResults(String key, Float result) {
        resultMap.put(key,result);
    }
}
