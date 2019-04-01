package com.vlad.myapplication;

import com.vlad.myapplication.model.AbstractChessBoard;
import com.vlad.myapplication.model.ChessBoardRandom;
import com.vlad.myapplication.model.ChessBoardSingleInLine;
import com.vlad.myapplication.model.ChessBoardThroughTheCell;

class ChessBoardFactory {
    AbstractChessBoard getChessBoard(ChessBoardTypes type) {
        switch (type) {
            case THROUGHTHECELL:
                return new ChessBoardThroughTheCell();
            case SINGLEINLINE:
                return new ChessBoardSingleInLine();
            case RANDOM:
                return new ChessBoardRandom();
            default:
                throw new IllegalArgumentException("Неизвестный тип шахматной доски:" + type);
        }
    }
}
