package com.application.festival.model;

public class DiscJockey {
    private Long id;
    private String name;
    private String genre;

    public DiscJockey(){}

    public DiscJockey(String name, String genre){
        this.name = name;
        this.genre = genre;
    }

    public String getName() { return name;}

}
