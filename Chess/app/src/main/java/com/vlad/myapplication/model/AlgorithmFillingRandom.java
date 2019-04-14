package com.vlad.myapplication.model;

import static com.vlad.myapplication.model.ChessBoard.HEIGHT_BOARD;
import static com.vlad.myapplication.model.ChessBoard.WIDTH_BOARD;

public class AlgorithmFillingRandom extends AbstractAlgorithmFilling {

    @Override
    public String[][] getFillingFigures(int count) {
        chessBoard = new String[WIDTH_BOARD][HEIGHT_BOARD];
        for (int i = 0; i < HEIGHT_BOARD; i++) {
            for (int j = 0; j < WIDTH_BOARD; j++) {
                if (count > 0) {
                    int randomPositionY = random.nextInt(HEIGHT_BOARD);
                    int randomPositionX = random.nextInt(WIDTH_BOARD);
                    if (chessBoard[randomPositionY][randomPositionX] == null) {
                        chessBoard[randomPositionY][randomPositionX] = "[*]";
                        count--;
                    }
                }
            }
        }
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return chessBoard;
    }
}
