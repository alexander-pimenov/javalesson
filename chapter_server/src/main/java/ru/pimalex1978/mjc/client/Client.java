package ru.pimalex1978.mjc.client;

public class Client {
    public static String ipAddr = "localhost";
    public static int port = 8080;

    public static void main(String[] args) {
        new ClientProcess(ipAddr, port);
    }
}
