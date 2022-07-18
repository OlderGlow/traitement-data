package fr.diginamic.entite;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * Classe LieuTournage permettant de définir un lieu de tournage
 */
@Entity
@Table(name = "lieu_tournage")
public class LieuTournage {

    /**
     * Identifiant du lieu de tournage
     * @see LieuTournage#getId()
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Ville du lieu de tournage
     * @see LieuTournage#getVille()
     */
    private String ville;

    /**
     * État ou province du lieu de tournage
     * @see LieuTournage#getEtatDept()
     */
    private String etatDept;

    /**
     * Pays du lieu de tournage
     * @see LieuTournage#getPays()
     */
    private String pays;

    /**
     * Liste des films où le lieu de tournage est utilisé
     * @see LieuTournage#getFilms()
     */
    @OneToMany(mappedBy = "lieuTournage")
    private Set<Film> films = new HashSet<>();

    /**
     * Constructeur par défaut prenant en paramètre une ville, un état ou une province, un pays et une liste de films
     * @param id Identifiant du lieu de tournage
     * @param ville Ville du lieu de tournage
     * @param etatDept État ou province du lieu de tournage
     * @param pays Pays du lieu de tournage
     * @param films Liste des films où le lieu de tournage est utilisé
     */
    public LieuTournage(Long id, String ville, String etatDept, String pays, Set<Film> films) {
        this.id = id;
        this.ville = ville;
        this.etatDept = etatDept;
        this.pays = pays;
        this.films = films;
    }

    /**
     * Constructeur vide
     */
    public LieuTournage() {
    }

    /**
     * Getter de l'identifiant du lieu de tournage
     * @return Identifiant du lieu de tournage
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter de l'identifiant du lieu de tournage
     * @param id Identifiant du lieu de tournage
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter de la ville du lieu de tournage
     * @return Ville du lieu de tournage
     */
    @JsonProperty("ville")
    public String getVille() {
        return this.ville;
    }

    /**
     * Setter de la ville du lieu de tournage
     * @param ville Ville du lieu de tournage
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Getter de l'état ou province du lieu de tournage
     * @return État ou province du lieu de tournage
     */
    @JsonProperty("etatDept")
    public String getEtatDept() {
        return this.etatDept;
    }

    /**
     * Setter de l'état ou province du lieu de tournage
     * @param etatDept État ou province du lieu de tournage
     */
    public void setEtatDept(String etatDept) {
        this.etatDept = etatDept;
    }

    /**
     * Getter du pays du lieu de tournage
     * @return Pays du lieu de tournage
     */
    @JsonProperty("pays")
    public String getPays() {
        return this.pays;
    }

    /**
     * Setter du pays du lieu de tournage
     * @param pays Pays du lieu de tournage
     */
    public void setPays(String pays) {
        this.pays = pays;
    }

    /**
     * Getter de la liste des films où le lieu de tournage est utilisé
     * @return Liste des films où le lieu de tournage est utilisé
     */
    public Set<Film> getFilms() {
        return films;
    }

    /**
     * Setter de la liste des films où le lieu de tournage est utilisé
     * @param films Liste des films où le lieu de tournage est utilisé
     */
    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    /**
     * Méthode toString
     * @return String contenant les informations du lieu de tournage
     */
    @Override
    public String toString() {
        return "LieuTournage{" +
                "ville='" + ville + '\'' +
                ", etatDept='" + etatDept + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
}
