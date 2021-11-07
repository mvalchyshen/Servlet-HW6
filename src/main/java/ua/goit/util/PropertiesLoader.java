package ua.goit.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private Properties properties = new Properties();

    public String getProperty(String name) {
        return properties.getProperty(name);
    }

    public void loadPropertiesFile(String fileName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
