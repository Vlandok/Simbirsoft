package com.vlad.myapplication.presentation.ui.main;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vlad.myapplication.AlgorithmFillingTypes;
import com.vlad.myapplication.model.AbstractAlgorithmFilling;
import com.vlad.myapplication.model.AlgorithmFillingFactory;
import com.vlad.myapplication.model.ChessBoard;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        chessBoard = new ChessBoard();
        algorithmFillingFactory = new AlgorithmFillingFactory();
//        showChessBoard();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            initAbstractAlgorithmFilling();
            showBoard();
        }
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

    private void initAbstractAlgorithmFilling() {
        algorithmFillingThroughTheCell
                = algorithmFillingFactory.getAlgorithmFilling(AlgorithmFillingTypes.THROUGHTHECELL);
        algorithmFillingSingleInLine
                = algorithmFillingFactory.getAlgorithmFilling(AlgorithmFillingTypes.SINGLEINLINE);
        algorithmFillingRandom
                = algorithmFillingFactory.getAlgorithmFilling(AlgorithmFillingTypes.RANDOM);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showBoard() {
        //        ForkJoinPool commonPool = ForkJoinPool.commonPool();

        ExecutorService executor = Executors.newWorkStealingPool();
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

//        CompletionService<String[][]> completionService = new ExecutorCompletionService<>(executor);

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 4),
                callable("task2", 1),
                callable("task3", 8));

//        List<Callable<String[][]>> callables = Arrays.asList(
//                () -> algorithmFillingRandom.getFillingFigures(COUNT_FIGURES),
//                () -> algorithmFillingThroughTheCell.getFillingFigures(COUNT_FIGURES),
//                () -> algorithmFillingSingleInLine.getFillingFigures(COUNT_FIGURES));

//        for (final Callable<String[][]> callable : callables) {
//            completionService.submit(callable);
//        }

        for (final Callable<String> callable : callables) {
            completionService.submit(callable);
        }
        executor.shutdown();
        try {
            while (!executor.isTerminated()) {
                Future<String> future = completionService.take();
                getViewState().showProgressView();
//                String[][] result = future.get();
//                getViewState().clearTextViewChessBoard();
//                chessBoard.setFiguresOnBoard(result);
                getViewState().showChessBoardExample(future.get());
                chessBoard.clearChessBoard();
                getViewState().hideProgressView();


//                System.out.println(future.get());
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }

//        try {
//            executor.invokeAll(callables)
//                    .parallelStream()
//                    .map(future -> {
//                        try {
//                            return future.get();
//                        } catch (Exception e) {
//                            throw new IllegalStateException(e);
//                        }
//                    })
//                    .sequential()
//                    .forEach(algorithmFillingArray -> {
////                        getViewState().clearTextViewChessBoard();
//                        System.out.println(algorithmFillingArray);
////                        chessBoard.setFiguresOnBoard(algorithmFillingArray);
////                        getViewState().showChessBoard(chessBoard.getChessBoard());
////                        chessBoard.clearChessBoard();
////                        getViewState().hideProgressView();
//                    });
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private void example() {
//        ExecutorService executorService = Executors.newWorkStealingPool();
//        executorService.submit((Runnable) algorithmFillingRandom);
//        executorService.submit((Runnable) algorithmFillingSingleInLine);
//        executorService.submit((Runnable) algorithmFillingThroughTheCell);
//    }

    Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }
}
