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
    public String getFilepath() {

        String filepath = config.getString("filepath");
        return filepath;
    }

    // Liefert die auszulesenden Sheets der Excel-Quelldatei
    public int[] getSheets() {
        int[] sheets = config.get(int[].class, "sheets");
        return sheets;
    }

    // Liefert alle Jahre, für die Year over Year Berechnungen durchgeführt werden sollen
    public int[] getYoYIndicators() {
        int[] yoyIndicators = config.get(int[].class, "processing.yoy_indicators.yoy");
        return yoyIndicators;
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
