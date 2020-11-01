package ru.pimalex1978.socket;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/*https://progi.pro/ogranichenie-soedineniya-java-serversocket-7082403*/
/*Здесь видим что подключение к серверу у каждого клиента своё и отличие идет
 * по localport(клиентский порт), он не регулируется.
 * Один и тот же клиент может также иметь несколько соединений с сервером
 * одновременно, например, одно соединение с клиентским портом 12345 и
 * другое с 12377 одновременно.
 * Всякий раз, когда ваш сервер принимает новый клиент, создается новый
 * Socket , и он независим от всех других сокетов, созданных до сих пор.
 * Идентификация клиентов не подразумевается каким-то образом..*/

public class SockTest {
    public static void main(String[] args) {
        final AtomicBoolean shouldRun = new AtomicBoolean(true);
        try {
            final ServerSocket server = new ServerSocket(9100);
            Thread serverThread = new Thread() {
                public void run() {
                    try {
                        while (shouldRun.get()) {
                            Socket s = server.accept();
                            s.close();
                            Thread.sleep(1);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            };
            serverThread.start();
            Socket[] clients = new Socket[150];
            for (int i = 0; i < clients.length; i++) {
                clients[i] = new Socket("localhost", 9100);
                System.out.printf("Client %2d: " + clients[i] + "%n", i);
                /*Делаем тоже самое, только вызываем методы:*/
                InetAddress inetAddress = clients[i].getInetAddress();
                int port = clients[i].getPort();
                int localPort = clients[i].getLocalPort();
                System.out.println("inetAddress=" + inetAddress + ", port=" + port + ",localPort=" + localPort);
                clients[i].close();
            }
            shouldRun.set(false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shouldRun.set(false);
        }
    }
}
