package generics.example1;

/*этот обощенный класс представляет из себя грубую иммитацию ArrayList
 * <T> - это обобщенный тип, для примера*/

public class CustomArrayList<T> {
    private Object[] data = new Object[0];//создаем массив Обджектов, в нем может храниться все что угодно
    private int length = 0;//объвляем длину нашего CustomArrayList

    /*метод add добавляющий елемент в ArrayList
    * в методе добавления мы получаем какой то элемент любого типа*/
    public void add(T element) {
        if (length == data.length) {
            Object[] newData = new Object[data.length * 2 + 1];//создаем массив в 2 раза больше +1 элемент
            //+1 нужно для того чтобы первоначально инициализированный массив Object[0] был не нулевой длины
            /*копируем все элементы из первого массива во вновь созданный массив*/
            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }
            //System.arraycopy(data, 0, newData, 0, data.length); - это копирование используется на практике, т.к. работает гораздо быстрее
            data = newData;
        }

        data[length] = element; //в ячейку с индексом длины записывается наш element
        length++; //длина увеличивается на 1
    }

    /*метод get позволяет по индексу получить зачение из ArrayList*/
    public T get(int index) {
        if (index >= length || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return (T) data[index]; //возвращаем тот элемент к-рый хранится в массиве и приводится к типу (Т)
    }

    /*метод getLength возвращающий длину*/
    public int getLength(){
        return length;
    }

    /*метод clear очищающий ArrayList*/
    public void clear(){
        data = new Object[0];
        length = 0;
    }
}
