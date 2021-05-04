package nestedclasses.local;

import nestedclasses.innerclassepam.Student;

public class Teacher extends AbstractTeacher {
    public Teacher(int id) {
        super(id);
    }

    /**
     * Учитель не может отчислить студента.
     * @param student студент.
     * @return false
     */
    @Override
    public boolean excludeStudent(Student student) {
        return false;
    }
}
