package ru.pimalex1978.firstGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/******************************************************************************
 *  Compilation:  javac GUI.java
 *  Execution:    java GUI
 *
 *  A minimal Java program with a graphical user interface. The
 *  GUI prints out the number of times the user clicks a button.
 *
 *  % java GUI
 *
 *  Below is the syntax highlighted version of GUI.java from §1.5 Input and Output.
 *  https://introcs.cs.princeton.edu/java/15inout/
 *
 *  Код из этого урока:
 *  https://www.youtube.com/watch?v=5o3fMLPY7qY
 *
 * Больше информации:
 * https://introcs.cs.princeton.edu/java/15inout/GUI.java.html
 *
 ******************************************************************************/

public class GUI implements ActionListener {
    private int count = 0;
    private JFrame frame = new JFrame();
    private JPanel panel;
    private JLabel label = new JLabel("Number of clicks:  0");

    public GUI() {

        // the clickable button
        JButton button = new JButton("Click me");
        button.addActionListener(this);

        // the panel with the button and text
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.pack();
        frame.setVisible(true);
    }

    // process the button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Number of clicks: " + count);
    }

    // create one Frame
    public static void main(String[] args) {
        new GUI();
    }
}
