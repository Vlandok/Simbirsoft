package com.vlad.myapplication.model;

import com.vlad.myapplication.AlgorithmFillingTypes;

public class AlgorithmFillingFactory {
    public AbstractAlgorithmFilling getAlgorithmFilling(AlgorithmFillingTypes type) {
        switch (type) {
            case THROUGHTHECELL:
                return new AlgorithmFillingThroughTheCell();
            case SINGLEINLINE:
                return new AlgorithmFillingSingleInLine();
            case RANDOM:
                return new AlgorithmFillingRandom();
            default:
                throw new IllegalArgumentException("Неизвестный тип алгоритма заполнения фигур:" + type);
        }
    }
}
