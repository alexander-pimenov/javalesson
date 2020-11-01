package ru.pimalex1978.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

/**
 * Java-программа для создания простого HTTP-сервера, чтобы продемонстрировать
 * как использовать ServerSocket и класс Socket.
 * Класс java.net.Socket позволяет получить сообщение и передать его.
 */

/*Перейдите в http://127.0.0.1:8080/ из браузера, и вы получите текущую дату.*/
/*В браузере ответ не выводится, "нет соединения", но через cUrl всё работает отлично.*/

public class SimpleHTTPServer {
    public static void main(String[] args) throws IOException {
        /*Создаем Сервер.
         * ServerSocket создает сервер.
         * 9000 - это порт. По умолчанию адрес будет localhost*/
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080 ....");
        while (true) {
            try (Socket socket = server.accept(); //позволяет получить сообщение и передать его
                 BufferedReader reader = new BufferedReader(
                         new InputStreamReader(socket.getInputStream())
                 )) {

                String line = reader.readLine();
                while (!line.isEmpty()) {
                    System.out.println(line);
                    String[] split = line.split("\\s");
                    System.out.println("\tСтрока ответа в Массив:" + Arrays.toString(split));
                    line = reader.readLine();
                }
                Date today = new Date();
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today + "\r\n\r\n";
                String html = "";
                html += "<html>\n";
                html += "<head>\n";
                html += "<head/>\n";
                html += "<body>\n";
                html += "<p> Hello world </p>\n";
                html += "</body>\n";
                html += "</html>\n";
                socket.getOutputStream().write(httpResponse.getBytes(StandardCharsets.UTF_8));
                socket.getOutputStream().write(html.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}

//public String homeStorage() {
//        String html = "";
//        html += "<ul>";
//        html += " <li><a href='/showAllStorage'>Показать всё Хранилище</a></li>";
//        html += " <li><a href='/showAllBoxes'>Показать все Box в Хранилище</a></li>";
//        html += " <li><a href='/showAllItems'>Показать все Item в Хранилище</a></li>";
//        html += " <li><a href='/data'>Поработать с запросом</a></li>";
//        html += "</ul>";
//
//        return html;
//    }

//<html>
//<head/>
//<body>
//<p> Hello world </p>
//</body>
//</html>

//out.println("HTTP/1.1 200 OK");
//       out.println("Content-Type: text/html");
//       out.println("\r\n");
//       out.println("<p> Hello world </p>");
//       out.flush();

//PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
