package fr.diginamic.entite;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * @see Realisateur
 * Classe Réalisateur permettant de définir un réalisateur
 */
@Entity
@Table(name = "realisateur")
public class Realisateur {

    /**
     * Identifiant du réalisateur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nom du réalisateur
     */
    private String identite;

    /**
     * URL de l'API Imdb
     */
    private String url;

    /**
     * Liste des films réalisés par le réalisateur
     */
    @JsonProperty("realisateurs")
    @ManyToMany(mappedBy = "realisateurs")
    private Set<Film> films = new LinkedHashSet<>();

    /**
     * Constructeur par défaut de la classe Réalisateur
     *
     * @param id       Identifiant du réalisateur
     * @param identite Nom du réalisateur
     * @param url      URL de l'API Imdb
     * @param films    Liste des films réalisés par le réalisateur
     */
    public Realisateur(Long id, String identite, String url, Set<Film> films) {
        this.id = id;
        this.identite = identite;
        this.url = url;
        this.films = films;
    }

    /**
     * Constructeur vide de la classe Réalisateur
     */
    public Realisateur() {
    }

    /**
     * Getter de l'identifiant du réalisateur
     *
     * @return Identifiant du réalisateur
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter de l'identifiant du réalisateur
     *
     * @param id Identifiant du réalisateur
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter de la liste des films réalisés par le réalisateur
     *
     * @return Liste des films réalisés par le réalisateur
     */
    public Set<Film> getFilms() {
        return films;
    }

    /**
     * Setter de la liste des films réalisés par le réalisateur
     *
     * @param films Liste des films réalisés par le réalisateur
     */
    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    /**
     * Getter du nom du réalisateur
     *
     * @return Nom du réalisateur
     */
    @JsonProperty("identite")
    public String getIdentite() {
        return this.identite;
    }

    /**
     * Setter du nom du réalisateur
     *
     * @param identite Nom du réalisateur
     */
    public void setIdentite(String identite) {
        this.identite = identite;
    }

    /**
     * Getter de l'URL de l'API Imdb
     *
     * @return URL de l'API Imdb
     */
    @JsonProperty("url")
    public String getUrl() {
        return this.url;
    }

    /**
     * Setter de l'URL de l'API Imdb
     *
     * @param url URL de l'API Imdb
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Méthode toString de la classe Réalisateur
     *
     * @return String contenant les informations du réalisateur
     */
    @Override
    public String toString() {
        return "Realisateur{" + "id=" + id + ", identite='" + identite + '\'' + ", url='" + url + '\'' + ", films=" + films + '}';
    }

    /*
     * Méthode permettant d'ajouter un film à la liste des films réalisés par le réalisateur
     * @param film Film à ajouter à la liste des films réalisés par le réalisateur
     */
    public void addFilm(Film film) {
        this.films.add(film);
    }
}
