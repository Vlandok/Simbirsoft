package com.vlad.myapplication.model;

import java.util.Random;

public abstract class AbstractChessBoard {
    final int width = 8;
    final int height = 8;
    String[][] chessBoard;
    Random random;

    AbstractChessBoard() {
        random = new Random();
        chessBoard = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                chessBoard[i][j] = "[ ]";
            }
        }
    }

    public abstract void setFiguresOnBoard(int count);

    public void showChessBoard() {
        for (String[] fieldBoardArray : chessBoard) {
            for (String fieldBoard : fieldBoardArray) {
                System.out.print(fieldBoard + "\t");
            }
            System.out.println();
        }
    }
}
