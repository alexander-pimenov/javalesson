package gcandfinalize;

/**
 * Мы помним, что пр создании объекта вызываются конструкторы начиная
 * от класса Object и до имеющегося крайнего наследника, и вызываются
 * они последовательно:
 * Object -> A -> B -> C
 *
 * Деструктор же вызывается в обратном порядке!!! Начиная от самого нижнего
 * подкласса и вверх по иерархии.
 * В Java метод finalize() является неким аналогом деструктора.
 * За очистку памяти в Java отвечает GC (Garbage Collector). GC срабатывает
 * не тогда, когда объект утратил ссылку, а когда он посчитает нужным!!!
 * Потому что GC останавливает все процессы и выполняет очистку памяти.
 * Если он будет делать часто или как только какой то объект утратил ссылку,
 * то вместо ускорения работы приложения, мы получим его замедление.
 * При вызове метода System.gc() или Runtime.getRuntime().gc() не значит что
 * виртуальная машина сразу начнет удаление объекта. Это  просто просьба.
 * И VM приняла к сведению, что её просят сделать уборку, но она её сделает,
 * когда посчитает нужным.
 * Сборку мусора не возможно форсировать!!!
 * Поэтому метод finalize()  никогда не следует переопределять.
 *
 * Для освобождения каких то ресурсов нужно применять блок finally
 * в try-catch-finally.
 */
public class LearnMainFinalize {
    public static void main(String[] args) {
        C c = new C();
        c = null;
        //Просим GC сделать уборку мусора
        System.gc(); //вызов GC
        //или еще один метод обращения к GC
        Runtime.getRuntime().gc();

    }
}

class A {

}

class B extends A {

}

class C extends B {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}