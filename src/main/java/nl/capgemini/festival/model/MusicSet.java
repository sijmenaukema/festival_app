package nl.capgemini.festival.model;

import javax.persistence.*;

@Entity
@Table(name = "music_set")
public class MusicSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String genre;

    @ManyToOne(cascade=CascadeType.ALL)
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
    public DiscJockey getDiscJockey() {return discJockey; }
    public String getGenre() {return genre; }
    public void setTitle(String title) {this.title = title;}
}
