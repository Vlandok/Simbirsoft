package com.vlad.lesson2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private int[] fibonacci;
    private int[] fourthPoint = {10, 3, 7, 2, 5, 8};

    final double a = 4.5;
    final double b = 3.5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1-ый пункт
        double average = getAverage(a, b);
        Log.d("average", String.valueOf(average));
        // конец 1-ого пункта

        // 2-ой пункт
        SecondPoint people = new SecondPoint();
        System.out.println("Full name: " + people.firstName + " " + people.lastName + " ");
        // конец 2-ого пункта

        //3-ий пункт
        initFibonacci(15);
        showFibonacci();
        //конец 3-ого пункта

        //4-ый пукнт
        sortArray(fourthPoint);
        //конец 4-ого пункта

        //5-ый пукнт
        String fivePoint = "abc123";
        String newString = fivePoint(fivePoint);
        Log.d("fivePoint", newString);
        //конец 5-ого пункта
    }

    double getAverage(double a, double b) {
        return (a + b) / 2;
    }

    void initFibonacci(int n) {
        fibonacci = new int[n];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        for (int i = 2; i < n; ++i) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
    }

    void showFibonacci() {
        for (int valueFibonacci : fibonacci) {
            System.out.println(valueFibonacci);
        }
    }

    void sortArray(int[] arrayInt) {
        for (int k = arrayInt.length - 1; k >= 1; k--) {
            for (int i = 0; i < arrayInt.length - 1; i++) {
                if (arrayInt[i] > arrayInt[i + 1]) {
                    int saveValue = arrayInt[i];
                    arrayInt[i] = arrayInt[i + 1];
                    arrayInt[i + 1] = saveValue;
                }
            }
        }
    }

    String fivePoint(String string) {
        try {
            int numbers = Integer.parseInt(string.replaceAll("[^0-9]", ""));
            int newNumbers = numbers + 1;
            return string.replaceAll(String.valueOf(numbers), String.valueOf(newNumbers));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Не получилось";
        }
    }

    public class SecondPoint {
        String firstName = "Влад";
        String lastName = "Маскайкин";
    }
}
