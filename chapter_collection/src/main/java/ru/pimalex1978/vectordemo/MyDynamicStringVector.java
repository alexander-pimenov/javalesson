package ru.pimalex1978.vectordemo;

public class MyDynamicStringVector {
    private String[] data = new String[3];
    private int size = 0;

    public String getLast() {
        return data[size-1];
    }

    public String get(int i) {
        return data[i];
    }

    public void add(String s) {
        if(data.length <= size) {
            String[] tmp = data;
            data = new String[size*2];
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
        MyDynamicStringVector vector = new MyDynamicStringVector();
        vector.add("1");
        vector.add("2");
        vector.add("15");
        vector.add("Selenium");

        System.out.println(vector.get(3));
        System.out.println(vector.getLast());
        System.out.println(vector.size());



    }
}
