package com.main;


import java.io.FileInputStream;
import java.util.Properties;

public class Config {

    Properties properties;
    String filePath;

    public Config(String filePath) {

        this.filePath = filePath;

        properties = new Properties();
        FileInputStream input = null;
        try {
            input = new FileInputStream(filePath);

            properties.load(input);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public String getPropertiesByName(String propertieName) {
        return properties.getProperty(propertieName);
    }

    public int getIntPropertiesByName(String propertieName) {
        return Integer.parseInt(properties.getProperty(propertieName));
    }
}