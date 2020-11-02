package ru.pimalex1978.client_server_baeldung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.io.*;

/**
 * Это будет приложение для двусторонней связи, в котором клиент приветствует сервер, а сервер отвечает.
 * https://www.baeldung.com/a-guide-to-java-sockets
 * https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-networking
 */
public class GreetServer {
    private static final Logger LOG = LoggerFactory.getLogger(GreetServer.class);

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    out.println("good bye");
                    break;
                }
                out.println(inputLine);
            }
        } catch (IOException e) {
            LOG.debug(e.getMessage());
        }

    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            LOG.debug(e.getMessage());
        }

    }

    public static void main(String[] args) {
        GreetServer server = new GreetServer();
        server.start(4444);
    }
}

//@Test
//public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect() {
//    GreetClient client = new GreetClient();
//    client.startConnection("127.0.0.1", 4444);
//    String response = client.sendMessage("hello server");
//    assertEquals("hello client", response);
//}