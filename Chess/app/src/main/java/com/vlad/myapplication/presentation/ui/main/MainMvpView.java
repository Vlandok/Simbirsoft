package com.vlad.myapplication.presentation.ui.main;

import com.arellomobile.mvp.MvpView;

public interface MainMvpView extends MvpView {
    void showChessBoard(String[][] chessBoardArray);
    void showProgressView();
    void hideProgressView();
    void clearTextViewChessBoard();

    void showChessBoardExample(String example);
}
