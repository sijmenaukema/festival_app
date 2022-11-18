package nl.capgemini.festival.model;

import javax.persistence.*;

@Entity
@Table(name = "discjockeys")
public class DiscJockey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String genre;

    public DiscJockey(){}

    public DiscJockey(String name, String genre){
        this.name = name;
        this.genre = genre;
    }

    public Long getId() {return id; }
    public String getName() { return name; }
    public String getGenre() {return genre; }
    public void setName(String name) {
        this.name = name;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
