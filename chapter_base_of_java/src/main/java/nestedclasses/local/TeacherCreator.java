package nestedclasses.local;

import nestedclasses.innerclassepam.Student;

/**
 * В данном случае наличие внутреннего класса внутри метода
 * имеет практическое применение. Т.е. мы скрыли реализацию класса
 * Rector
 */
public class TeacherCreator {
    public static AbstractTeacher createTeacher(int id) {
        // inner local class
        class Rector extends AbstractTeacher {
            public Rector(int id) {
                super(id);
            }
            @Override
            public boolean excludeStudent(Student student) {
                if (student != null) {
                    //more code
                    return true;
                } else {
                    return false;
                }
            }
        } // end of inner class
        if (isRectorId(id)) {
            return new Rector(id);
        } else {
            return new Teacher(id);
        }
    }

    private static boolean isRectorId(int id) {
        // id checking
        return (id == 6); //stub
    }
}
