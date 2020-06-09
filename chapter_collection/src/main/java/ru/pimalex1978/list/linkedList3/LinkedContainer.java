package ru.pimalex1978.list.linkedList3;

import java.util.Iterator;

/**
 * Вся работа в нашем ссылочном контейнере LinkedContainer
 * сводится к тому, что бы организовать взаимосвязь между нашими Node.
 * Например, перебросить наши ссылки в момент добавления/удаления
 * элементов.
 * <p>
 * Здесь будет реализован не замкнутый связный список.
 * Т.е. у которого первый и последний элементы списка не имеют ссылки друг
 * на друга. Это будет только в тот момент, когда список еще пуст.
 * И эти ссылки имеют null.
 * <p>
 * Node схематически выглядит так: <-prevElement[Node(e=value)]nextElement->
 * <p>
 * Ниже показаны Первый и Последний элементы списка:
 * Когда список еще пуст.
 * null<-prevElement[fstNode(e=null)]nextElement-> <-prevElement[lstNode(e=null)]nextElement->null
 * <p>
 * Ниже показаны Первый, последующий и Последний элементы списка:
 * null<-prevElement[fstNode(e=null)]nextElement-> <-prevElement[Node(e=value)]nextElement-> <-prevElement[lstNode(e=null)]nextElement->null
 * <p>
 * Этот контейнер также реализует интерфейс Iterable. Для того чтобы по нему можно было итерироваться с помощью Итератора.
 * Прямая итерация от головы к хвосту.
 * <p>
 * А также LinkedContainer будет имплиментировать интерфейс interface DescendingIterator<E>, с методом Iterator<E> descendingIterator(); -
 * он нужен для итерирования по элеметам списка в обратном порядке: от хвоста к голове.
 * Возможность связного списка итерироваться в обратном порядке - это существенный момент.
 *
 * @param <E>
 */

public class LinkedContainer<E> implements Linked<E>, Iterable<E>, DescendingIterator<E> {
    private Node<E> fstNode; // указатель на первый элемент. В самом начале он пуст.
    private Node<E> lstNode; // указатель на последний элемент. В самом начале он пуст.
    private int size = 0;

    //ниже показаны Первый и Последний элементы списка:
    //null<-prevElement[fstNode(e=null)]nextElement-> <-prevElement[lstNode(e=null)]nextElement->null
    //ниже показаны Первый, последующий и Последний элементы списка:
    //null<-prevElement[fstNode(e=null)]nextElement-> <-prevElement[Node(e=value)]nextElement-> <-prevElement[lstNode(e=null)]nextElement->null
//

    /**
     * Конструктор нашего ссылочного контейнера.
     * Нужен, чтобы инициализировать fstNode и lstNode.
     * С помощью этого конструктора создадим экземпляр класса,
     * у которого уже будет некая связная структура между первой и последней
     * Нодой (Node).
     */
    public LinkedContainer() {
        //инициализируем последнюю Node
        /*Раз Node будет последняя, то текущего элемна не будет и currentElement=null,
         * ссылка на предыдущий элемент указывать на Первый элемент prevElement=fstNode,
         * а ссылка наследующий элемент nextElement=null*/
        this.lstNode = new Node<E>(null, fstNode, null);

        //инициализируем первую Node
        /*Текущий элемент currentElement=null, т.к это просто заглушка,
         * раз она Первая, то ссылки предыдущий элемент тоже нет prevElement=null,
         * а ссылка на следующий элемент будет указывать на последний nextElement=lstNode.*/
        this.fstNode = new Node<E>(null, null, lstNode);
    }

    @Override
    public void addLast(E e) {
        Node<E> prev = lstNode; //Переприсвоим указатель с последнего элемента, чтоб не потерять данные.

        prev.setCurrentElement(e); //Зададим ему текущий элемент.
        //Присвоим ему элемент, и он теперь не пустышка.

        //А для lstNode создадим новую Ноду пустышку в конец (конечную).
        //Заменяем старую последнюю Ноду на новую.
        //Это будет новая Нода, у которой будет ссылка на prev.
        lstNode = new Node<>(null, prev, null);

        //Теперь нужно связать prev элемент к нашей новой последней Ноде,
        //т.е. связываем его с конечным элементом пустышкой.
        prev.setNextElement(lstNode);

        //И соответственно инкрементируем size.
        size++;
    }

