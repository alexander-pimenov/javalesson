package ru.pimalex1978.vectordemo;

public class MyDynamicIntegerVector {
    private Integer[] data = new Integer[3];
    private int size = 0;

    public Integer getLast() {
        return data[size-1];
    }

    public Integer get(int i) {
        return data[i];
    }

    public void add(Integer s) {
        if(data.length <= size) {
            Integer[] tmp = data;
            data = new Integer[size*2];
            for(int i = 0; i < tmp.length; i++) {
                data[i] = tmp[i];
            }
        }
        data[size] = s;
        size++;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MyDynamicIntegerVector vector = new MyDynamicIntegerVector();
        vector.add(1);
        vector.add(2);
        vector.add(15);
        vector.add(42);

        System.out.println(vector.get(3));
        System.out.println(vector.getLast());
        System.out.println(vector.size());
    }
}
