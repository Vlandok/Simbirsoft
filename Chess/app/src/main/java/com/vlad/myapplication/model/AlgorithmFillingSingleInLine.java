package com.vlad.myapplication.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.vlad.myapplication.model.ChessBoard.HEIGHT_BOARD;
import static com.vlad.myapplication.model.ChessBoard.WIDTH_BOARD;

public class AlgorithmFillingSingleInLine extends AbstractAlgorithmFilling {
    /**
     * Получение расположения фигур на шахматной доске по одной на вертикальную/горизонтальную линию
     *
     * @param count количество фигур
     * @return расположение фигур
     */
    @Override
    public String[][] getFillingFigures(int count) {
        chessBoard = new String[WIDTH_BOARD][HEIGHT_BOARD];
        List<Integer> positionsWithFigures = new ArrayList<>();
        for (int i = 0; i < HEIGHT_BOARD; i++) {
            if (count > 0) {
                int positionX = random.nextInt(WIDTH_BOARD);
                if (!positionsWithFigures.contains(positionX)) {
                    positionsWithFigures.add(positionX);
                    chessBoard[i][positionX] = "[*]";
                    count--;
                } else {
                    i--;
                }
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return chessBoard;
    }
}
