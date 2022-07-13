package fr.diginamic.entite;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String characterName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<Acteur> acteurs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("characterName")
    public String getCharacterName() {
        return this.characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    @JsonProperty("film")
    public Film getFilm() {
        return this.film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Set<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(Set<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    @Override
    public String toString() {
        return "Role{" +
                "characterName='" + characterName + '\'' +
                ", film=" + film +
                '}';
    }
}
