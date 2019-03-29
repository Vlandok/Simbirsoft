package com.vlad.myapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChessBoard {
    private final int width = 8;
    private final int height = 8;
    private String[][] chessBoard;

    public ChessBoard() {
        chessBoard = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                chessBoard[i][j] = "[ ]";
            }
        }
    }

    public void setFiguresOnBoard(int count) {
        Random random = new Random();
        List<Integer> positionFigure = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (count > 0) {
                    if (random.nextBoolean()) {
                        if (positionFigure.isEmpty()) {
                            positionFigure.add(j);
                            chessBoard[i][j] = "[*]";
                            count--;
                        } else {
                            int sizePositionFigure = positionFigure.size();
                            for (int index = 0; index < sizePositionFigure; index++) {
//                                for (int position : positionFigure) {
//                                    if (j != position - 1 && j != position + 1) {
                                if (j < positionFigure.get(index) - 1
                                        || j > positionFigure.get(index) + 1) {
                                    chessBoard[i][j] = "[*]";
                                    positionFigure.add(j);
                                    count--;
                                }
                            }
                        }
                    }
                } else {
                    break;
                }
            }
        }
    }

    public void setFiguresOnBoardSingle(int count) {
        Random random = new Random();
        for (int i = 0; i < width; i++) {
            if (count > 0) {
                chessBoard[i][random.nextInt(width)] = "[*]";
                count--;
            }
        }
    }

    public void showChessBoard() {
        for (String[] fieldBoardArray : chessBoard) {
            for (String fieldBoard : fieldBoardArray) {
                System.out.print(fieldBoard + "\t");
            }
            System.out.println();
        }
    }
}
