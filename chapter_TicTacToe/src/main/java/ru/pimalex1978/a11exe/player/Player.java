package ru.pimalex1978.a11exe.player;


import ru.pimalex1978.a11exe.logic.Mark;
import ru.pimalex1978.a11exe.logic.Point;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 09.07.2019
 */
public interface Player {

    Point move();

    String getMoveMessage();

    void setMark(Mark mark);

}