package nestedclasses.local;

import nestedclasses.innerclassepam.Student;

public class InnerLocalMain {
    public static void main(String[] args) {
        AbstractTeacher teacher = TeacherCreator.createTeacher(6);
        //чтоб посмотреть как компилируется код
        System.out.println(teacher.getClass());
        //class nestedclasses.local.TeacherCreator$1Rector

        boolean result = teacher.excludeStudent(new Student());
        System.out.println(result); //true

        teacher = TeacherCreator.createTeacher(77);
        System.out.println(teacher.getClass());
        //class nestedclasses.local.Teacher

        result = teacher.excludeStudent(new Student());
        System.out.println(result); //false
    }
}
