package com.vlad.myapplication.presentation.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.vlad.myapplication.R;
import com.vlad.myapplication.model.AlgorithmFillingFactory;
import com.vlad.myapplication.model.ChessBoard;

public class MainActivity extends MvpAppCompatActivity implements MainMvpView {

    public final static int COUNT_FIGURES = 8;

    @InjectPresenter
    MainPresenter mainPresenter;
    TextView textViewChessBoard;
    ProgressBar progressBar;

    @ProvidePresenter
    MainPresenter provideMainPresenter() {
        return new MainPresenter(new AlgorithmFillingFactory(), new ChessBoard());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewChessBoard = findViewById(R.id.textViewChessBoard);
        progressBar = findViewById(R.id.progressBarMain);
    }

    @Override
    public void showProgressView() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressView() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showChessBoard(String[][] chessBoardArray) {
        for (String[] fieldBoardArray : chessBoardArray) {
            for (String fieldBoard : fieldBoardArray) {
                textViewChessBoard.append(fieldBoard + "\t");
            }
            textViewChessBoard.append("\n");
        }
    }

    @Override
    public void clearTextViewChessBoard() {
        textViewChessBoard.setText("");
    }
}
