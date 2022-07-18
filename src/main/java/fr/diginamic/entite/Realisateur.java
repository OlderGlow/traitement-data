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

    @JsonProperty("realisateurs")
    @ManyToMany(mappedBy = "realisateurs")
    private Set<Film> films = new LinkedHashSet<>();

    public Realisateur(Long id, String identite, String url, Set<Film> films) {
        this.id = id;
        this.identite = identite;
        this.url = url;
        this.films = films;
    }

    public Realisateur() {
    }

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

    public void addFilm(Film film) {
        this.films.add(film);
    }
}
