package nestedclasses.local;

import nestedclasses.innerclassepam.Student;

public abstract class AbstractTeacher {
    private int id;

    public AbstractTeacher(int id) {
        this.id = id;
    }

    /**
     * Абстрактный метод о возможности отчисления студента.
     */
    public abstract boolean excludeStudent(Student student);
}
