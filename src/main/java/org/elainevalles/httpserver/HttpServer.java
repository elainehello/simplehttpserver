package org.elainevalles.httpserver;

import org.elainevalles.httpserver.config.Configuration;
import org.elainevalles.httpserver.config.ConfigurationManager;
import org.elainevalles.httpserver.core.ServerListenerThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.CRL;

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

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
