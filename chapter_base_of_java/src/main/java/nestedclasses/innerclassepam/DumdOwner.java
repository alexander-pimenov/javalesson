package nestedclasses.innerclassepam;

/**
 * Пример показывающий взаимоотношение между полями во
 * внешнем и вложенном классах
 */
public class DumdOwner {
    private int id;
    private static int coeff;


    public class DumberInner {
        private int id;

        public void setId(int id) {
            this.id = id + DumdOwner.this.id * coeff;
        }
    }
}
