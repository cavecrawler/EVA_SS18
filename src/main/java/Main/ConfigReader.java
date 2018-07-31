package Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    protected static Properties prop;
    private InputStream inputStream;

    public ConfigReader() {

        prop = new Properties();
        inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config_wp.txt");

        try {
            prop.load(inputStream);
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen von config_wp.txt");
        }
    }

    public int getIndicator(int indicator) {
        String key = "YoY"+indicator;
        Integer i = Integer.valueOf(prop.getProperty(key));
        return i;
    }

    public String getFilePath() {
        return prop.getProperty("filepath");
    }

    public int getWorkbook() {
        Integer workbook = Integer.valueOf(prop.getProperty("workbook"));
        return workbook;
    }

}
