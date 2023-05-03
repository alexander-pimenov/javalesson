package ru.pimalex1978.client_server_baeldung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;

/**
 * https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-networking
 */
public class EchoClient {

    private static final Logger LOG = LoggerFactory.getLogger(EchoClient.class);

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            LOG.debug("Error when initializing connection", e);
        }

    }

    public String sendMessage(String msg) {
        try {
            System.out.println("The message to Server: " + msg);
            out.println(msg);
            return in.readLine();
        } catch (Exception e) {
            return null;
        }
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            LOG.debug("error when closing", e);
        }

    }

    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient();
        echoClient.startConnection("127.0.0.1", 5555);
        echoClient.sendMessage("Всем приветик!!!!");
        echoClient.stopConnection();
    }
}