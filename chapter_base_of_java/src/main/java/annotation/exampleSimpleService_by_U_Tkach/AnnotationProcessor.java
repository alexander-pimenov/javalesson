package annotation.exampleSimpleService_by_U_Tkach;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* Создание собственных аннотаций - Annotations
 * https://www.youtube.com/watch?v=9BbxPd3GPeU
 * https://www.youtube.com/watch?v=y8gkmqFvybs&t=154s*/
public class AnnotationProcessor {
    static Map<String, Object> servicesMap = new HashMap<>();

    public static void main(String[] args) {
        inspectService(SimpleService.class);
        inspectService(LazyService.class);
//        inspectService(String.class);

        loadService(SimpleService.class.getName()); //"annotation.exampleSimpleService.SimpleService"
        loadService(LazyService.class.getName()); //"annotation.exampleSimpleService.LazyService"
        loadService("java.lang.String");

        Object superSimpleService = servicesMap.get("SuperSimpleService");
        System.out.println(superSimpleService);
        System.out.println(servicesMap.size());

        invokeMethod(SimpleService.class.getName());
        invokeMethod(LazyService.class.getName());
        invokeMethod("java.lang.String");
    }

    static void inspectService(Class<?> service) {
        /*Получим все public методы у класса, а также у его суперкласса*/
        Method[] methods = service.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Init.class)) {
                Init annInit = m.getAnnotation(Init.class);
                System.out.println("Аннотация @Init есть в методе " + m.getName());
            } else {
                System.out.println("Sorry, нет такой аннотации у метода " + m.getName());
            }
        }

        //У сервиса спрашиваем есть ли у него аннотация Service,
        //если есть, то выводим её название.
        if (service.isAnnotationPresent(Service.class)) {
            Service ann = service.getAnnotation(Service.class);
            System.out.println("Аннотация @Service имеется у класса " + service.getName()
                    + "Имя аннотации: " + ann.name());
        } else {
            System.out.println("Sorry, нет такой аннотации у " + service.getName());
        }
    }

    /**
     * Динамически создаем объекты сервисов по имени класса, если у них есть
     * аннотация @Service.
     * Записываем экземпляры сервисов в Map по имени, полученному
     * из аннотации.
     *
     * @param className полное строковое имя класса.
     */
    static void loadService(String className) {
        try {
            //загружаем класс
            Class<?> clazz = Class.forName(className);
            //проверяем есть ли у нашего класса аннотация @Service
            if (clazz.isAnnotationPresent(Service.class)) {
                //если есть, то создаем объект класса
                Object serviceObj = clazz.newInstance();
                //добавляем в мапу объект по имени их аннотации
                String name = clazz.getAnnotation(Service.class).name();
                servicesMap.put(name, serviceObj);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println(e.getCause() + " : " + e.getMessage());
//            e.printStackTrace();
        }
    }

    /**
     * Динамически создаем объект класса. Проходим по его методам
     * и если у мета есть аннотация @Init, то запускаем метод.
     *
     * @param className полное строковое имя класса.
     */
    static void invokeMethod(String className) {
        Class<?> clazz = null;
        Object serviceObj = null;
        try {
            clazz = Class.forName(className);
            serviceObj = clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Method[] methods = clazz.getDeclaredMethods();
        //бежим по всем методам
        for (Method method : methods) {
            if (method.isAnnotationPresent(Init.class)) {
                try {

                    method.invoke(serviceObj);
                } catch (Exception e) {
                    Init ann = method.getAnnotation(Init.class);
                    if (ann.suppressException()) {
                        System.err.println(e.getMessage()); //логируем иксепшен
                    } else {
                        //чтобы не обязательно было его отлавливать, ставим
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
