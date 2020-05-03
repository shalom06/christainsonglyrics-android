package com.thorandzeus.shalom.lyrics.models;

public class ListItem {

    private String songName;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public ListItem(String songName) {
        this.songName = songName;
    }

}
