package com.vlad.myapplication;

public class Main {
    private final static int COUNT_FIGURES = 8;

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setFiguresOnBoard(COUNT_FIGURES);
        chessBoard.showChessBoard();
    }
}
