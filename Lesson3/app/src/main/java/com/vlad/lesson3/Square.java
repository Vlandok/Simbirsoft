package com.vlad.lesson3;

public class Square implements Shape {
    private double length = 3.1;

    @Override
    public double perimeter() {
        return length * 4;
    }

    @Override
    public double area() {
        return length * length;
    }
}
