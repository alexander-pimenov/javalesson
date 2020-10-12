package ru.pimalex1978.list.singleLinkList;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        /*Создаем новый список SingleLinkList<>*/
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(123, "Васильев Евстахий Борисович", "+129381832"));
        contactList.addToEnd(new Contact(151, "Коновалов Степан Петрович", "+234432334"));
        contactList.addToEnd(new Contact(332, "Калинин Артём Валериевич", "+2234234423"));
        contactList.addToEnd(new Contact(432, "Предыбайло Григорий Анатолиевич", "+2342344234"));
        contactList.addToEnd(new Contact(556, "Степанов Мирослав Андреевич", "+6678877777"));

        /*Т.к. SingleLinkList реализован интерфейс Iterable то мы можем
         * пройти по списку циклом foreach*/
        for (Contact contact : contactList) {
            System.out.println(contact);
        }

        contactList.reverse();

        System.out.println("------------------------");

        for (Contact contact : contactList) {
            System.out.println(contact);
        }
    }

    /**
     * Класс Contact для тестирования нашего SingleLinkList.
     */
    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    /**
     * Класс SingleLinkList - реализует структуру односвязного списка.
     *
     * @param <T> обощение типа.
     */
    public static class SingleLinkList<T> implements Iterable<T> {

        /**
         * Чтобы не потеярть всю нашу цепочку односвязного списка
         * храним дополнительную ссылку на начало списка - head голова
         * списка.
         */
        ListItem<T> head;
        /**
         * Чтобы можно было быстро добавлять элементы в конец списка,
         * хранят ссылку на конец списка - tail хвост списка.
         */
        ListItem<T> tail;

        /**
         * Метод возвращает объект Iterator для последовательного перебора
         * элементов списка.
         *
         * @return объект Iterator.
         */
        @Override
        public Iterator<T> iterator() {
            /*Возвращаем новый Итератор*/
            return new Iterator<T>() {
                /*У Итератора есть поле current, указывающее на текущий
                 * элемент, в котором находится в данный момент Итератор.
                 * Изначально это поле указывает на начало списка.*/
                ListItem<T> current = head;

                /**
                 * Метод hasNext() показывает есть ли еще элементы в списке
                 * или мы уже дошли до конца списка.
                 * @return true - элементы еще есть
                 * или false - элементов больше нет.
                 */
                @Override
                public boolean hasNext() {
                    return current != null;
                }

                /**
                 * Метод возвращающий следующий элемент
                 * @return объект T.
                 */
                @Override
                public T next() {
                    /*Данные из текущего элемента:*/
                    T data = current.data;
                    /*Сдвигаем текущий элемент на следующий элемент:*/
                    current = current.next;
                    return data;
                }
            };
        }

        private static class ListItem<T> {
            /*Данные пользовательские*/
            T data;
            /*Ссылка на следующий элемент*/
            ListItem<T> next;
        }

        /**
         * Метод, проверяющий пустой ли наш список.
         * Тут мы просто проверяем, что голова списка
         * указывает на null, и это значит, что в списке
         * нет ничего.
         *
         * @return true или false.
         */
        public boolean isEmpty() {
            return head == null;
        }

        /**
         * Метод добавляющий элемент в конец списка.
         * Здесь ссылка имеющего последнего элемента в списке
         * будет указывать на новый элемент, который мы вставляем.
         * Т.е. полю next имеющегося последнего элемента присваиваем
         * ссылку на следующщий элемент.
         *
         * @param item добавляемый элемент
         */
        public void addToEnd(T item) {
            /*Для пользовательских данных (T item) создаем
             * новый элемент списка newItem.*/
            ListItem<T> newItem = new ListItem<>();
            /*Заполняем у нового элента поле data*/
            newItem.data = item;
            /*Если список пустой, то:*/
            if (isEmpty()) {
                /*голова будет указывать на новый созданный элемент*/
                head = newItem;
                /*и хвост будет указывать на новый созданный элемент*/
                tail = newItem;
            }
            /*А если список не пустой*/
            else {
                /*то тогда последенему элементу (tail) в поле next
                 * задаем ссылку на наш говый элемент.*/
                tail.next = newItem;
                /*и этот новый элемент становится новым хвостом списка*/
                tail = newItem;
            }
        }

        /**
         * Метод обращения односвязного писка, т.е.
         * переворачивает список наоборот.
         * Связи между элементами прямого списка заменяются
         * на обратные.
         * В методе мы проходим по списку и меняем связи на
         * обратные.
         * Работаем с тремя указателями: на предыдущий элемент head,
         * на текущий элемент current, на следующий элемент next.
         */
        public void reverse() {
            /*Если список содержит больше одного элемента, т.е. есть хотя
             * бы два эелемнта:*/
            if (!isEmpty() && head.next != null) {
                /*хвосту присваиваем голову, т.е. тот элемент, который был
                 * первым, теперь станет последним.*/
                tail = head;
                /*создаем ссылку на текущий узел и сразу записываем в него
                 * head.next*/
                ListItem<T> current = head.next;
                /*в head.next записываем null, т.к. это теперь будет
                 * последним элементом списка. А последний элемент списка
                 * не должен ни на что указывать.*/
                head.next = null;
                /*Идем по списку пока текущий элемент не равен null.*/
                while (current != null) {
                    /*сохраняем ссылку на следующий элемент.*/
                    ListItem<T> next = current.next;
                    /*ссылке на следующий элемент в текущем элементе
                     * присваиваем head
                     * Т.е. меняем указатель, чтобы он указывал не на следующий
                     * элемент, а на предыдущий.*/
                    current.next = head;
                    /*Продвигаемся дальше по списку.
                     * В head теперь записываем current.
                     * В head хранится ссылка на предыдущий элемент.*/
                    head = current;
                    /*а в current записываем next*/
                    current = next;
                }
            }
        }
    }
}
