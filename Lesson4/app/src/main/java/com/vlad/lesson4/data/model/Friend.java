package com.vlad.lesson4.data.model;

public class Friend {
    private String firstName;
    private String lastName;
    private int imageFriend;

    public Friend (String firstName, String lastName, int imageFriend ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageFriend = imageFriend;
    }

    public String getLastName() {
        return lastName;
    }

    public int getImageFriend() {
        return imageFriend;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setImageFriend(int imageFriend) {
        this.imageFriend = imageFriend;
    }
}
