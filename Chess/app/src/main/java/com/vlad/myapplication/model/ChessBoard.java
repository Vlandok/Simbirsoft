package com.vlad.myapplication.model;

public class ChessBoard {
    final static int WIDTH_BOARD = 8;
    final static int HEIGHT_BOARD = 8;
    private String[][] chessBoard;

    public ChessBoard() {
        chessBoard = new String[HEIGHT_BOARD][WIDTH_BOARD];
        clearChessBoard();
    }

    public int getHeight() {
        return HEIGHT_BOARD;
    }

    public int getWidth() {
        return WIDTH_BOARD;
    }

    public String[][] getChessBoard() {
        return chessBoard;
    }

    public void setFiguresOnBoard(String[][] figures) {
        if (figures.length == chessBoard.length) {
            for (int i = 0; i < HEIGHT_BOARD; i++) {
                for (int j = 0; j < WIDTH_BOARD; j++) {
                    if (figures[i][j] != null) {
                        if (figures[i][j].equals("[*]")) {
                            chessBoard[i][j] = "[*]";
                        }
                    }
                }
            }
        } else {
            System.out.println("Не удалось выставить фигуры на шахматной доске:(");
        }
    }

    public void clearChessBoard() {
        for (int i = 0; i < HEIGHT_BOARD; i++) {
            for (int j = 0; j < WIDTH_BOARD; j++) {
                chessBoard[i][j] = "[ ]";
            }
        }
    }
}
