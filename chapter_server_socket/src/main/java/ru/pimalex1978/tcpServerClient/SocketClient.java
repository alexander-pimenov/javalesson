package ru.pimalex1978.tcpServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Основы программирования TCP-сокетов на Java.
 * <a href="https://nuancesprog.ru/p/8583/">Ссылка на источник</a>
 * Показанная ниже программа действует как клиент,
 * создавая соединение с серверным сокетом. После
 * подключения клиент получает отправленные сервером данные.
 * Входной поток соединяется с буфером, используя
 * BufferedReader для хранения полученных данных,
 * так как мы не можем быть уверены, что данные будут
 * использоваться сразу же после получения. Затем мы
 * считываем данные из буфера и выводим их в консоль.
 */
public class SocketClient {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 50001);
            InputStream is = clientSocket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String receivedData = br.readLine();
            System.out.println("Received Data: " + receivedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
