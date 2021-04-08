package ru.pimalex.coffee3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainCoffeeMachine {

    public static void main(String[] args) throws IOException {
        System.out.println("Я - Кофе-машина. Добрый день! Что будете пить?");
        //Нужно, чтобы число можно было ввести с клавиатуры в консоле
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите сумму денег: ");
        int moneyAmount = sc.nextInt();
        boolean canByAnything = false;
        int[] drinkPrices = {20, 80, 150, 160, 40};
        String[] drinkNames = {"воду", "эспрессо", "капучино", "латте", "молоко"};
        for (int i = 0; i < drinkNames.length; i++) {
            if (moneyAmount >= drinkPrices[i]) {
                System.out.println("Вы можете купить " + drinkNames[i]);
                canByAnything = true;
            }
        }
        //2. Дописать проверку canBuyAnything
        if (!canByAnything) {
            System.out.println("Не достаточно средств! Пополните сумму, пожалуйста");
        }
        //3. Нужно сделать так, чтобы дата выводилась в формате: 20191205_214218
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        System.out.println(dateFormat.format(new Date()));
        //4. Написать код получения скриншота экрана, который распечатает размеры экрана
        //Обернули в try/catch, что бы не выбрасывать исключение в main метод.
        BufferedImage screenCapture = null;
        try {
            screenCapture = new Robot()
                    .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

            ImageIO.write(screenCapture, "JPG", new File("screenShot.jpg"));
        } catch (AWTException | IOException e) {
            System.err.println("Something go wrong: " + e.getMessage());
            e.printStackTrace();
        }
        if (screenCapture != null) {
            System.out.println(screenCapture.getWidth() + "x" + screenCapture.getHeight());
        }
    }
}
