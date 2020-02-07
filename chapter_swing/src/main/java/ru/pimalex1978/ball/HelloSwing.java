package ru.pimalex1978.ball;

import javax.swing.*;
import java.util.Random;

/**
 * Быстрый старт с Java: «лопни шарик»
 * Знакомимся с графической библиотекой Swing
 * https://geekbrains.ru/posts/bystryj-start-s-java-lopni-sharik?utm_campaign=bystryj-start-s-java-lopni-sharik&utm_source=vk.com&utm_medium=internal
 */

public class HelloSwing extends JFrame {

    public static void main(String[] args) {
//        Random r = new Random();
//        for (int i = 0; i < 50; i++) {
//            int d = r.nextInt(40);
//            System.out.print(String.format("%d, ", d));
//            //System.out.print(d + " ");
//        }

        new HelloSwing(); //Создаем объект-окно
    }

    HelloSwing() {
        setTitle("Hello, Swing!!!"); // Заголовок окна
        setDefaultCloseOperation(EXIT_ON_CLOSE); // При закрытии
        setSize(500, 300); // размеры окна
        setLocationRelativeTo(null); //Позиция на экране
        setVisible(true); //Сделать видимым
    }
}
