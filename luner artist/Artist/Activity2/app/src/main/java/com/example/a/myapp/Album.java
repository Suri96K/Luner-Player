package com.example.a.myapp;

public class Album {

    private String albumName;
    private String artistName;
    private int albumCoverDrawableId;

    public Album(String albumName, String artistName, int albumCoverDrawableId) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.albumCoverDrawableId = albumCoverDrawableId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }


    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getAlbumCoverDrawableId() {
        return albumCoverDrawableId;
    }

    public void setAlbumCoverDrawableId(int albumCoverDrawableId) {
        this.albumCoverDrawableId = albumCoverDrawableId;
    }
}
