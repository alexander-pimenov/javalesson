package ru.pimalex1978;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

//https://habr.com/ru/post/438870/
//Lombok возвращает величие Java
//Аннотация @Value аналогична @Data за исключением того, что все поля по умолчанию являются закрытыми и окончательными,
// а сеттеры не создаются.
// Благодаря этому объекты @Value сразу становятся неизменяемыми.
// Поскольку все поля являются окончательными, конструктора аргументов нет.
// Вместо этого Lombok использует @AllArgsConstructor.
// В результате получается полностью функциональный, неизменяемый объект.
@Value
@Builder(toBuilder = true)
public class User {
    @NonNull
    UUID userId;
    @NonNull
    String email;
    @Singular
    Set<String> favoriteFoods;
    @NonNull
    @Builder.Default
    String avatar = "default.png";
}
//@Value
//@Builder(toBuilder = true)
//public class User {
//    @NonNull
//    UUID userId;
//    @NonNull
//    String email;
//    @Singular
//    Set<String> favoriteFoods;
//    @NonNull
//    @Builder.Default
//    String avatar = "default.png";
//}

//@Data — просто удобная аннотация, которая применяет сразу несколько аннотаций Lombok.
//Она на этапе компиляции сгенерирует геттеры\сеттеры для всех полей, toString и переопределит
// equals и hashCode по стандартам.
// В IDE можно установить плагин и он будет видеть все ещё не созданные методы.
//
//@ToString генерирует реализацию для метода toString(), которая состоит из аккуратного
// представления объекта: имя класса, все поля и их значения.
//@EqualsAndHashCode генерирует реализации equals и hashCode, которые по умолчанию
// используют нестатические и нестационарные поля, но настраиваются.
//@Getter / @Setter генерирует геттеры и сеттеры для частных полей.
//@RequiredArgsConstructor создаёт конструктор с требуемыми аргументами, где обязательными
// являются окончательные поля и поля с аннотацией @NonNull (подробнее об этом ниже).