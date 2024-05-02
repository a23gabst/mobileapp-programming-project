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
}
