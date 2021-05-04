package ru.pimalex1978.pojo;

import java.io.Serializable;

/**
 * Можно выделить отдельный abstract класс, которому дать несколько
 * implements интерфейсов, чтобы у каждого класса POJO это не
 * прописывать, а только наследовать этот abstract класс.
 */
public abstract class Entity implements Serializable, Cloneable {
}
