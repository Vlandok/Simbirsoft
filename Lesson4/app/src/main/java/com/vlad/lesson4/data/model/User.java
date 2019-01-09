package com.vlad.lesson4.data.model;

public class User {
    private String name;
    private String lastName;
    private int birthDay;
    private String birthMonth;
    private int birthYears;
    private String fieldActivity;
    private boolean isPushNotifications;

    public User(String name, String lastName, int birthDay,
                String birthMonth, int birthYears,
                String fieldActivity, boolean isPushNotifications) {
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYears = birthYears;
        this.fieldActivity = fieldActivity;
        this.isPushNotifications = isPushNotifications;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public int getBirthYears() {
        return birthYears;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setBirthYears(int birthYears) {
        this.birthYears = birthYears;
    }

    public String getFieldActivity() {
        return fieldActivity;
    }

    public String getLastName() {
        return lastName;
    }


    public String getName() {
        return name;
    }

    public boolean isPushNotifications() {
        return isPushNotifications;
    }

    public void setFieldActivity(String fieldActivity) {
        this.fieldActivity = fieldActivity;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPushNotifications(boolean pushNotifications) {
        isPushNotifications = pushNotifications;
    }

}

