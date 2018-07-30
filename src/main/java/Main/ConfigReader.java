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

    public int getSelectedIndicator() {
        Integer i = Integer.valueOf(prop.getProperty("YoY"));
        return i;
    }
}
