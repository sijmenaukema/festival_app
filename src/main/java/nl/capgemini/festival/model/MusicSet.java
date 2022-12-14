package nl.capgemini.festival.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "music_set")
public class MusicSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull(message = "title is mandatory")
    private String title;

    @Column
    private String genre;

    @NotNull
    @ManyToOne
    @JoinColumn(name="disc_jockey_id")
    DiscJockey discJockey;

    public MusicSet(){}

    public MusicSet(String title, DiscJockey discJockey, String genre){
        this.title = title;
        this.discJockey = discJockey;
        this.genre = genre;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() {return genre; }
    public void setTitle(String title) { this.title = title; }
    public void setGenre(String genre) { this.genre = genre; }
    public DiscJockey getDiscJockey(){ return discJockey; }
}
