package ru.pimalex1978.vectordemo;

public class MyDynamicVector<E> {
    private Object[] data = new Object[3];
    private int size = 0;

    @SuppressWarnings("unchecked")
    public E getLast() {
        return (E) data[size-1];
    }

    @SuppressWarnings("unchecked")
    public E get(int i) {
        return (E) data[i];
    }

    public void add(E e) {
        if(data.length <= size) {
            Object[] tmp = data;
            data = new Object[size*2];
            if (tmp.length >= 0) System.arraycopy(tmp, 0, data, 0, tmp.length);

            //Или копируем в ручную
//            for(int i = 0; i < tmp.length; i++) {
//                data[i] = tmp[i];
//            }

        }
        data[size] = e;
        size++;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MyDynamicVector<String> vector = new MyDynamicVector<>();
        vector.add("1");
        vector.add("2");
        vector.add("15");
        vector.add("Selenium");

        System.out.println(vector.get(3));
        System.out.println(vector.getLast());
        System.out.println(vector.size());


        MyDynamicVector<Integer> vector2 = new MyDynamicVector<>();
        vector2.add(1);
        vector2.add(2);
        vector2.add(15);
        vector2.add(Integer.parseInt("42a"));

        System.out.println(vector2.get(3));
        System.out.println(vector2.getLast());
        System.out.println(vector2.size());



    }
}
