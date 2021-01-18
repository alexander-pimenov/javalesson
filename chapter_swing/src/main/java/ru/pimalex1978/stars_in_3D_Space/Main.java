package ru.pimalex1978.stars_in_3D_Space;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * В данном уроке вы увидите как нарисовать простейшую 3D графику
 * стандартными средствами языка программирования Java. Рисуем
 * систему частиц в пространстве.
 * Код сделан как можно более простым и не использует дополнительных
 * библиотек.
 * Исходники:
 * https://github.com/Arhiser/java_tutorials/tree/master/src/ru/arhiser/stars
 * <p>
 * В данном приложении мы пробуем воспроизвести общую структуру, которую
 * имеют игры и подобные и графические приложения.
 * <p>
 * Для расчета координат точек в пространстве используем формулу
 * из статьи на ресурсе habr.com:
 * "Трёхмерная графика с нуля. Часть 2: растеризация",
 * разделы "Перспективная проекция", "Уравнение проецирования"
 * https://habr.com/ru/post/342708/
 */
public class Main {
    //переменная для синхронизации потоков
    //делаем простую неблокирующую синхронизацию между двумя потоками
    volatile static boolean isFrameReadyToDraw = true;

    public static void main(String[] args) {
        //константы размера окна (экрана)
        final int screenWidth = 1200; //ширина
        final int screenHeight = 800; //высота

        //Создаем экземпляр окна
        JFrame frame = new JFrame();
        frame.setSize(screenWidth, screenHeight); //задаем размер окна
        //задаем, что делается когда мы закрываем окно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //выход приложения

        //объект этого класса будем использовать в качестве буфера,
        //в котором будем создавать изображение очередного кадра
        BufferedImage image = new BufferedImage(screenWidth, screenHeight,
                BufferedImage.TYPE_INT_ARGB); //указываем формат пикселей, которые будут
        //содержаться внутри изображения.


        //чтобы отобразить BufferedImage создадим JLabel, но сначала
        //обернем в в ImageIcon
        //JLabel отобразит на окне нашу картинку
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel picLabel = new JLabel(imageIcon);

        //Layout создадим чтоб положить в него picLabel
        BorderLayout borderLayout = new BorderLayout();
        //зададим для нашего контента (окна) этот Layout
        frame.getContentPane().setLayout(borderLayout);
        //добавляем в него picLabel
        frame.getContentPane().add(picLabel, BorderLayout.CENTER);

        //чтобы окно показалось
        frame.setVisible(true);

        //собираем основной цикл программы
        //создаем наши компоненты
        Model model = new Model();
        Render render = new Render();

        //переменная для замера времени
        long lastTime = System.currentTimeMillis();

        //пока окно открыто, программа работает
        while (frame.isVisible()) {
            long time = System.currentTimeMillis();
            //обновляем все точки согласно прошедшему времени
            model.update(time - lastTime);
//            ru.job4j.vacancyparser.model.update2(time - lastTime); //метод взят из пакета file_render
            //теперь текущее время становится прошедшим
            //сохраняем для расчета на следующем кадре
            lastTime = time;

            //если кадр готов к перерисовке
            if (isFrameReadyToDraw) {
                //начали перерисовку и isFrameReadyToDraw ставим в false
                isFrameReadyToDraw = false;
                //вызываем render и даем ему куда рисвать - в image
                //и что рисовать - ru.job4j.vacancyparser.model
                render.draw(image, model);
//                render.draw2(image, ru.job4j.vacancyparser.model); //метод взят из пакета file_render
                //т.к. интерфей работате в отдельном потоке, то
                //перерисовку окна нужно вызвать в отдельном потоке
                //здесь как в многпоточности реализуем интерфейс Runnable
                SwingUtilities.invokeLater(() -> {
                    frame.repaint();
                    //закончили перерисовку и isFrameReadyToDraw ставим в true
                    isFrameReadyToDraw = true;
                });
            }
        }
    }
}
