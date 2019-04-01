package com.vlad.myapplication.model;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardThroughTheCell extends AbstractChessBoard {
    @Override
    public void setFiguresOnBoard(int count) {
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
}
