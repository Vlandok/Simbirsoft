package com.vlad.myapplication.model;

import java.util.Random;

import static com.vlad.myapplication.model.ChessBoard.HEIGHT_BOARD;
import static com.vlad.myapplication.model.ChessBoard.WIDTH_BOARD;

public abstract class AbstractAlgorithmFilling {
    protected Random random;
    protected String[][] chessBoard;

    public AbstractAlgorithmFilling() {
        random = new Random();
        chessBoard = new String[WIDTH_BOARD][HEIGHT_BOARD];
    }

    public abstract String[][] getFillingFigures(int count);
}
