package statics;

public class LearnMainStaticMethod {
    public static void main(String[] args) {
        //1
        Student.convertFaculty("This is the best -->");

        //2
        Base obj = new SubClass();
        obj.print(); //хотя вызывать стат.метод через объект не приветствуется
    }
}

//Output
//This is the best --> mmf
//Base