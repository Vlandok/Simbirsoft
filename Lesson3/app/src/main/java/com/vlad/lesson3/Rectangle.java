package com.vlad.lesson3;

public class Rectangle implements Shape {

    private double length = 2.1;
    private double width = 1.3;

    @Override
    public double perimeter() {
        return ((length + width) * 2);
    }

    @Override
    public double area() {
        return length * width;
    }
}
