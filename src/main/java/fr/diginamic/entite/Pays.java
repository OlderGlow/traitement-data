package fr.diginamic.entite;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * Classe Pays : classe représentant un pays
 */
@Entity
@Table(name = "pays")
public class Pays {

    /**
     * Attribut id : identifiant du pays
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Attribut nom : nom du pays
     */
    private String nom;

    /**
     * Attribut URL : URL du pays de l'API ImDB
     */
    private String url;

    /**
     * Attribut film : liste des films où le pays est présent
     */
    @OneToMany(mappedBy = "pays")
    private Set<Film> films = new HashSet<>();

    /**
     * Constructeur principal de la classe Pays
     * @param id identifiant du pays
     * @param nom nom du pays
     * @param url URL du pays de l'API ImDB
     * @param films liste des films où le pays est présent
     */
    public Pays(Long id, String nom, String url, Set<Film> films) {
        this.id = id;
        this.nom = nom;
        this.url = url;
        this.films = films;
    }

    /**
     * Constructeur vide de la classe Pays
     */
    public Pays() {
    }

    /**
     * Getter de l'attribut id
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter de l'attribut id
     * @param id identifiant du pays
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter de l'attribut nom
     * @return nom
     */
    @JsonProperty("nom")
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter de l'attribut nom
     * @param nom nom du pays
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter de l'attribut url
     * @return url
     */
    @JsonProperty("url")
    public String getUrl() {
        return this.url;
    }

    /**
     * Setter de l'attribut url
     * @param url URL du pays de l'API ImDB
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter de l'attribut films
     * @return films
     */
    public Set<Film> getFilms() {
        return films;
    }

    /**
     * Setter de l'attribut films
     * @param films liste des films où le pays est présent
     */
    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    /**
     * Méthode toString de la classe Pays
     * @return une chaîne de caractères représentant le pays
     */
    @Override
    public String toString() {
        return "Pays{" + "id=" + id + ", nom='" + nom + '\'' + ", url='" + url + '\'' + "}";
    }
}
