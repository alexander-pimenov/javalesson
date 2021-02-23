package nestedclasses.anonymosclasses.example2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Типичный пример применения анонимного класса
 * Этот пример отображает JPanel на экране. К объекту JFrame
 * добавляется перехватчик событий,
 * который завершает приложение при закрытии окна пользователем.
 */

public class AnonDemo2 {
    public static void main(String[] args) {

        //создать JFrame и добавить к нему перехватчик
        //событий для обработки закрытия окна

        JFrame frame = new JFrame("AnonDemo2");
        frame.addWindowListener(new WindowAdapter() {

            //здесь переопределяется метод интерфейса WindowsListener windowClosing()
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //создать JPanel и добавить к фрейму

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 300));
        frame.getContentPane().add(panel);

        //отобразить фрейм

        frame.pack();
        frame.setVisible(true);
    }
}
