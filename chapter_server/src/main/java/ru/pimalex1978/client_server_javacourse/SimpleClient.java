package ru.pimalex1978.client_server_javacourse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
 * Класс Socket. Что это?
 * Работать с этим классом достаточно просто. При создании вы передаете ему
 * имя хоста и номер порта, с которым хотите соединиться. При таком варианте
 * Java сама ищет нужный IP по DNS, самостоятельно получает порт на локальном
 * компьютере (соединение требует двух сокетов и каждый имеет адрес и порт)
 * и делает соединение с указанным хостом.
 * Если все прошло успешно и соединение установлено, то дальше наступает
 * очередь потоков ввода-вывода. Сокет предоставляет два потока:
 * один на чтение — InputStream, другой на запись — OutputStream.
 *
 * Источник:
 * https://java-course.ru/begin/networking/
 */
public class SimpleClient {
    public static void main(String[] args) throws IOException {

        // Открываем сокет для доступа к компьютеру
        // по адресу "java-course.ru" (порт 80)
        Socket s = new Socket("java-course.ru", 80);

        // Открываем поток для чтения из сокета (информация будет
        // посылаться нам с удаленного компьютера)
        InputStream in = s.getInputStream();
        // Открываем поток для записи в сокет (информация будет
        // посылаться от нас на удаленный компьютер)
        OutputStream out = s.getOutputStream();

        // Готовим строчку с данными для запроса к серверу
        //GET-запрос по стандарту.
        // Можно пока игнорировать смысл этого запроса
        String str = "GET /network.txt HTTP/1.1\r\n" +
                "Host:java-course.ru\r\n\r\n";

        // Превращаем их в массив байт для передачи
        // Мы же используем поток, а он работает с байтами
        byte buf[] = str.getBytes();
        // Пишем в поток вывода
        out.write(buf);

        // И читаем результат в буфер
        int size;
        byte buf_out[] = new byte[1024];
        while ((size = in.read(buf_out)) != -1) {
            System.out.print(new String(buf_out, 0, size));
        }
        s.close();
    }
}

//Output
//HTTP/1.1 200 OK
//Date: Fri, 26 Feb 2021 07:04:43 GMT
//Server: Apache
//Last-Modified: Sat, 16 Dec 2017 20:43:22 GMT
//Accept-Ranges: bytes
//Content-Length: 40
//Content-Type: text/plain
//
//Congratulation !!!
//Socket is working !!!
