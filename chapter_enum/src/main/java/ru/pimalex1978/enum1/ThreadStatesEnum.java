package ru.pimalex1978.enum1;

/*Продвинутые возможности Enum (перечислений)*/

import java.io.Closeable;
import java.io.IOException;

public enum ThreadStatesEnum implements Closeable {
    START(1) {
        @Override
        public String toString() {
            return "Реализация START. Приоритет : " + getPriority();
        }

        @Override
        public String getDetail() {
            return "START";
        }
    },
    RUNNING(2) {
        @Override
        public String getDetail() {
            return "RUNNING";
        }
    },
    WAITING(3) {
        @Override
        public String getDetail() {
            return "WAITING";
        }
    },
    DEAD(4) {
        @Override
        public String getDetail() {
            return "DEAD";
        }
    };

    private int priority;

    //абстрактный метод, переопределенный в полях Enum
    public abstract String getDetail();

    // Enum конструкторы должны всегда быть private.
    private ThreadStatesEnum(int priority) {
        this.priority = priority;
    }

    // У Enum могут быть методы
    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    // Enum может переопределять методы
    @Override
    public String toString() {
        return "Стандартная реализация ThreadStatesConstructors. Приоритет : " + getPriority();
    }

    @Override
    public void close() throws IOException {
        System.out.println("Закрытие Enum");
    }
}