    @Override
    //Здесь будет аналогично методу addLast, но ориентированно на Первую Ноду заглушку.
    public void addFirst(E e) {
        Node<E> next = fstNode; //Переприсвоим указатель с первого элемента.
        next.setCurrentElement(e); //делаем из первой ноды полноценный контейнер.
        fstNode = new Node<>(null, null, next);//Создаем для fstNode новую Ноду пустышку (заглушку) в начало.

        //Задаем для next ссылку на Первую Ноду пустышку,
        //связываем его с первым элементом пустышкой,
        //т.е. то что предшествует нексту.
        next.setPrevElement(fstNode);

        //И соответственно инкрементируем size.
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * В этом методе getElementByIndex лежит вся идея связного писка.
     * Мы опускаемся все ниже и ниже к искомому элементу, вызывая в Node
     * метод из статического класса Node:
     * {@Code public Node<E> getNextElement() {
     * return nextElement;
     * } }
     * и таким образом получаем последующую, и затем последующую Ноду.
     *
     * @param counter индекс искомого элемента.
     * @return элемент списка с соответствующим индексом.
     */
    @Override
    public E getElementByIndex(int counter) {
        //Создаем Ноду target, которая будет хранить наш результат.
        //Начинаем проход от начала. Т.е. берем первую Ноду, получаем у нее
        //getNextElement из геттера класса Node:
        //public Node<E> getNextElement() {
        //            return nextElement;
        //        }
        //Индекс здесь будет равен 0.
        Node<E> target = fstNode.getNextElement();

        //пройдемся циклом до индекса counter, т.е. доберемся до нужной нам Ноды.
        //Залезли в Ноду нужное количество раз. Дошли до нужного индекса.
        for (int i = 0; i < counter; i++) {
            target = getNextElement(target); //при каждом проходе, мы перезаписываем target.
        }
        return target.getCurrentElement(); //Когда дошли до нужного индекса,
        // то вызывает getCurrentElement(), т.е. вызываем сам элемент.
    }

    /**
     * Создадим приватный метод для прохода до нужной нам Ноды.
     *
     * @param current текущая Нода.
     * @return возвращает нам Ноду.
     */
    private Node<E> getNextElement(Node<E> current) {
        return current.getNextElement(); //получаем следующий элемент.
    }

    /**
     * Т.к. наш контейнер реализует интерфейс Iterable<E>, то
     * нужно реализовать метод iterator().
     * Обход нашей коллекции от головы к хвосту.
     * Реализуем его анонимным классом.
     *
     * @return Iterator<E>
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int counter = 0; //заводим переменную counter. Идем от начала к концу.

            @Override
            public boolean hasNext() {
                return counter < size;
            }


            @Override
            public E next() {

                //Здесь будем использовать метод getElementByIndex, передавать в него counter
                //и затем counter инкрементировать.
                return getElementByIndex(counter++);
            }
        };
    }

    /**
     * Т.к. наш контейнер реализует интерфейс DescendingIterator<E>, то
     * нужно реализовать метод Iterator<E> descendingIterator().
     * Обход нашей коллекции от хвоста к голове, т.е. итерация в обратном
     * порядке.
     * Реализуем его анонимным классом.
     *
     * @return Iterator<E>
     */
    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {
            int counter = size - 1;// заводим переменную counter. Идем от конца к началу.

            @Override
            public boolean hasNext() {
                return counter >= 0;
            }

            @Override
            public E next() {

                //Здесь будем использовать метод getElementByIndex, передавать в него counter
                //и затем counter декрементировать.
                return getElementByIndex(counter--);
            }
        };
    }

    /**
     * Статический класс Node<E> - это наш
     * контейнер (вагончик), который хранит в себе
     * данные элементва и ссылки между элементами.
     * Полный аналог Node<E> из LinkedList Java.
     * В классе добавили геттеры и сеттеры для удобства.
     *
     * @param <E>
     */
    private static class Node<E> {
        private E currentElement; //данные, которые храним в списке, текущий элемент.
        private Node<E> nextElement; //ссылка на следующий элемент списка.
        private Node<E> prevElement; //ссылка на предыдущий элемент списка.

        /**
         * Конструктор Node
         *
         * @param currentElement текущий элемент
         * @param prevElement    ссылка на предыдущий элемент
         * @param nextElement    ссылка на следующий элемент
         */
        public Node(E currentElement, Node<E> prevElement, Node<E> nextElement) {
            this.currentElement = currentElement;
            this.nextElement = nextElement;
            this.prevElement = prevElement;
        }

        public E getCurrentElement() {
            return currentElement;
        }

        public void setCurrentElement(E currentElement) {
            this.currentElement = currentElement;
        }

        public Node<E> getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node<E> nextElement) {
            this.nextElement = nextElement;
        }

        public Node<E> getPrevElement() {
            return prevElement;
        }

        public void setPrevElement(Node<E> prevElement) {
            this.prevElement = prevElement;
        }

    }
}
