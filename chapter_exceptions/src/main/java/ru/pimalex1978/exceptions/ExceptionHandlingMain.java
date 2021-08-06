package ru.pimalex1978.exceptions;

/*пример демонстрирующий обработки ошибок
 * обработка исключение должна быть такой, чтобы когда выпадали исключения(ошибки),
 * программа продолжала взаимодействовать с пользователем, спрашивала новые параметы
 * или выводила сообщение об ошибке, а не экстренно завершалась
 * ArithmeticException & InputMismatchException - это Unchecked exceptionsExample - обычные исключения
 * наследуют RuntimeException -
 * возникающие в результате ошибок программирования: плохо написан код, не тот передан параметр
 * Unchecked exceptionsExample мы не обязане хэндлидь, основная задача программиста сделать так, чтоб они
 * не возникали. Т.е. в случае с "0", на который делить нельзя, это надо проверить до того как он
 * будет передан в метод
 * Также Checked exceptionsExample (IOException) - это категория ошибок относящаяся к каким то сторонним ресурсам:
 * файлы на диске, проблемы доступа к базе, другие сетевые ресурсы. Checked exceptionsExample должны обязательно обрабатывать
 * Когда ловим эксепшены, то всегда надо быть более специфичным и ловить конкретные эксепшены, а не общий Exception
 * В Java механизмы обработки ошибок эксепшенов сложные, но очень эфективные.
 * Хорошо написаный Handling помогает очень сильно в поисках и решениях проблем и он позволяет программе не умирать, а продолжать работать
 *
 * В Java используется Модель Прекращения (Termination Model) для работы с исключениями.
 *
 * В Java подерживается два типа обработки или Задекларировать или Catch
 * Если задекларирован Unchecked exception, то мы его не обязаны в нашем main() ловить, т.е. помещать в блок try-catch
 * Но если мы имеем дело с Checked Exception , например IOException, мы обязаны добавлять catch-блок, т.е. обработать
 *
 * Стек-трейсы - e.printStackTrace(); - мы выводим для того, чтобы видеть, где у нас возникает проблема. Но их можно и не выводить
 *
 *
 * Рассмотрим также тему try-with-resources: в try() в круглых скобках можем инициализировать тот ресурс,
 * который хотим использовать*/

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandlingMain {

    public static void main(String[] args) {

        //обрабатываем наше собственное исключение
        //выделим doEverything(); и нажмем Ctrl-Alt-T или Code->Surround With...
        //и в ручную указываем в catch какой exception будет ловиться
        try {
            doEverything();
        } catch (InvalidInputParamException e) {
            System.out.println("InvalidInputParamException");
//            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        //попытаемся обработать наши exceptionsExample, поэтому оборачиваем doEverything в try-catch
        //выделим doEverything(); и нажмем Ctrl-Alt-T или Code->Surround With...
//        try {
//            doEverything();
//        } catch (NullPointerException e) {
//            System.out.println("NPE");
//            e.printStackTrace();
//            System.out.println("-----------------");
//            //можем работать с exception: метод getCause() - покажет, какой метод выбросил exception
//            //т.е. какой из exception стал причиной возникновения всей проблемы
//            //также например метод getStackTrace(), который возвращает массив

//            //Но нас интересует, getSuppressed()
//            //который возвращает нам тот exception который изначально был выброшен
//            //он возвращает массив эксепшенов
//            //и мы по индексу можем получить наш эксепшен  и вывести на экран

//            Throwable[] suppressed = e.getSuppressed();//метод который возвращает тот метод который изначально был выброшен
//            //для вывода на экран можно использовать цикл for, но т.к. у нас только 1 эксепшн, то просто по индексу выведем на экран
//            System.out.println(suppressed[0]);
//
//        }catch (ArrayIndexOutOfBoundsException e){ //этот блок ловит ArrayIndexOutOfBoundsException
//            System.out.println("ArrayIndexOutOfBoundsException");
//        }
    }

    //этот метод создан Ctrl-Alt-M, обернули часть кода в метод, для решения подавления одного исключения другим
    //это подавление нужно исправить, т.к. исключения должны обрабатываться а не подавляться
    private static void doEverything() throws IOException {
        Scanner scanner = new Scanner(System.in); // для ввода сконсоли нужен сканнер, инициализируем его

        //при выпадении ошибки, программа завершается, но нам это не нужно чтоб программа продолжалась
        //поэтому вводим преременную для продолжения цикла, по ней будем решать: продолжать программу или нет.
        boolean continueLoop = true;
//        PrintWriter writer= null;

        //и используем цикл do-while

        do {
            //поместим в блок try то место, где может возникнуть исключение
            //инициализируя в этом месте PrintWriter имеем большое приимущество, что он автоматически будет закрыт
            //т.е. нет необходимости в writer.close()
            //при возникновении exception все ресурсы будут закрыты автоматически
            //ну и также после выполнения блока try() они закроются автоматически
            try (PrintWriter writer = new PrintWriter(new FileWriter("out.txt")))
//                 BufferedReader reader = new BufferedReader(new FileReader("1234"))) //-это еще один ресурс для примера
            {
                System.out.println("Please enter numerator: ");
                int numerator = scanner.nextInt();
                System.out.println("Please enter denominator: ");
                int denominator = scanner.nextInt();
                System.out.println("Result is " + divide(numerator, denominator));

//                //для иммитации выбрасывания иксепшна, выбросим сами RuntimeException
//                //для дальнейшей работы он не понадобиться
//                if (continueLoop) {
//                    throw new RuntimeException("Runtime exception");
//                }

                //съимитируем иксепшн, при работе с массивами
                //будет выбрасывать exception, т.к. выходим за пределы массива
//                int[] intArray = new int[1];
//                int i = intArray[2];


// убираем его отсюда,т.к. он инициализируется в ( ) в try
//              writer = new PrintWriter(new FileWriter("out.txt"));
                writer.println("Result = " + divide(numerator, denominator));


                //если нормально выполнили программу, никаких исключений (..деление на 0, буквы вместо цифр...) не было,
                // то присвоим continueLoop false
                continueLoop = false;

                //далее используем multi-catch-block:
                //указывая эксепшены через пайп лайн (pipe line - | ), т.к. внутри блоков код у них одинаковый
            } catch (ArithmeticException | InputMismatchException e) { //и ловим эти ошибки и както с ними работаем
                System.out.println("Exception : " + e);
                //ae.printStackTrace();
                scanner.nextLine();//переходим на следующую строку
                System.out.println("Only integer non-zero parameters allowed");
//            в этом блоке нет необходимости, не будем дублировать код
//            } catch (InputMismatchException ime) {
//                System.out.println("Exception : " + ime);
//                //ime.printStackTrace();
//                scanner.nextLine();
//                System.out.println("Only integer values allowed");
            } catch (IOException e) {
                System.out.println("Unable to open file");
                e.printStackTrace();//чтоб указать, где возникла проблема
            } catch (IndexOutOfBoundsException e) {
                //пробросим здесь наш собственные exception
                //он выброшен в методе doEverything
                //обязательно указывая понятное, внятное сообщение, которое можно прочитать и определить,
                //какого рода проблема возникла
                throw new InvalidInputParamException("Array index out of bound. Thrown in doEverything" + e);


                //в этом catch можно ловить вообще все эксепшены, но тогда нет конкретики к сожалению,
                // а это не верно
//            }catch (Exception e){
//                System.out.println("All Exceptions here");
//                //этот эксепшен можно поймав, дальше пробросить по стеку, и ловить уже в другом блоке,
//                //у нас это блок catch в doEverything catch (ArrayIndexOutOfBoundsException e)
//                throw e;

                //в блок finally должен быть помещен код, который должен быть выполнен не зависимого от того
                //успешно ди выполнился наш блок try или нет
            } finally {
                System.out.println("Finally block called");
                //обязательно добавим проверку !!! ЭТИ ПРОВЕРКИ НУЖНО ДОБАВЛЯТЬ, ЧТОБ НЕ БЫЛО NPE
//                writer.close();

                //в этой проверке нет неоходимости, т.к. инициализация проходит в ( ) в try
                //это и есть ПРВЕРКА:
//                if (writer != null) {
//                    writer.close();
//                }

            }
            System.out.println("Try catch block finished");
        } while (continueLoop); //continueLoop используется в качестве параметра для цикла
    }
    //съимитируем ошибочную ситуацию
    //и в нормальной ситуации мы вообще не захотим чтоб возникала ошибка деления на 0
    //или ввод не числа. Но к сожалению эту проверку в методе здесь сделать не сможем
    //т.к. используется ввод с консоли
    //можем также указать для других программистов, что метод divide модет выбросить исключения
    //с помощью слова throws, т.е задекларируем иксепшн

    //написав это - throws ArithmeticException, NullPointerException, мы декларируем, что метод divide() может выбросить exception
    private static int divide(int numerator, int denominator) throws ArithmeticException, NullPointerException { //числитель, знаменатель
        return numerator / denominator; //если будет деление на 0 то возникнет exception
    }


    //съимируем появление Checked Exception
    //создадим метод сохранения результата деления в файл, т.е. будем сохранять результат деления в файл
    //передаем в него параметр (int res), который является результом нашего деления

//    //этот весь код перенесем в блок try для демонстрации, но лучше иметь отдельно, как есть:
//    private static void saveToFile(int res) throws IOException {
//        //PrintWriter - класс который записывает результат в файл
//        PrintWriter writer = new PrintWriter(new FileWriter("out.txt"));//в качестве параметра принимает объкт другого класса,
//        //который называется FileWriter, в который передаем имя файла куда записываем данные
//        //"out.txt" автоматически создастся в папке src нашего проекта
//        //FileWriter может бросать IOException, поэтому его прописали в методе saveToFile
//
//        //результат запишем в файл с помощью writer.println()
//        writer.println("Result = " + res);
//        //все файлы, все подлкючения к базе после использования мы обязаны закрывать
//        writer.close();
//    }
}
