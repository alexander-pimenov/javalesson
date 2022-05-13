package immutable;

/*
 * 37 2 Иммутабельные классы в Java
 * https://www.youtube.com/watch?v=71D06DCK-Tw&list=PLbarVm4RfajIKaF6P5J_GC54N-glT6VXM&index=112
 * */
public class TestImmutableClass {
    public static void main(String[] args) {
        //1. ПРОБЕМА !!!
        //создадим StringBuilder, чтобы передать в аргумент объекту NotImmutableSb
        StringBuilder sb = new StringBuilder("инициализируем");
        NotImmutableSb problem = new NotImmutableSb(sb);

        //sb снаружи остался доступен и можем к нему что-то добавить
        sb.append(" добавим текст ");

        //gotBuilder будет ссылаться на тот же объект, что и sb !!!
        //плюс (еще одна проблема) через полученную ссылку gotBuilder мы также сможем
        //изменять наше внутреннее поле builder !!!
        StringBuilder gotBuilder = problem.getBuilder();

        //т.е. миссия - получить immutable объект из класса NotImmutableSb, не
        //достигнута, т.к. его можно менять по ссылке и еще хуже того по двум:
        //sb и gotBuilder
        sb.append(" добавим больше текста ");
        gotBuilder.append(" добавим еще больше текста ");

        System.out.println(problem.getBuilder()); //инициализируем, добавим текст, добавим больше текста, добавим еще больше текста

        System.out.println("=======================================");

        //2. ВСЁ ОК !!!
        //создадим StringBuilder, чтобы передать в аргумент объекту ImmutableSb
        StringBuilder sb2 = new StringBuilder("инициализируем");
        ImmutableStringBuilder notProblem = new ImmutableStringBuilder(sb2);

        //sb2 снаружи остался доступен и можем к нему что-то добавить, но он уже
        //ни как не повлияет на объект ImmutableStringBuilder (т.к. там создается в
        // конструкторе другой объект StringBuilder)
        sb2.append(" добавим текст ");

        //Полученный gotBuilder2 тоже не будет ссылаться на наш объект, который
        // находится в поле builder, т.к. возвращается через геттер не наш объект,
        // а похожий (такой же по наполнению) !!!
        StringBuilder gotBuilder2 = notProblem.getBuilder();

        //т.е. миссия - получить immutable объект из класса ImmutableSb,
        //достигнута, изменять находящийся объект внутри поля builder
        //не получится !!!

        //здесь в sb2 добавим что-то, просто чтобы видеть что он ни как не влияет на наш ImmutableSb !!!
        sb2.append(" добавим больше текста ");

        //а здесь в gotBuilder2 добавим что-то, просто, чтобы видеть, что он тоже ни как не влияет на наш ImmutableSb !!!
        gotBuilder2.append(" добавим еще больше текста ");

        System.out.println(notProblem.getBuilder()); //инициализируем

        System.out.println("=======================================");

        //3. ВСЁ ОК !!!
        //создадим StringBuilder, чтобы передать в аргумент объекту ImmutableSb
        String str = "инициализируем";
        ImmutableString notStrProblem = new ImmutableString(str);

        //sb2 снаружи остался доступен и можем к нему что-то добавить, но он уже
        //ни как не повлияет на объект ImmutableSb (т.к. там создается в конструкторе
        // другой объект StringBuilder)
        str.concat(" добавим текст ");

        //Полученный gotBuilder2 тоже не будет ссылаться на наш объект, который
        // находится в поле builder, т.к. возвращается через геттер не наш объект,
        // а похожий (такой же по наполнению) !!!
        String gotStrBuilder = notStrProblem.getBuilder();

        //т.е. миссия - получить immutable объект из класса ImmutableSb,
        //достигнута, изменять находящийся объект внутри поля builder
        //не получится !!!

        //здесь в sb2 добавим что-то, просто чтобы видеть что он ни как не влияет на наш ImmutableSb !!!
        str.concat(" добавим больше текста ");

        //а здесь в gotBuilder2 добавим что-то, просто, чтобы видеть, что он тоже ни как не влияет на наш ImmutableSb !!!
        gotStrBuilder.concat(" добавим еще больше текста "); //String не изменяется, он создает после вызова метода другой стринг !!!

        System.out.println(notStrProblem.getBuilder()); //инициализируем
    }
}
