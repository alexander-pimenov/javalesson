package generics.example3;

import generics.example2.Product;

/**
 * class Name <T extends A & B & C & D>{}
 * A - класс или интерфейс
 * и потом только интерфейсы, класс потом нельзя указать.
 * B,C,D - только интерфейс
 * Эти дополнительные ограничения более суживают то с
 * чем мы можем в итоге работать.
 *
 */
public class Name <T extends Product<T> & Comparable<T> >{
}
