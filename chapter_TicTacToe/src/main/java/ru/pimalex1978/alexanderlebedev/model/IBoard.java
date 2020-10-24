package ru.pimalex1978.alexanderlebedev.model;

public interface IBoard {

    void printTable();
    char[][] getBoard();
    Cell getCell();
    int size();
}