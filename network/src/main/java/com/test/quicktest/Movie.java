package com.test.quicktest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by asd on 12.7.2017.
 */

public class Movie implements Serializable{
    @SerializedName("Id")
    @Expose
    int id;

    @SerializedName("Name")
    @Expose
    String name;

    @SerializedName("Artist")
    @Expose
    String artist;

    @SerializedName("ImageUrl")
    @Expose
    String imageUrl;

    @SerializedName("Block")
    @Expose
    boolean isBlocked;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isBlocked() {
        return isBlocked;
    }
}
