package org.elainevalles.httpserver;

import org.elainevalles.httpserver.config.ConfigurationManager;

/*
* Driver Class for the HTTP Server
* */
public class HttpServer {
    public static void main(String[] args) {
        System.out.println("\nStarting HTTP server...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

    }
}
