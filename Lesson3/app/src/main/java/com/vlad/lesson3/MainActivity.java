package com.vlad.lesson3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final static String POINT_ONE = "one";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Начало 2-ого пункта
        Runnable myClosure = () -> System.out.println("I love Java");
        myClosure.printer();
        repeatTask(10, myClosure);
        //конец 2-ого пункта


        //Начало 6-ого пункта
        manyMove();
        //конец 6-ого пункта

        //Начало 8-ого пункта
        Rectangle rectangle = new Rectangle();
        System.out.println(rectangle.perimeter());
        System.out.println(rectangle.area());
        //конец 8-ого пункта

        //Начало 9-ого пункта
        Square square = new Square();
        System.out.println(square.perimeter());
        System.out.println(square.area());
        //конец 9-ого пункта

        //Начало 10-ого пункта
        Circle circle = new Circle();
        System.out.println(circle.perimeter());
        System.out.println(circle.area());
        //конец 10-ого пункта
    }

    private void repeatTask(int times, Runnable task) {
        for (int i = 0; i < times; i++) {
            task.printer();
        }
    }

    private int getLatLng(int lat, int lng, Directions move) {
        switch (move) {
            case UP:
                lng++;
                return lng;
            case DOWN:
                lng--;
                return lng;
            case LEFT:
                lat--;
                return lat;
            case RIGHT:
                lat++;
                return lat;
            default:
                throw new RuntimeException("Хмм, ошибочка");
        }
    }

    private void manyMove() {
        Location location = new Location(0, 0);
        Directions[] moveList = {Directions.UP, Directions.UP, Directions.LEFT, Directions.DOWN, Directions.LEFT,
                Directions.DOWN, Directions.DOWN, Directions.RIGHT, Directions.RIGHT, Directions.DOWN, Directions.RIGHT};
        for (Directions move : moveList) {
            int value = getLatLng(location.getLat(), location.getLng(), move);
            if (move == Directions.UP || move == Directions.DOWN) {
                location.setLng(value);
            } else {
                location.setLat(value);
            }
            System.out.println("Координаты " + location.getLat() + " и " + location.getLng());
        }
    }
}


