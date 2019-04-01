package com.vlad.myapplication.model;

public class ChessBoardSingleInLine extends AbstractChessBoard {
    @Override
    public void setFiguresOnBoard(int count) {
        for (int i = 0; i < width; i++) {
            if (count > 0) {
                chessBoard[i][random.nextInt(width)] = "[*]";
                count--;
            }
        }
    }
}
