package nl.capgemini.festival.model;

import javax.persistence.*;

@Entity
@Table(name = "musicsets")
public class MusicSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String artist;
    @Column
    private String genre;

    public MusicSet(){}

    public MusicSet(String title, String artist, String genre){
        this.title = title;
        this.artist = artist;
        this.genre = genre;
    }

    public String getTitle() { return title; }
}
