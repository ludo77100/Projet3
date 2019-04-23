package com.enedis.ludovic;

import java.io.InputStream;
import java.util.Properties;

/**
 * Classe pour charger le fichier config.properties
 */
public class Config {

    Properties properties;
    String filePath;

    /**
     * Cette méthode permet de charger le fichier de config
     * @param filePath
     */
    public Config(String filePath) {

        this.filePath = filePath;

        properties = new Properties();
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream(filePath);

            properties.load(input);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * Cette méthode permet de retourner la valeur d'un paramètre de config en fonction de son nom
     * @param propertieName nom de la propriété
     * @return la valeur de la propriété ciblé
     */
    public int getIntPropertiesByName(String propertieName) {
        return Integer.parseInt(properties.getProperty(propertieName));
    }
}