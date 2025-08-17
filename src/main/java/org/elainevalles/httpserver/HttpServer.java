package org.elainevalles.httpserver;

import org.elainevalles.httpserver.config.Configuration;
import org.elainevalles.httpserver.config.ConfigurationManager;

/*
* Driver Class for the HTTP Server
* */
public class HttpServer {
    public static void main(String[] args) {
        System.out.println("\nStarting HTTP server...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfig();
        System.out.println("Current configuration: " + conf);
        System.out.println("Current port: " + conf.getPort());
        System.out.println("Current webroot: " + conf.getWebroot());
    }
}
