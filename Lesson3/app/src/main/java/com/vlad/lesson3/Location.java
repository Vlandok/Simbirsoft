package com.vlad.lesson3;

public class Location {
    private int lat;
    private int lng;

    Location(int lat, int lng) {
        this.lat = lat;
        this.lng = lng;
    }


    public int getLat() {
        return lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }
}
