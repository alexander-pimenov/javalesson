package ru.pimalex1978.httpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс наследуется от Thread.
 */
public class Handler extends Thread {
    //определяем мапу с контент типом, которые будем тестировать
    //ключом будет расширение файла, а значением котнтент-тип
    //Заголовок-сущность Content-Type используется для того, чтобы определить MIME тип ресурса.
    private static final Map<String, String> CONTENT_TYPES = new HashMap<>() {{
        put("jpg", "image/jpeg");
        put("png", "image/png");
        put("html", "text/html");
        put("json", "application/json");
        put("txt", "text/plain");
        put("", "text/plain"); //это если расширения нет
    }};

    //константа NOT_FOUND
    private static final String NOT_FOUND_MESSAGE = "NOT FOUND";

    private Socket socket;
    private String directory;

    Handler(Socket socket, String directory) {
        this.socket = socket;
        this.directory = directory;
    }

    /**
     * Переопределяем метод run() у потока
     * и в конструкции try-with-resources
     * получаем из сокета InputStream и OutputStream
     */
    @Override
    public void run() {
        try (var input = this.socket.getInputStream();
             var output = this.socket.getOutputStream()) {
            //напишем URL который укажет путь к желаемому файлу
            var url = this.getRequestUrl(input);
            //формируем путь к файлу
            var filePath = Path.of(this.directory + url);
            //проверяем есть ли такой путь и не является ли он директорией
            //в блоке if будет отправляться сам файл, а
            //в блоке else будет отправляться сообщение NOT_FOUND

            if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
                var extension = this.getFileExtension(filePath);
                //выбираем контент-тип из мапы
                var type = CONTENT_TYPES.get(extension);
                //с помощью статического метода .readAllBytes(...) получаем массив байтов файла
                var fileBytes = Files.readAllBytes(filePath);
                //отправляем head
                this.sendHeader(output, 200, "OK", type, fileBytes.length);
                //в output запишем массив байтов файла
                output.write(fileBytes);
            } else {
                var type = CONTENT_TYPES.get("text");
                this.sendHeader(output, 404, "Not Found", type, NOT_FOUND_MESSAGE.length());
                //
                output.write(NOT_FOUND_MESSAGE.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для извлечения url запроса
     *
     * @param input инпутстрим
     * @return url
     */
    private String getRequestUrl(InputStream input) {
        //С помощью Scanner читать запрос будет удобнее всего
        //сразу выставляем разделитель
        var reader = new Scanner(input).useDelimiter("\r\n");
        //читаем первую строку (starting line)
        //starting line содержит запрос-url-версию протокола
        //GET /path HTTP/1.1
        var line = reader.next();
        //разделяем по пробелу и возвращаем 2-й элемент, т.е. url
        return line.split(" ")[1];
    }

    /**
     * Вспомагательный метод для ответа
     *
     * @param output     стрим для записи ответа
     * @param statusCode код ответа
     * @param statusText текст статуса
     * @param type       контент-тип
     * @param lenght     длина сообщения
     */
    private void sendHeader(OutputStream output, int statusCode, String statusText, String type, long lenght) {
        //чтобы было красивее и проще будем использовать обертку над OutputStream PrintStream
        var ps = new PrintStream(output);
        //отправляем ответ
        ps.printf("HTTP/1.1 %s %s%n", statusCode, statusText);
        ps.printf("Content-Type: %s%n", type);
        ps.printf("Content-Length: %s%n%n", lenght); //здесь два раделителя строки, т.к. разделяем head и body
    }

    /**
     * Метод для получения расширения файлов
     *
     * @param path путь к файлу
     * @return расширение
     */
    private String getFileExtension(Path path) {
        //получаем имя файла
        var name = path.getFileName().toString();
        //получаем индекс символа, с которого начинается расширение, т.е. последняя точка
        var extensionStart = name.lastIndexOf(".");
        //если расширения нет, то возвращаем пучтую строчку если оно есть
        //то вырезаем его .substring(...)
        return extensionStart == -1 ? "" : name.substring(extensionStart + 1);
    }
}
