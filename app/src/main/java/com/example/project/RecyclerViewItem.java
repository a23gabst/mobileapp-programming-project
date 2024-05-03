package com.example.project;

@SuppressWarnings("WeakerAccess")
public class RecyclerViewItem {
    private String name;
    private String genre;
    private int cost;

    public RecyclerViewItem(String name, String genre, int cost) {
        this.name = name;
        this.genre = genre;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getCost() {
        return cost;
    }
}
