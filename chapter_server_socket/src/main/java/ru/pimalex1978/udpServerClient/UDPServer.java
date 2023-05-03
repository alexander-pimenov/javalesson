package ru.pimalex1978.udpServerClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * https://vk.com/@javatutorial-osnovy-programmirovaniya-udp-soketov-na-java
 * Сокеты — это абстракция самого низкого уровня для программистов,
 * работающих в области сетевого программирования. Существует в основном
 * два способа (протокола) того, как должна происходить коммуникация сокетов.
 * <p>
 * Один из способов устанавливает набор правил и механизмов, ограничивающих
 * коммуникацию, так, что вероятность возникновения ошибки делается очень низкой,
 * но эти дополнительные элементы управления сами замедляют коммуникацию.
 * <p>
 * Он называется TCP (Transfer Control Protocol). В противоположность этому,
 * другой способ имеет очень мало правил, и поэтому он ускоряет общение, но
 * не гарантирует точность. Он называется UDP (User Datagram Protocol).
 * <p>
 * TCP реализует различные механизмы, такие как управление потоком, контроль
 * ошибок, контроль перегрузки и т.д. Однако, когда нашим главным приоритетом
 * является быстрая коммуникация, а не точная, выбор должен быть сделан в
 * пользу UDP.
 * <p>
 * Скорость — основной приоритет для потоковой передачи в реальном времени.
 * Когда футбольный матч транслируется в режиме реального времени, люди,
 * которые смотрят его по телевидению, должны получать обновленную информацию
 * о событиях сразу же, как только они происходят на игровой площадке.
 * <p>
 * Неспособность поставлять контент в режиме реального времени может
 * критически сказаться на росте поставщика потоковой передачи и его позиции
 * на рынке.
 * <p>
 * В этом примере серверный сокет получает данные типа String от клиента и
 * отправляет заглавную строку обратно клиенту.
 * <p>
 * Как реализовать надежную передачу по протоколу UDP?
 * <p>
 * UDP не обеспечивает гарантии доставки на уровне протокола, вы можете
 * использовать собственную логику для надежного обмена сообщениями, например,
 * путем введения порядковых номеров и повторной передачи.
 * <p>
 * Если получатель обнаруживает, что он пропустил порядковый номер, он может
 * запросить повтор этого сообщения с Сервера.  Протокол TRDP , который
 * используется  Tibco Rendezvous  (популярное промежуточное ПО для
 * высокоскоростного обмена сообщениями), использует UDP для более быстрого
 * обмена сообщениями и обеспечивает надежность за счет использования
 * порядкового номера и повторной передачи.
 */

public class UDPServer {
    // Серверный UDP-сокет запущен на этом порту
    public final static int SERVICE_PORT = 50001;

    public static void main(String[] args) throws IOException {
        try {
            // Создайте новый экземпляр DatagramSocket, чтобы получать ответы от клиента
            DatagramSocket serverSocket = new DatagramSocket(SERVICE_PORT);

      /* Создайте буферы для хранения отправляемых и получаемых данных.
Они временно хранят данные в случае задержек связи */
            byte[] receivingDataBuffer = new byte[1024];
            byte[] sendingDataBuffer = new byte[1024];

            /* Создайте экземпляр UDP-пакета для хранения клиентских данных с использованием буфера для полученных данных */
            DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            System.out.println("Waiting for a client to connect...");

            // Получите данные от клиента и сохраните их в inputPacket
            serverSocket.receive(inputPacket);

            // Выведите на экран отправленные клиентом данные
            String receivedData = new String(inputPacket.getData());
            System.out.println("Sent from the client: " + receivedData);

            /*
             * Преобразуйте отправленные клиентом данные в верхний регистр,
             * Преобразуйте их в байты
             * и сохраните в соответствующий буфер. */
            sendingDataBuffer = receivedData.toUpperCase().getBytes();

            // Получите IP-адрес и порт клиента
            InetAddress senderAddress = inputPacket.getAddress();
            int senderPort = inputPacket.getPort();

            // Создайте новый UDP-пакет с данными, чтобы отправить их клиенту
            DatagramPacket outputPacket = new DatagramPacket(
                    sendingDataBuffer, sendingDataBuffer.length,
                    senderAddress, senderPort
            );

            // Отправьте пакет клиенту
            serverSocket.send(outputPacket);
            // Закройте соединение сокетов
            serverSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}