package ru.pimalex1978.exceptions;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;


//для примера по переопределению exceptionsExample
public interface SelfCloseable extends Closeable {


    //переопределили метод, и поставили не throws IOException
    //а throws FileNotFoundException. Т.е. используем подтип от IOException
    //а не сам IOException. Т.обр. работает наследование в классе Exception
    //супер тип Exception здесь указать нельзя, можно только подтип
    @Override
    void close() throws FileNotFoundException;
}
