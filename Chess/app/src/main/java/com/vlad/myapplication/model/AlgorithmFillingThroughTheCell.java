package com.vlad.myapplication.model;

import java.util.ArrayList;
import java.util.List;

import static com.vlad.myapplication.model.ChessBoard.HEIGHT_BOARD;
import static com.vlad.myapplication.model.ChessBoard.WIDTH_BOARD;

public class AlgorithmFillingThroughTheCell extends AbstractAlgorithmFilling {
    /**
     * Получение расположения фигур на шахматной доске с минимальным отступом одна клетка
     *
     * @param count количество фигур
     * @return расположение фигур
     */
    @Override
    public String[][] getFillingFigures(int count) {
        chessBoard = new String[WIDTH_BOARD][HEIGHT_BOARD];
        List<Integer> listPositionEvenLineWithFigures = new ArrayList<>();
        List<Integer> listPositionUnEvenLineWithFigures = new ArrayList<>();
        for (int i = 0; i < HEIGHT_BOARD; i++) {
            if (i % 2 == 0) {
                listPositionEvenLineWithFigures.clear();
            } else {
                listPositionUnEvenLineWithFigures.clear();
            }
            for (int j = 0; j < WIDTH_BOARD; j++) {
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
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return chessBoard;
    }
}
