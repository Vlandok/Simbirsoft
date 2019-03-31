package com.vlad.myapplication;

public class Main {
    private final static int COUNT_FIGURES = 8;

    public static void main(String[] args) {
        ChessBoard chessBoardTaskOne = new ChessBoard();
        chessBoardTaskOne.setFiguresOnBoard(COUNT_FIGURES);
        chessBoardTaskOne.showChessBoard();

        System.out.println();

        ChessBoard chessBoardTaskTwo = new ChessBoard();
        chessBoardTaskTwo.setFiguresOnBoardSingle(COUNT_FIGURES);
        chessBoardTaskTwo.showChessBoard();
    }
}
