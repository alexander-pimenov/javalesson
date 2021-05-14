package ru.pimalex1978.huffman_code;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Класс для тестирования с использованием
 * кода Хаффмана.
 * Генерирует длинную строчку из маленьких
 * латинских букв. Длина 10_000 символов.
 */
public class Generate {
    public static void main(String[] args) throws FileNotFoundException {
        new Generate().run();
    }

    private void run() throws FileNotFoundException {
        //Данные будем выводить в файл
        PrintWriter printWriter = new PrintWriter("input.txt");
        int n = 10000;

        //генератор рандомных чисел
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            //берем код символа 'a' прибавляем рандомное число в пределах 26
            //(0...25), т.обр. получаем код какой то буквы от 'a' до 'z', приводим
            //код к char, так получаем саму букву
            printWriter.print((char) ('a' + random.nextInt(26)));
        }
        printWriter.close(); //закрываем, иначе он не сбросит буфер и ничего не сохраниться
    }
}
