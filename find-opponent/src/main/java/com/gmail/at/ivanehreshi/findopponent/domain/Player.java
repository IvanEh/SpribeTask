package com.gmail.at.ivanehreshi.findopponent.domain;

public class Player {
    private String name;
    private double rating;

    public Player() {
    }

    public Player(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
