package com.vlad.lesson3;

public class Circle implements Shape {

    final static double PI = 3.14;

    private double diameter = 1.6;

    @Override
    public double perimeter() {
        return PI * diameter;
    }

    @Override
    public double area() {
        return (PI * diameter * diameter) / 4;
    }
}
