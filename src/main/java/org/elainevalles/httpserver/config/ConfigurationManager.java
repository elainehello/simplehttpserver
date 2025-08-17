package org.elainevalles.httpserver.config;

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

    }

    /*
    * Get current Loaded Configuration
    * */
    public void getCurrentConfig() {
    }

}
