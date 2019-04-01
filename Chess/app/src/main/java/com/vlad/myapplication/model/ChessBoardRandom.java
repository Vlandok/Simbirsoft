package com.vlad.myapplication.model;

public class ChessBoardRandom extends AbstractChessBoard {
    @Override
    public void setFiguresOnBoard(int count) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (count > 0) {
                    int randomPositionY = random.nextInt(height);
                    int randomPositionX = random.nextInt(width);
                    if (chessBoard[randomPositionY][randomPositionX].equals("[ ]")) {
                        chessBoard[randomPositionY][randomPositionX] = "[*]";
                        count--;
                    }
                }
            }
        }
    }
}
