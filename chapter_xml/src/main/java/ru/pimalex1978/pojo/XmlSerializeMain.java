package ru.pimalex1978.pojo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Сохранение объекта POJO в XML формате.
 * Результат преобразования находится в папке data (в корне проекта)
 * И обратное преобразование из XML в java объект.
 */
public class XmlSerializeMain {
    public static void main(String[] args) {
        try (XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream("data/serial.xml")))) {

            Order order = new Order(22, 77);
            xmlEncoder.writeObject(order);
            xmlEncoder.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream("data/serial.xml")))) {

            Order order = (Order) xmlDecoder.readObject();
            System.out.println(order);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
//Создание XML файла
//<?xml version="1.0" encoding="UTF-8"?>
//<java version="12.0.2" class="java.beans.XMLDecoder">
// <object class="pojo.Order"> //имя класса
//  <void property="amount"> //property в алфавитном порядке
//   <double>77.0</double>
//  </void>
//  <void property="orderId">
//   <long>22</long>
//  </void>
// </object>
//</java>

//Чтение из XML файла
//Order[orderId=22,amount=77.0]
