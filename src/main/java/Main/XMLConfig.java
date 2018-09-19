package Main;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import java.util.List;


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

        String filepath = config.getString("filepaths.datasource");
        return filepath;
    }

    // Liefert alle Jahre, für die Year over Year Berechnungen durchgeführt werden sollen
    public int[] getYoYIndicators() {
        int[] yoyIndicators = config.get(int[].class, "processing.yoy_indicators.yoy");
        return yoyIndicators;
    }

    // Liefert die auszulesenden Sheets der Excel-Quelldatei
    public int[] getSheets() {
        int[] sheets = config.get(int[].class,"processing.sheets.sheet");
        return sheets;
    }

}
