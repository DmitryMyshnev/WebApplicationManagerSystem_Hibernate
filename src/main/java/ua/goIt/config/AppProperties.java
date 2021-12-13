package ua.goIt.config;

import java.io.IOException;
import java.util.Properties;

public class AppProperties {

    private static AppProperties value;
    private Properties properties;

    private AppProperties() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        this.properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        if (value == null) {
            value = new AppProperties();
        }
        return value.properties;
    }
}
