package fr.diginamic.entite;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "realisateur")
public class Realisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String identite;
    private String url;

    @ManyToMany(mappedBy = "realisateurs")
    private Set<Film> films;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @JsonProperty("identite")
    public String getIdentite() {
        return this.identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    @JsonProperty("url")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
