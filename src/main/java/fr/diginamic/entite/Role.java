package fr.diginamic.entite;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * @see Role
 * Classe Role permettant de définir un rôle
 */
@Entity
@Table(name = "role")
public class Role {

    /**
     * Identifiant du rôle
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nom du rôle
     */
    private String characterName;

    /**
     * Film auquel appartient le rôle
     */
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    /*
    * Acteur auquel appartient le rôle
     */
    @ManyToOne
    @JoinColumn(name = "acteur_id")
    private Acteur acteur;


    /**
     * Constructeur vide de la classe Role
     */
    public Role() {
    }

    /**
     * Constructeur par défaut de la classe Role
     * @param characterName Nom du rôle
     * @param film Film auquel appartient le rôle
     * @param acteur Acteur auquel appartient le rôle
     */
    public Role(String characterName, Film film, Acteur acteur) {
        this.characterName = characterName;
        this.film = film;
        this.acteur = acteur;
    }

    /**
     * Getter de l'identifiant du rôle
     * @return Identifiant du rôle
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter de l'identifiant du rôle
     * @param id Identifiant du rôle
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter du nom du rôle
     * @return Nom du rôle
     */
    @JsonProperty("characterName")
    public String getCharacterName() {
        return this.characterName;
    }

    /**
     * Setter du nom du rôle
     * @param characterName Nom du rôle
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    /**
     * Getter du film auquel appartient le rôle
     * @return Film auquel appartient le rôle
     */
    @JsonProperty("film")
    public Film getFilm() {
        return this.film;
    }

    /**
     * Setter du film auquel appartient le rôle
     * @param film Film auquel appartient le rôle
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     * Getter de l'acteur auquel appartient le rôle
     * @return Acteur auquel appartient le rôle
     */
    public Acteur getActeur() {
        return acteur;
    }

    /**
     * Setter de l'acteur auquel appartient le rôle
     * @param acteur Acteur auquel appartient le rôle
     */
    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }

    /**
     * Méthode toString de la classe Role
     * @return Retourne une chaîne de caractères contenant les informations du rôle
     */
    @Override
    public String toString() {
        return "Role{" + "characterName='" + characterName + '\'' + ", film=" + film + '}';
    }
}
