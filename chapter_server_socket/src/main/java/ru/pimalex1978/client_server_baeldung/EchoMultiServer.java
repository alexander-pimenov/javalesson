package ru.pimalex1978.client_server_baeldung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.io.*;

/**
 * Пример многопоточного сервера для обратотки большого количества запросов от Клиентов.
 * Каждый запрос будет обрабатываться в отдельнои потоке.
 * Можно провести аналогию с секретарем — его задача принять звонок и сразу
 * перенаправить его другому сотруднику. И наш секретарь снова может принимать
 * новые звонки.
 * <p>
 * https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-networking
 */

public class EchoMultiServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoMultiServer.class);

    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for a connection on " + port);
                new EchoClientHandler(serverSocket.accept()).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }

    }

    public void stop() {
        try {

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    //Печатаем сообщение
                    System.out.println("The message from Client: " + inputLine);
                    if (".".equals(inputLine)) {
                        // Тоже говорим клиенту "bye" и выходим из цикла
                        out.println("bye");
                        break;
                    }
                    //Посылаем Клиенту ответ
                    out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();

            } catch (IOException e) {
                LOG.debug(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        EchoMultiServer server = new EchoMultiServer();
        server.start(5555);
    }

}