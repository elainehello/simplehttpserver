package org.elainevalles.httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elainevalles.httpserver.util.Json;

import java.io.FileNotFoundException;
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
     */
    public void loadConfigurationFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(filePath)) {
            int i;
            while ((i = reader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode conf = null;
        try {
            conf = mapper.readTree(sb.toString());
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error reading configuration file", e);
        }
        try {
            myCurrentConfig = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing configuration file", e);
        }
    }

    /*
     * Returns current Loaded Configuration
     */
    public Configuration getCurrentConfig() {
        if (myCurrentConfig == null) {
            throw new HttpConfigurationException("No current configuration found");
        }
        return myCurrentConfig;
    }

}
