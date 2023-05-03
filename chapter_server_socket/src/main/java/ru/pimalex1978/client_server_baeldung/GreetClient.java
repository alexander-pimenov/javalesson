package ru.pimalex1978.client_server_baeldung;

import java.net.*;
import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-networking
 */
public class GreetClient {
    private static final Logger LOG = LoggerFactory.getLogger(EchoMultiServer.class);

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            LOG.debug(e.getMessage());
        }

    }

    public String sendMessage(String msg) {
        try {
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
            LOG.debug(e.getMessage());
        }
    }
}
//@Test
//public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect() {
//    GreetClient client = new GreetClient();
//    client.startConnection("127.0.0.1", 6666);
//    String response = client.sendMessage("hello server");
//    assertEquals("hello client", response);
//}