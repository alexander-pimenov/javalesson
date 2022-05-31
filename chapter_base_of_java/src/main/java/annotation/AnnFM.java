package annotation;

import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;

/*
* @Retention:
* Используется для аннотирования (примечания) уровня существования любого Annotation.
* В частности, есть 3 уровня распознания существования предмета с примечанием:
* RetentionPolicy.SOURCE: Существует на исходном коде, и не узнаваем компилятором
* (compiler).
* RetentionPolicy.CLASS: Компилятор распознает существование, но не распознается
* виртуальной машиной при запуске (Runtime).
* RetentionPolicy.RUNTIME: достигает высшую степень существования, распознается
* компилятором (compiler), и распознается существование виртуальной машиной при
* запуске.
*
* @Target:
* Используется для аннотирования (примечания) другого annotation, и в каких сферах
* тот annotation будет использоваться.
* Каждая аннотация, аннотированная @Target(ElementType.ANNOTATION_TYPE), называется
* Meta-annotation
* ElementType.TYPE - Прикреплен на объявлении Class, interface, enum, annotation.
* ElementType.FIELD - Прикреплен на объявлении поля (field), включая константы
* enum.
* ElementType.METHOD - Прикреплен на объявлении method.
* ElementType.PARAMETER - Прикреплен на объявлении parameter
* ElementType.CONSTRUCTOR - Прикреплен на объявлении конструктора
* ElementType.LOCAL_VARIABLE - Прикреплен на объявлении локальной переменной.
* ElementType.ANNOTATION_TYPE - Прикреплен на объявлении Annotation
* ElementType.PACKAGE - Прикреплен на объявлении package.
* */

// Данная аннотация (Annotation) говорит, что AnnFM только распознается
// на исходном коде.
// Он не будет распознан компилятором (compiler),
// и во время запуска виртуальной машины, так же не будет знать о его
// существовании.
@Retention(value = RetentionPolicy.SOURCE)

// Данная аннотация (Annotation) говорит:
// AnnFM будет использован только как аннотация (annotate) на FIELD или METHOD.
@Target(value = {ElementType.FIELD, ElementType.METHOD})
public @interface AnnFM {
}
