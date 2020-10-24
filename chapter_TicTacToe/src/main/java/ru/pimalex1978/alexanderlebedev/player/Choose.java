package ru.pimalex1978.alexanderlebedev.player;

import ru.pimalex1978.alexanderlebedev.input.Input;
import ru.pimalex1978.alexanderlebedev.logic.Logic;
import ru.pimalex1978.alexanderlebedev.model.IBoard;

public interface Choose {

    Player[] getPlayers(Input input, IBoard board, Logic logic);
}