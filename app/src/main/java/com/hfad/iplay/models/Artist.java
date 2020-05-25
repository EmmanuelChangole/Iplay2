package com.hfad.iplay.models;

public class Artist
{
    int Song;
    int artist;

    public Artist(int song, int artist) {
        Song = song;
        this.artist = artist;
    }

    public int getSong() {
        return Song;
    }

    public void setSong(int song) {
        Song = song;
    }

    public int getArtist() {
        return artist;
    }

    public void setArtist(int artist) {
        this.artist = artist;
    }
}
