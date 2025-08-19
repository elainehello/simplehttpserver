package org.elainevalles.httpserver;

import org.elainevalles.httpserver.config.Configuration;
import org.elainevalles.httpserver.config.ConfigurationManager;

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
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // HTML content to be sent as the response body
            String html = "<html><head><title>Simple HTTP Server</title></head>"
                    + "<body><h1>Welcome to the Simple HTTP Server!</h1></body></html>";

            final String CRLF = "\n\r";

            // Full HTTP response including status line, headers, and body
            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: " + html.getBytes().length + CRLF +
                    CRLF +
                    html +
                    CRLF + CRLF;

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
