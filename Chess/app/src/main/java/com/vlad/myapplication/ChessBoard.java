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
        List<Integer> listPositionEvenLineWithFigures = new ArrayList<>();
        List<Integer> listPositionUnEvenLineWithFigures = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            if (i % 2 == 0) {
                listPositionEvenLineWithFigures.clear();
            } else {
                listPositionUnEvenLineWithFigures.clear();
            }
            for (int j = 0; j < height; j++) {
                if (count > 0) {
                    if (random.nextBoolean()) {
                        boolean isCanInsertFigure = true;
                        if (!listPositionEvenLineWithFigures.isEmpty() ||
                                !listPositionUnEvenLineWithFigures.isEmpty()) {
                            List<Integer> allPositionsWithFigures = new ArrayList<>();
                            allPositionsWithFigures.addAll(listPositionEvenLineWithFigures);
                            allPositionsWithFigures.addAll(listPositionUnEvenLineWithFigures);
                            for (int positionFigure : allPositionsWithFigures) {
                                if (j >= positionFigure - 1 && j <= positionFigure + 1) {
                                    isCanInsertFigure = false;
                                    break;
                                }
                            }
                        }
                        if (isCanInsertFigure) {
                            if (i % 2 == 0) {
                                listPositionEvenLineWithFigures.add(j);
                            } else {
                                listPositionUnEvenLineWithFigures.add(j);
                            }
                            chessBoard[i][j] = "[*]";
                            count--;
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
