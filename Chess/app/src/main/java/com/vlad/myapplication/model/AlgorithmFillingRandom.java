package com.vlad.myapplication.model;

import java.util.concurrent.Callable;

import static com.vlad.myapplication.model.ChessBoard.HEIGHT_BOARD;
import static com.vlad.myapplication.model.ChessBoard.WIDTH_BOARD;

public class AlgorithmFillingRandom extends AbstractAlgorithmFilling implements Callable {

    /**
     * Получение случайного расположения фигур на шахматной доске
     *
     * @param count количество фигур
     * @return расположение фигур
     */
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

    @Override
    public Object call() throws Exception {
        return getFillingFigures(8);
    }
}
