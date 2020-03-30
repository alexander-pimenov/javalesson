package ru.pimalex1978.ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
/**
 * Быстрый старт с Java: «лопни шарик»
 * https://geekbrains.ru/posts/bystryj-start-s-java-lopni-sharik?utm_campaign=bystryj-start-s-java-lopni-sharik&utm_source=vk.com&utm_medium=internal
 *
 */


public class RandomBalls extends JFrame {
    private final String TITLE_OF_PROGRAM = "Random balls";
    private int WINDOW_WIDTH = 650;
    private int WINDOW_HEIGHT = 650;

    private Random random;
    private Canvas canvas;

    private final Color[] COLORS = {Color.red, Color.green, Color.blue, Color.yellow}; //К полям класса добавим массив цветов

    //Объявим поле balls для хранения списка шариков.
    private ArrayList<Ball> balls;

    //нужное количество шариков COUNT_BALLS
    private final int COUNT_BALLS = 35;

    //Для реализации игры, во-первых, добавим счётчик создаваемых шаров counter
    private int counter = 0;

    //И время задержки в миллисекундах showDelay.
    private int showDelay = 1000;


    public static void main(String[] args) {
        //new RandomBalls(); // создаём объект-окно
        //Для реализации игры, в-третьих, будем вызывать метод game()
        new RandomBalls().game();
    }

    public RandomBalls() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        random = new Random(); // создание объекта в конструкторе
        //Создадим соответствующий объект (подобно random) в конструкторе.
        balls = new ArrayList<>();
        //Для реализации инры, во-вторых, уберём из конструктора фрагмент,
        //создающий при помощи цикла for нужное количество шариков
//        for (int i = 0; i < COUNT_BALLS; i++) {
//            addBall();
//        }



        /*
         * В конструкторе создадим объект canvas,
         * назначив ему белый фон и заданные размеры.
         * Затем добавим созданные объект в наше окно,
         * с помощью метода add().
         * Метод pack() скорректирует размеры окна в
         * соответствии с canvas.
         */
        canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(
                new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        /*
         * Добавим интерактивности, подключив взаимодействие с мышью.
         * Потребуется импорт классов для обработки событий из библиотеки
         * java.awt.event.
         * Вызов метода addMouseListener() создаёт обработчик событий от мыши.
         * Интересно, что параметром метода является не переменная, а исполняемый
         * код. Фактически в скобках описывается анонимный класс, создаваемый
         * наследованием от абстрактного MouseAdapter() и содержащий
         * переопределённый метод mouseReleased(). Аннотация @Override помогает
         * нам не ошибиться в имени метода. Метод будет вызываться всякий раз,
         * когда мы будем отпускать (released) кнопку мыши. В теле метода
         * одна строка кода, которая вызывает перерисовку канвы, а именно
         * вызов paint() объекта canvas.
         * */
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                deleteBall(e.getX(), e.getY());
                canvas.repaint();
            }
        });

        add(canvas); //Добавляет холст в наше окно
        pack(); //Корректирует окно под размер холста
        setLocationRelativeTo(null); //Позиция на экране
        setResizable(false);
        setVisible(true); //Сделать видимым

    }

    /**
     * Теперь напишем метод, который добавляет очередной объект-шарик
     * (на основе класса Ball) в список. Его параметры (координаты,
     * диаметр и цвет) получаем с помощью random, генерирующего случайные значения.
     */
    private void addBall() {
        int d = random.nextInt(20) + 60;
        int x = random.nextInt(WINDOW_WIDTH - d);
        int y = random.nextInt(WINDOW_HEIGHT - d);
        Color color = COLORS[random.nextInt(COLORS.length)];
        balls.add(new Ball(x, y, d, color));
    }

    /**
     * Теперь, когда шарики перестали быть просто картинками,
     * а стали объектами, с ними можно что-то делать. Например,
     * «лопать», кликая мышкой. Для этого добавим следующий метод.
     */
    private void deleteBall(int x, int y) {
        // Цикл for перебирает список, определяя: находится ли точка,
        // заданная координатами в параметрах метода, внутри или снаружи шарика?
        // Если внутри – шарик удаляется и перебор прекращается.
        for (int i = balls.size() - 1; i > -1; i--) {
            double dx = balls.get(i).x + balls.get(i).d / 2 - x;
            double dy = balls.get(i).y + balls.get(i).d / 2 - y;
            double d = Math.sqrt(dx * dx + dy * dy);
            if (d < balls.get(i).d / 2) {
                balls.remove(i);
                break;
            }
        }
    }

    /**
     * Например: на пустом игровом поле появляются «случайные» шарики. Удаляем их мышкой.
     * После каждых 10 шариков скорость появления новых возрастает. Если на экране
     * одновременно будет находиться 5 шариков – игра заканчивается. Код метода game(),
     * представленный ниже – вариант такой реализации.
     */
    void game() {
        while (true) {
            addBall();
            if (balls.size() >= 5) {
                System.out.println("Game Over: " + counter);
                break;
            }
            canvas.repaint();
            counter++;
            if (counter % 10 == 0 && showDelay > 100) {
                showDelay -= 100;
            }
            try {
                Thread.sleep(showDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Чтобы создать область для рисования, «холст», мы опишем
     * внутренний класс Canvas, сделав его наследником класса JPanel.
     * В этом случае появится возможность переопределить метод paint()
     * класса-предка. В нём мы разместим свой код, сразу после вызова
     * метода предка: super.paint(g).
     * Аннотация @Override поможет не допустить ошибку в сигнатуре
     * переопределяемого метода.
     */
    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //Рисуем окружность
            for (Ball ball : balls) {
                ball.paint(g);
            }

//            /*
//             * Изменяя цифры, можно варьировать количество окружностей и их размер.
//             * Приведённый ниже фрагмент нарисует 100 «случайных» окружностей:
//             * x и y – координаты левого верхнего угла квадрата, в который вписана
//             * окружность, а d – её диаметр. Цвета берутся из массива COLORS,
//             * каждая окружность имеет чёрную “обводку”.
//             */
//            for (int i = 0; i < 100; i++) {
//                int d = random.nextInt(20) + 60;
//                int x = random.nextInt(WINDOW_WIDTH - d);
//                int y = random.nextInt(WINDOW_HEIGHT - d);
//                Color color = COLORS[random.nextInt(COLORS.length)];
//                g.setColor(color);
//                g.fillOval(x, y, d, d);
//                g.setColor(Color.black);
//                g.drawOval(x, y, d, d);
//            }

        }
    }

    /**
     * Теперь добавим класс Ball. Он может быть внутренним (внутри нашего класса)
     * или внешним, если сохранить его в отдельном файле с именем, идентичным
     * имени класса. Внешнему классу потребуются импорты.
     * Класс Ball имеет четыре поля, согласно четырём параметрам шарика:
     * координаты левого верхнего угла квадрата, куда он вписан, диаметр
     * и цвет. Есть параметрический конструктор и также метод paint(),
     * обеспечивающий отрисовку шарика.
     */

    public class Ball {
        private int x, y, d;
        private Color color;

        public Ball(int x, int y, int d, Color color) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.color = color;
        }

        void paint(Graphics g) {
            g.setColor(color);
            g.fillOval(x, y, d, d);
            g.setColor(Color.black);
            g.drawOval(x, y, d, d);
        }
    }
}
