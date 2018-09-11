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

    // Auslesen der Year-over-Year Jahre Berechnung aus Config
    public int getYearOverYearIndicator(int yearOverYearIndicator) {
        String key = "YoY"+yearOverYearIndicator;
        Integer i = Integer.valueOf(prop.getProperty(key));
        return i;
    }

    // dynamischer Filepath für die Quelldatei
    public String getFilePath() {
        return prop.getProperty("filepath");
    }

    // bestimmt das Workbook anhand der Arbeitsblatt-Nummer aus der Config
    public int getWorkbook() {
        Integer workbook = Integer.valueOf(prop.getProperty("workbook"));
        return workbook;
    }

}
