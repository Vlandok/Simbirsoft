package com.vlad.myapplication;

import com.vlad.myapplication.model.AbstractChessBoard;

import java.util.Arrays;
import java.util.List;

public class Main {
    private final static int COUNT_FIGURES = 8;

    public static void main(String[] args) {
        ChessBoardFactory chessBoardFactory = new ChessBoardFactory();

        AbstractChessBoard chessBoardThroughTheCell
                = chessBoardFactory.getChessBoard(ChessBoardTypes.THROUGHTHECELL);
        AbstractChessBoard chessBoardSingleInLine
                = chessBoardFactory.getChessBoard(ChessBoardTypes.SINGLEINLINE);
        AbstractChessBoard chessBoardRandom = chessBoardFactory.getChessBoard(ChessBoardTypes.RANDOM);

        List<AbstractChessBoard> chessBoardList = Arrays.asList(chessBoardThroughTheCell,
                chessBoardSingleInLine, chessBoardRandom);
        for (AbstractChessBoard chessBoard : chessBoardList) {
            chessBoard.setFiguresOnBoard(COUNT_FIGURES);
            chessBoard.showChessBoard();
            System.out.println();
        }
    }
}
