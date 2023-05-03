package ru.pimalex1978.client_server_javacourse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Класс - клиент для отправки и получения данных
 * Клиент посылает строку с текстом.
 * Первым шагом мы пишем текстовую строку — она определена в начале программы.
 * Потом мы читаем ответ сервера (ответ сервера предусматривает дублирование
 * нашей строки с префиксом «Server returns: «) и посылаем вторую строку —
 * “bye”. Это сигнал, по которому сервер должен понять, что мы хотим
 * прекратить работу. Он нам тоже отвечает “bye” и закрывает сокет, мы эту
 * строку читаем и заканчиваем работу.
 *
 * Источник:
 * https://java-course.ru/begin/networking/}
 */
public class Client {

    public static void main(String[] args) throws Exception {
        // Определяем номер порта, на котором нас ожидает сервер для ответа
        int portNumber = 1777;
        // Подготавливаем строку для запроса - просто строка
        String str = "Тестовая строка для передачи: Всем привет!";
//        String str = "bye";

        // Пишем, что стартовали клиент
        System.out.println("Client is started");

        // Открыть сокет (Socket) для обращения к локальному компьютеру
        // Сервер мы будем запускать на этом же компьютере
        // Это специальный класс для сетевого взаимодействия c клиентской стороны
        Socket socket = new Socket("127.0.0.1", portNumber);

        // Создать поток для чтения символов из сокета
        // Для этого надо открыть поток сокета - socket.getInputStream()
        // Потом преобразовать его в поток символов - new InputStreamReader
        // И уже потом сделать его читателем строк - BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Создать поток для записи символов в сокет
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        // Отправляем тестовую строку в сокет
        pw.println(str);

        // Входим в цикл чтения, что нам ответил сервер
        while ((str = br.readLine()) != null) {
            // Если пришел ответ “bye”, то заканчиваем цикл
            if (str.equals("bye")) {
                break;
            }
            // Печатаем ответ от сервера на консоль для проверки
            System.out.println(str);
            // Посылаем ему "bye" для окончания "разговора"
            pw.println("bye");
        }

        br.close();
        pw.close();
        socket.close();
    }
}