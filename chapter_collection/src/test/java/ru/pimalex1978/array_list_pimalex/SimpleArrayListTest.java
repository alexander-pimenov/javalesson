package ru.pimalex1978.array_list_pimalex;

import org.junit.Before;
import org.junit.Test;
import ru.pimalex1978.linked_list_pimalex.SimpleSinglyLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class SimpleArrayListTest {

    private SimpleSinglyLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleSinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsAndCheckFirstElement() {
        assertThat(list.get(0), is(3));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test(expected = NullPointerException.class)
    public void whenDeleteThanDeleteFirstElement() {
        assertThat(list.delete(), is(3));
        assertThat(list.getSize(), is(2));
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(1));
        list.get(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteWhenEmptyList() {
        assertThat(list.delete(), is(3));
        assertThat(list.delete(), is(2));
        assertThat(list.delete(), is(1));
        assertThat(list.getSize(), is(0));
        list.delete();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetWhenEmptyList() {
        list.delete();
        list.delete();
        list.delete();
        //System.out.println(list.getSize()); //для просмотра size списка
        list.get(0);
    }

    @Test //(expected = NoSuchElementException.class)
    public void whenCallIterator() {
        Iterator<Integer> itr = list.iterator();
//        while (itr.hasNext()) {
//            System.out.println(itr.next());
//            System.out.println(list.getSize());
//        }
        //Вызвали итератор 1-й раз
        Integer next1 = itr.next();
        assertThat(next1, is(3));

        //Вызвали итератор 2-й раз
        Integer next2 = itr.next();
        assertThat(next2, is(2));

        //Проверяем размер списка.
        assertThat(list.getSize(), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenCallIteratorMoreThenHaveElements() {
        Iterator<Integer> itr = list.iterator();
        Integer next1 = itr.next();
        assertThat(next1, is(3));
        Integer next2 = itr.next();
        assertThat(next2, is(2));
        itr.next();
        itr.next();
    }

    @Test
    public void whenAddManyElementsThanHaveTooMuchSize(){
        SimpleSinglyLinkedList<Integer> list = new SimpleSinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        System.out.println(list.getSize());

        //assertThat(list.getSize(), is(11));
    }

}