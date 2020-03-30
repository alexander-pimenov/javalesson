package ru.pimalex1978.currency_converter2;

import net.sf.saxon.resource.DataURIScheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CurrencyConverter extends JFrame {

    private JPanel contentPane; //панель содержимого
    private JTextField textField2;
    private JTextField textField1;
    private JTextField textField;

    /**
     * Создаем рамку (frame).
     */
    private void createFrame() {

        Reader r = new Reader();
        r.openFile();
        r.readFile();

        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 277, 116);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // создать комбо-боксы (comboBoxes)
        JComboBox<String> comboBox1 = new JComboBox<>();
        comboBox1.setBounds(5, 5, 93, 48);
        contentPane.add(comboBox1);
        JComboBox<String> comboBox2 = new JComboBox<>();
        comboBox2.setBounds(5, 43, 93, 48);
        contentPane.add(comboBox2);

        // добавить названия валют в comboBox 1 & 2
        for (int i = 0; i < r.sizeOf(); i++) {
            comboBox1.addItem(r.getName(i));
            comboBox2.addItem(r.getName(i));
        }
        comboBox2.setSelectedItem(comboBox2.getItemAt(1));

        // создать текстовое поле (textField1)
        textField1 = new JTextField();
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                double input1 = Double.parseDouble(textField1.getText());
                double gbpVal1 = r.getRate(comboBox1.getSelectedIndex());
                double curVal1 = r.getRate(comboBox2.getSelectedIndex());
                textField2.setText(String.format("%.6f", (input1 * gbpVal1) / curVal1));
            }
        });
        textField1.setColumns(10);
        textField1.setBounds(110, 14, 152, 28);
        contentPane.add(textField1);

        // создать текстовое поле (textField2)
        textField2 = new JTextField();
        textField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                double input = Double.parseDouble(textField2.getText());
                double gbpVal = r.getRate(comboBox2.getSelectedIndex());
                double curVal = r.getRate(comboBox1.getSelectedIndex());
                textField1.setText(String.format("%.6f", (input * gbpVal) / curVal));
            }
        });
        textField2.setColumns(10);
        textField2.setBounds(110, 52, 152, 28);
        contentPane.add(textField2);

        textField = new JTextField();
        textField.setBounds(38, 43, 134, 28);
        contentPane.add(textField);
        textField.setColumns(10);
    }

    /**
     * Запустить приложение.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    CurrencyConverter frame = new CurrencyConverter();
                    frame.createFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
