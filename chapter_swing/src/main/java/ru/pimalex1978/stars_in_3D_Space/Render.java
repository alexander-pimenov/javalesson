package ru.pimalex1978.stars_in_3D_Space;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Класс, отвечающий за рисование точек, за отрисовку
 * точек на BufferedImage, который будем показывать в окне.
 */
public class Render {
    /**
     * Метод отрисовки точек.
     *
     * @param image буфер, в который мы рисуем очередной кадр.
     * @param model класс модели, из которой берем данные,
     *              которые надо отрисовать
     */
    public void draw(BufferedImage image, Model model) {
        //создаем класс для рисования на изображении
        Graphics2D graphics = image.createGraphics();
        //задаем параметры рисования, рисуем черным цветом
        graphics.setPaint(new Color(0, 0, 0));
        //заполняем прямоугольник
        //всё изображение заливаем черным цветом
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        //на черном фоне рисуем точки
        for (Point point : model.getPoints()) {
            //здесь начинается 3-х мерная графика
            /*координату на экране по Х и по У, пребразуем из 3-х мерного пространства в точку на экране
             * image.getWidth() / 2f - сдвигаем на пол экрана координату
             * image.getWidth() / 2f * point.x / point.z - умножаем на половину ширины окна*/
            int sx = (int) (image.getWidth() / 2f + (image.getWidth() / 2f * point.x / point.z));
            int sy = (int) (image.getHeight() / 2f + (image.getHeight() / 2f * point.y / point.z));
            //проверяем, что координаты не выходят за пределы окна
            if (sx < image.getWidth() && sx > 0
                    && sy < image.getHeight() && sy > 0) {
                //для вычисления белого цвета
                //чем дальше объект, тем темнее белый цвет
                //масштабируем по координате Z
                int color = 255 + (int) (point.z * (255 / Math.abs(Model.INITIAL_Z_COORD)));
                //рисуем точку с помощью метода setRGB
                image.setRGB(sx, sy, 0xff000000 | color << 16 | color << 8 | color);
            }
        }
    }

    /**
     * Метод отрисовки точек.
     * Метод взят из пакета file_render для Render.
     *
     * @param image буфер, в который мы рисуем очередной кадр.
     * @param model класс модели, из которой берем данные,
     *              которые надо отрисовать
     */
    public void draw2(BufferedImage image, Model model) {
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(new Color(0, 0, 0, 96));
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        for (Point point : model.getPoints()) {
            float sx = image.getWidth() / 2f + (image.getWidth() / 2f * point.x / point.z);
            float sy = image.getHeight() / 2f + (image.getHeight() / 2f * point.y / point.z);

            int isx = Math.round(sx);
            int isy = Math.round(sy);

            int sx2 = Math.round(sx + ((sx - isx > 0) ? 1 : -1));
            int sy2 = Math.round(sy + ((sy - isy > 0) ? 1 : -1));

            if (sx2 < image.getWidth() && sx2 >= 0
                    && sy2 < image.getHeight() && sy2 >= 0) {
                float gain = (Math.abs(sx - isx) + Math.abs(sy - isy)) / 2;
                float colorGain = ((float) (255 + (int) (point.z * (255 / Math.abs(Model.INITIAL_Z_COORD))))) / 255f;
                colorGain = colorGain * gain;
                int colorR = (point.color & 0xff0000) >> 16;
                int colorG = (point.color & 0xff00) >> 8;
                int colorB = (point.color & 0xff);
                image.setRGB(sx2, sy2, 0xff000000
                        | (int) (colorR * colorGain) << 16
                        | (int) (colorG * colorGain) << 8
                        | (int) (colorB * colorGain));
            }

            if (isx < image.getWidth() && isx >= 0
                    && isy < image.getHeight() && isy >= 0) {
                float colorGain = ((float) (255 + (int) (point.z * (255 / Math.abs(Model.INITIAL_Z_COORD))))) / 255f;
                int colorR = (point.color & 0xff0000) >> 16;
                int colorG = (point.color & 0xff00) >> 8;
                int colorB = (point.color & 0xff);
                image.setRGB(isx, isy, 0xff000000
                        | (int) (colorR * colorGain) << 16
                        | (int) (colorG * colorGain) << 8
                        | (int) (colorB * colorGain));
            }
        }
    }
}
