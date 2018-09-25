package Main;

import java.util.HashMap;
import java.util.Map;

public class ResultObject {

    Map<String, Float> resultMap;

    public ResultObject() {
       resultMap = new HashMap<String, Float>();
    }

    public Map<String, Float> getResultMap() {
        return resultMap;
    }

    public void putResults(String key, Float result) {
        resultMap.put(key,result);
    }
}
