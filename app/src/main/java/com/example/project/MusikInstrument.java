package com.example.project;

import com.google.gson.annotations.SerializedName;

public class MusikInstrument {
    @SerializedName("ID")
    private String id;
    @SerializedName("type")
    private String login;
    private String name;
    @SerializedName("category")
    private String genre;
    private int cost;
    @SerializedName("company")
    private String artist;
    @SerializedName("size")
    private int timeLine;
    @SerializedName("location")
    private String origin;

    //Standard konstruktor
    public MusikInstrument () {
        id="Saknar ID";
        login="Login saknas";
        name="Namn saknas";
        genre="Saknar genre";
        cost=1;
    }

    public MusikInstrument(String id, String login, String name, String genre, int cost) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.genre = genre;
        this.cost = cost;
    }

    public MusikInstrument(String name, String genre, int cost) {
        this.name = name;
        this.genre = genre;
        this.cost = cost;
    }

    public MusikInstrument(String artist, int timeLine, String origin) {
        this.artist = artist;
        this.timeLine = timeLine;
        this.origin = origin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(int timeLine) {
        this.timeLine = timeLine;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Login: " + login +
                ", Name: " + name +
                ", Genre: " + genre +
                ", Cost: " + cost + "Kr";
    }
}
