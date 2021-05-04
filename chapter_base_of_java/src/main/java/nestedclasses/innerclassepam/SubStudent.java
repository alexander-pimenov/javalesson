package nestedclasses.innerclassepam;

/**
 * Пример наследования у внутренних классов.
 * Практического толка в таком наследовании нет, но
 * тем не менее это возможно.
 */
public class SubStudent extends Student {
    public SubStudent() {
    }

    class SubAddress extends Address {
        public SubAddress() {
        }
    }
}
