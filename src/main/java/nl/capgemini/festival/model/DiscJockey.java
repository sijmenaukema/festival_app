package nl.capgemini.festival.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "disc_jockey")
public class DiscJockey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name is mandatory")
    @NotEmpty(message = "Name is mandatory")
    @Column
    private String name;

    @Column
    private String genre;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "discJockey")
    private Set<MusicSet> musicSet;

    public DiscJockey(){}

    public DiscJockey(String name){
        this.name = name;
    }

    public Long getId() {return id; }
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }
}
