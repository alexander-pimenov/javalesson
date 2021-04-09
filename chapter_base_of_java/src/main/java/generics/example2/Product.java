package generics.example2;

/**
 * По урокам Yuriy Tkach:
 * https://www.youtube.com/playlist?list=PL6jg6AGdCNaX1yIJpX4sgALBTmTVc_uOJ
 * Рекурсивное расширение типа - Generics #3 - Advanced Java:
 * https://www.youtube.com/watch?v=ns8T7-nI_Ec&list=PL6jg6AGdCNaX1yIJpX4sgALBTmTVc_uOJ&index=3
 * @param <T>
 */
public abstract class Product<T extends Product<T>> implements Comparable<T> {
    String name;
    int price;

    /**
     * Метод сравнения Продуктов по цене
     * @param o продукт
     * @return число (-1; 0; 1)
     */

    public int compareTo(T o) {
        return Integer.compare(this.price, o.price);
    }

//    @Override
//    public int compareTo(Product o) {
//        int result;
//        result = this.name.compareTo(o.name);
//        if (result != 0) return result;
//        result = Integer.compare(this.price, o.price);
//        return result;
//    }

    /**
     * Метод для реализации в субПродуктах, т.е. в наследниках.
     * @param p продукт
     */
    abstract boolean subCompare(T p);
}
