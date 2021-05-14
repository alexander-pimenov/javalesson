package ru.pimalex1978.huffman_code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://stepik.org/lesson/16968/step/1?unit=4499
 * Кодирование Хаффмана.
 */
public class HuffmanCode {
    /**
     * Класс описывающий Узел дерева.
     * Для построения дерева.
     */
    class Node implements Comparable<Node> {
        //суммарная частота встречания символов в поддереве
        final int sum;

        //Код передаваемый в узел
        String code;

        public Node(int sum) {
            this.sum = sum;
        }

        /**
         * Метод присвоения кода узлу.
         * Выполняется всегда и для листов и
         * для внутренних узлов.
         */
        void buildCode(String code) {
            this.code = code;

        }

        /**
         * Метод производящий сравнение объектов Node
         * по их sum
         * Тот элемент у которого меньше сумма, тот
         * будет минимальным.
         *
         * @param o другой объект Node
         * @return целочисленное число с разным знаком
         */
        @Override
        public int compareTo(Node o) {
            return Integer.compare(sum, o.sum);
        }
    }

    /**
     * Класс описывающий внутренний узел,
     * у которого есть дети.
     * Правый и левый.
     * Его значение суммы sum - это сумма детей.
     */
    class InternalNode extends Node {
        Node left;
        Node right;

        public InternalNode(Node left, Node right) {
            super(left.sum + right.sum);
            this.left = left;
            this.right = right;
        }

        /**
         * Метод выполняется только для внутренних узлов.
         * Устанавливает дополнительный код на символы.
         *
         * @param code код
         */
        @Override
        void buildCode(String code) {
            super.buildCode(code);
            //у левого ребенка наш код + символ 0
            left.buildCode(code + "0");
            //у правого ребенка наш код + символ 1
            right.buildCode(code + "1");
        }
    }

    /**
     * Класс, описывающий лист дерева.
     * Лит отвечает какому то символу.
     */
    class LeafNode extends Node {
        char symbol;

        public LeafNode(char symbol, int count) {
            super(count);
            this.symbol = symbol;
        }

        @Override
        void buildCode(String code) {
            super.buildCode(code);
            System.out.println(symbol + ": " + code);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        new HuffmanCode().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() throws FileNotFoundException {
        //данные будем считывать из файла лежащего в корне
        //используем Scanner
        Scanner input = new Scanner(new File("input.txt"));
//        Scanner input = new Scanner(new File("inputHuffman.txt"));
        String str = input.next(); //читаем строчку из файла
        System.out.println(str);

        /*-----Считаем количество вхождений каждого символа-----*/
        //1 вариант:
        final Map<String, Long> counter1 = Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(counter1.size() + " " + counter1);
        System.out.println("------------------------------");

        //2 вариант
        Map<Character, Integer> counter2 = new HashMap<>();
        //идем по всем символам строчки
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //смотрим, есть ли он в мапе
            if (counter2.containsKey(c)) {
                //если есть, то инкремент его количества
                counter2.put(c, counter2.get(c) + 1);
            } else {
                //если такого ключа нет, то кладем 1
                counter2.put(c, 1);
            }
        }
        //выведем значения итерируясь по паре ключ-значение
        for (Map.Entry<Character, Integer> entry : counter2.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("------------------------------");
        /*------------------------------------------------------------------*/
        /*---------------Реализуем алгоритм Каффмана---------------*/
        //1 вариант: используем очередь с приоритетом, т.к. у нее на вершине храниться
        //минимальный элемент
        //достаем два узла с минимальными значениями и объединяем их в один
        //А так же для записи потом кодированной строки создадим мапу charNode где будем хранить данные
        Map<Character, Node> charNode = new HashMap<>();

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        //пробежимся по мапе
        for (Map.Entry<Character, Integer> entry : counter2.entrySet()) {
            //добавляем в приоритетную очередь все листовые узлы (т.е. конечные)
            LeafNode node = new LeafNode(entry.getKey(), entry.getValue());
            charNode.put(entry.getKey(), node);
            priorityQueue.add(node);
        }
        //Берем по два узла и склеиваем их в один
        //пока размер не меньше 2
        int sum = 0;
        while (priorityQueue.size() > 1) {
            //достанем 1-й узел
            Node first = priorityQueue.poll();
            //достанем 2-й узел
            Node second = priorityQueue.poll();
            //Объединим их в один узел и положим в очередь
            InternalNode node = new InternalNode(first, second);
            sum += node.sum;
            priorityQueue.add(node);
        }
        //обработаем ситуацию, если нам дают только один символ
        if (counter2.size() == 1) {
            sum = str.length();
        }
        System.out.println(counter2.size() + " " + sum);

        //берем корень у приоритетной очереди
        Node root = priorityQueue.poll();
        if (counter2.size() == 1) {
            root.buildCode("0");
        } else {
            root.buildCode("");
        }
        /*Выведем кодированную строчку. Собирать строчку будем с помощью StringBuilder*/
        StringBuilder encodedString = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            encodedString.append(charNode.get(c).code);
        }
        System.out.println(encodedString.toString());
    }
}
