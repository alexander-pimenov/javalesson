package ru.pimalex1978;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

//https://habr.com/ru/post/438870/
//Lombok возвращает величие Java
// Аннотация @FieldDefaults добавляет ко всем полям окончательный и приватный модификаторы.
// @RequiredArgsConstructor создаёт конструктор, который устанавливает экземпляр UserDao.
// Аннотация @NonNull добавляет проверку в конструкторе и создаёт исключение NullPointerException,
// если экземпляр UserDao равен нулю.
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class UserService {
    @NonNull UserDao userDao;
}

//Без Lombok
// public class UserService {
//
//   private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);
//
//   private final UserDao userDao;
//
//   @java.beans.ConstructorProperties({"userDao"})
//   public UserService(UserDao userDao) {
//       if (userDao == null) {
//           throw new NullPointerException("userDao is marked @NonNull but is null")
//       }
//       this.userDao = userDao;
//   }
//
// }
