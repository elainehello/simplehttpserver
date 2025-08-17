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
    * */
    public void loadConfigurationFile(String filePath) throws JsonProcessingException {
        FileReader reader = null;
        try {
            reader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        while (true) {
            try {
                if (((i = reader.read()) != -1))
                    break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sb.append((char) i);
        }
        ObjectMapper  mapper = new ObjectMapper();
        // Parse JSON string to JsonNode
        JsonNode conf = mapper.readTree(sb.toString());
        myCurrentConfig = Json.fromJson(conf, Configuration.class);
    }

    /*
    * Returns current Loaded Configuration
    * */
    public Configuration getCurrentConfig() {
        if (myCurrentConfig == null) {
            throw new HttpConfigurationException("No current configuration found");
        }
        return  myCurrentConfig;
    }

}
