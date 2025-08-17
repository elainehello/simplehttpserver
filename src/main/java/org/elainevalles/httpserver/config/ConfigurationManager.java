package org.elainevalles.httpserver.config;

import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfig;

    private ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null) {
            myConfigurationManager = new ConfigurationManager();
        }
        return myConfigurationManager;
    }

    /*
    * Load Configuration file by the path provided
    * */
    public void loadConfigurationFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            StringBuffer sb = new StringBuffer();
            int i;
            while ((i = reader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException e) {
            System.err.println("Error reading configuration file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*
    * Get current Loaded Configuration
    * */
    public void getCurrentConfig() {
    }

}
