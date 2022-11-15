package com.capgemini.festival.model;

import javax.persistence.*;

@Entity
@Table(name = "discjockeys")
public class DiscJockey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String genre;

    public DiscJockey(){}

    public DiscJockey(String name, String genre){
        this.name = name;
        this.genre = genre;
    }

    public String getName() { return name; }

}
