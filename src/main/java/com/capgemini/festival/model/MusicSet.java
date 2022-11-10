package com.application.festival.model;

public class MusicSet {
    private Long id;

    private String title;

    private String genre;

    public MusicSet(){}

    public MusicSet(String title, String genre){
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {return title;}
}
