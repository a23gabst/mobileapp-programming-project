package com.example.project;

@SuppressWarnings("WeakerAccess")
public class RecyclerViewItem {
    private String name;
    private String genre;
    private int cost;

    private String artist;
    private int timeLine;
    private String origin;

    public RecyclerViewItem(String name, String genre, int cost) {
        this.name = name;
        this.genre = genre;
        this.cost = cost;
    }

    public RecyclerViewItem(String artist, int timeLine, String origin) {
        this.artist = artist;
        this.timeLine = timeLine;
        this.origin = origin;
    }


    public String getArtist() {
        return artist;
    }

    public int getTimeLine() {
        return timeLine;
    }

    public String getOrigin() {
        return origin;
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
