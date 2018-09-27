package Main;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XMLConfig {

    private XMLConfiguration config;

    public XMLConfig() {

        Configurations configs = new Configurations();

        try {
            config = configs.xml(XMLConfig.class.getResource("/configs.xml"));
        } catch (ConfigurationException e) {
            // an error occured
        }
    }

    // Liefert den relativen Dateipfad der Excel-Quelldatei
    public String getSourceFilepath() {

        String filepath = config.getString("source.filepath");
        return filepath;
    }

    public String getTargetFilepath() {
        String filepath = config.getString("target.filepath");
        return filepath;
    }

    public String getResultFilepath() {
        String filepath = config.getString("result.filepath");
        return filepath;
    }

    // Liefert die auszulesenden Sheets der Excel-Quelldatei
    public int[] getSourceSheets() {
        int[] sourceSheets = config.get(int[].class, "source.sheets");
        return sourceSheets;
    }


    public Map<String, Integer> getTargetSheets() {
        int[] ints = config.get(int[].class, "target.sheets.sheet");
        List<String> types = config.getList(String.class, "target.sheets.sheet[@type]");
        Map<String, Integer> targetSheets = new HashMap<>();

        for (int i=0; i<ints.length;i++) {
            targetSheets.put(types.get(i), ints[i]);
        }

        return targetSheets;
    }

      public ArrayList<Calculation> getCalculations() {

        ArrayList<Calculation> calculations = new ArrayList<Calculation>();
        List<String> types = config.getList(String.class, "calculations.calculation[@type]");
        List<Integer> timeIndicators = config.getList(Integer.class,"calculations.calculation[@year]");

        for (int i=0; i < types.size();i++) {
            String key = "calculations.calculation(" +i+ ").indices.bondIndex";
            List<String> bondIndices = config.getList(String.class, key);
            int timeIndicator = timeIndicators.get(i);
            String type = types.get(i);
            Calculation currentCalculation = new Calculation(type,bondIndices,timeIndicator);
            calculations.add(currentCalculation);
        }
        int i = 1;


        return calculations;
    }
}
