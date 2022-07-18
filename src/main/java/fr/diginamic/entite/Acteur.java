package fr.diginamic.entite;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * Classe Acteur permettant de définir un acteur
 */
@Entity
@Table(name = "acteur")
public class Acteur {


    /**
     * Identifiant de l'acteur
     * @see #getId()
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nom de l'acteur
     * @see #getIdentite()
     */
    private String identite;

    /**
     * Date et lieu de naissance de l'acteur
     * @see #getNaissance()
     */
    @Embedded
    private Naissance naissance;

    /**
     * URL de l'acteur (Imdb)
     * @see #getUrl()
     */
    private String url;

    /**
     * l'ID de l'acteur dans l'API Imdb
     * @see #getIdOmdb()
     */
    private String idOmdb;

    /**
     * Liste des films de l'acteur
     * @see #getFilms()
     */
    @OneToMany(mappedBy = "acteur")
    private Set<Role> roles = new HashSet<>();

    /**
     * Liste des acteurs principaux d'un film
     * @see #getCastingPrincipal()
     */
    @ManyToMany(mappedBy = "castingPrincipal")
    private Set<Film> castingPrincipal = new HashSet<>();

    /**
     * Liste des films de l'acteur
     * @see #getFilms()
     */
    @ManyToMany(mappedBy = "acteurs")
    private Set<Film> films = new HashSet<>();


    /**
     * Constructeur prenant toutes les valeurs en paramètre
     * @param id identifiant de l'acteur
     * @param identite nom de l'acteur
     * @param naissance date et lieu de naissance de l'acteur
     * @param url URL de l'acteur (Imdb)
     * @param idOmdb l'ID de l'acteur dans l'API Imdb
     * @param roles liste des roles de l'acteur
     * @param castingPrincipal liste des acteurs principaux d'un film
     * @param films liste des films de l'acteur
     */
    public Acteur(Long id, String identite, Naissance naissance, String url, String idOmdb, Set<Role> roles, Set<Film> castingPrincipal, Set<Film> films) {
        this.id = id;
        this.identite = identite;
        this.naissance = naissance;
        this.url = url;
        this.idOmdb = idOmdb;
        this.roles = roles;
        this.castingPrincipal = castingPrincipal;
        this.films = films;
    }

    /**
     * Constructeur vide
     */
    public Acteur() {
    }

    /**
     * Getter de l'identifiant de l'acteur
     * @return identifiant de l'acteur
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter de l'identifiant de l'acteur
     * @param id identifiant de l'acteur
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter du nom de l'acteur
     * @return nom de l'acteur
     */
    @JsonProperty("identite")
    public String getIdentite() {
        return this.identite;
    }

    /**
     * Setter du nom de l'acteur
     * @param identite nom de l'acteur
     */
    public void setIdentite(String identite) {
        this.identite = identite;
    }

    /**
     * Getter de la date et lieu de naissance de l'acteur
     * @return date et lieu de naissance de l'acteur
     */
    @JsonProperty("naissance")
    public Naissance getNaissance() {
        return this.naissance;
    }

    /**
     * Setter de la date et lieu de naissance de l'acteur
     * @param naissance date et lieu de naissance de l'acteur
     */
    public void setNaissance(Naissance naissance) {
        this.naissance = naissance;
    }

    /**
     * Getter de l'URL de l'acteur (Imdb)
     * @return URL de l'acteur (Imdb)
     */
    @JsonProperty("url")
    public String getUrl() {
        return this.url;
    }

    /**
     * Setter de l'URL de l'acteur (Imdb)
     * @param url URL de l'acteur (Imdb)
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter de l'ID de l'acteur dans l'API Imdb
     * @return ID de l'acteur dans l'API Imdb
     */
    @JsonProperty("id")
    public String getIdOmdb() {
        return this.idOmdb;
    }

    /**
     * Setter de l'ID de l'acteur dans l'API Imdb
     * @param id ID de l'acteur dans l'API Imdb
     */
    public void setIdOmdb(String id) {
        this.idOmdb = id;
    }

    /**
     * Getter de la liste des roles de l'acteur
     * @return liste des roles de l'acteur
     */
    @JsonProperty("roles")
    public Set<Role> getRoles() {
        return this.roles;
    }

    /**
     * Setter de la liste des roles de l'acteur
     * @param roles liste des roles de l'acteur
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Getter de la liste des acteurs principaux d'un film
     * @return liste des acteurs principaux d'un film
     */
    public Set<Film> getCastingPrincipal() {
        return castingPrincipal;
    }

    /**
     * Setter de la liste des acteurs principaux d'un film
     * @param castingPrincipal liste des acteurs principaux d'un film
     */
    public void setCastingPrincipal(Set<Film> castingPrincipal) {
        this.castingPrincipal = castingPrincipal;
    }

    /**
     * Getter de la liste des films de l'acteur
     * @return liste des films de l'acteur
     */
    public Set<Film> getFilms() {
        return films;
    }

    /**
     * Setter de la liste des films de l'acteur
     * @param films liste des films de l'acteur
     */
    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    /**
     * Méthode toString de l'acteur
     * @return nom, date et lieu de naissance de l'acteur ainsi que l'URL de l'acteur (Imdb), l'ID de l'acteur dans l'API Imdb et la liste des roles de l'acteur
     */
    @Override
    public String toString() {
        return "Acteur{" +
                "identite='" + identite + '\'' +
                ", naissance=" + naissance +
                ", url='" + url + '\'' +
                ", id='" + idOmdb + '\'' +
                ", roles=" + roles +
                '}';
    }

    /**
     * Méthode permettant d'ajouter un film à la liste de la distribution principale d'un acteur
     */
    public void addCastingPrincipal(Film film) {
        this.castingPrincipal.add(film);
    }

    /**
     * Méthode permettant d'ajouter un film à la liste des films de l'acteur
     */
    public void addFilm(Film film) {
        this.films.add(film);
    }
}
