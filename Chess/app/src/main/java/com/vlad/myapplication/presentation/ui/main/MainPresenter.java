package com.vlad.myapplication.presentation.ui.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vlad.myapplication.AlgorithmFillingTypes;
import com.vlad.myapplication.model.AbstractAlgorithmFilling;
import com.vlad.myapplication.model.AlgorithmFillingFactory;
import com.vlad.myapplication.model.ChessBoard;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.vlad.myapplication.presentation.ui.main.MainActivity.COUNT_FIGURES;


@InjectViewState
public class MainPresenter extends MvpPresenter<MainMvpView> {

    private ChessBoard chessBoard;
    private AlgorithmFillingFactory algorithmFillingFactory;
    private AbstractAlgorithmFilling algorithmFillingThroughTheCell;
    private AbstractAlgorithmFilling algorithmFillingSingleInLine;
    private AbstractAlgorithmFilling algorithmFillingRandom;
    private Disposable disposable;

    public MainPresenter(AlgorithmFillingFactory algorithmFillingFactory, ChessBoard chessBoard) {
        this.algorithmFillingFactory = algorithmFillingFactory;
        this.chessBoard = chessBoard;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        showChessBoard();
    }

    private Flowable<AbstractAlgorithmFilling> getAbstractAlgorithmFilling() {
        algorithmFillingThroughTheCell
                = algorithmFillingFactory.getAlgorithmFilling(AlgorithmFillingTypes.THROUGHTHECELL);
        algorithmFillingSingleInLine
                = algorithmFillingFactory.getAlgorithmFilling(AlgorithmFillingTypes.SINGLEINLINE);
        algorithmFillingRandom
                = algorithmFillingFactory.getAlgorithmFilling(AlgorithmFillingTypes.RANDOM);

        return Flowable.just(algorithmFillingThroughTheCell,
                algorithmFillingSingleInLine, algorithmFillingRandom);
    }

    private void showChessBoard() {
        getViewState().showChessBoard(chessBoard.getChessBoard());
        disposable = getAbstractAlgorithmFilling()
                .parallel()
                .runOn(Schedulers.computation())
                .map(algorithmFilling -> algorithmFilling.getFillingFigures(COUNT_FIGURES))
                .sequential()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> getViewState().showProgressView())
                .doOnTerminate(() -> getViewState().hideProgressView())
                .subscribe(algorithmFillingArray -> {
                    getViewState().clearTextViewChessBoard();
                    chessBoard.setFiguresOnBoard(algorithmFillingArray);
                    getViewState().showChessBoard(chessBoard.getChessBoard());
                    chessBoard.clearChessBoard();
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
