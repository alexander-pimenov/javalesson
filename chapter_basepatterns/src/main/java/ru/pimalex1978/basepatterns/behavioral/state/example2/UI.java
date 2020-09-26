package ru.pimalex1978.basepatterns.behavioral.state.example2;

import javax.swing.*;
import java.awt.*;

/**
 * GUI проигрывателя
 */
public class UI {
    private Player player;
    private static JTextField textField = new JTextField();

    public UI(Player player) {
        this.player = player;
    }

    public void init() {
        JFrame frame = new JFrame("Test player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //закрывать программу полностью при нажатии на "Х"
        JPanel context = new JPanel();
        context.setLayout(new BoxLayout(context, BoxLayout.Y_AXIS));
        frame.getContentPane().add(context);
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        context.add(textField);
        context.add(buttons);

        // Контекст заставляет состояние реагировать на пользовательский ввод
        // вместо себя. Реакция может быть разной в зависимости от того, какое
        // состояние сейчас активно.
        JButton play = new JButton("Play");
        play.addActionListener(e -> textField.setText(player.getState().onPlay()));
        JButton stop = new JButton("Stop");
        stop.addActionListener(e -> textField.setText(player.getState().onLock()));
        JButton next = new JButton("Next");
        next.addActionListener(e -> textField.setText(player.getState().onNext()));
        JButton prev = new JButton("Prev");
        prev.addActionListener(e -> textField.setText(player.getState().onPrevious()));
        frame.setVisible(true); //окно программы видимое
        frame.setLocationRelativeTo(null); //окно программы располагается в центре экрана
        frame.setResizable(false); //false - не можем менять размеры заданного окна, true - можем
        frame.setSize(300, 100); //размер нашего окна
        buttons.add(play);
        buttons.add(stop);
        buttons.add(next);
        buttons.add(prev);
    }
}