package ru.pimalex1978.custom_exc;

import java.io.IOException;

/*делаем проверяемое исключение*/
public class MyOwnException extends IOException {
    /*создаем свой метод с определением, когда это произошло.*/
    public void logSomething(){
        System.out.println("This event was created at " + System.currentTimeMillis());
    }
}
