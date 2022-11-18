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
    private String discJockey;
    @Column
    private String genre;

    public MusicSet(){}

    public MusicSet(String title, String discJockey, String genre){
        this.title = title;
        this.discJockey = discJockey;
        this.genre = genre;
    }

    public String getTitle() { return title; }
    public String getDiscJockey() {return discJockey; }
    public String getGenre() {return genre; }
    public void setTitle(String title) {this.title = title;}
    public void setDiscJockey(String discJockey) {this.title = discJockey;}
}
