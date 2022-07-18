package fr.diginamic.entite;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * Classe Film permettant de créer un nouveau film
 */
@Entity
@Table(name = "film")
public class Film {

    /**
     * Identifiant du film
     *
     * @see #getId()
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Titre du film
     *
     * @see #getNom()
     */
    private String nom;

    /**
     * URL du film (Imdb)
     *
     * @see #getUrl()
     */
    private String url;

    /**
     * l'ID du film dans l'API Imdb
     *
     * @see #getIdOmdb()
     */
    private String idOmdb;

    /**
     * Langue du film
     *
     * @see #getLangue()
     */
    private String langue;

    /**
     * Description du film
     *
     * @see #getPlot()
     */
    private String plot;

    /**
     * Année de sortie du film
     *
     * @see #getAnneeSortie()
     */
    private String anneeSortie;

    /**
     * Lieu de tournage du film
     *
     * @see #getLieuTournage() ()
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lieu_tournage_id")
    private LieuTournage lieuTournage;

    /**
     * Pays de production du film
     *
     * @see #getPays()
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pays_id")
    private Pays pays;

    /**
     * Liste des réalisateurs du film
     *
     * @see #getRealisateurs()
     */
    @ManyToMany
    @JoinTable(name = "film_realisateur", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "realisateur_id", referencedColumnName = "id"))
    private Set<Realisateur> realisateurs = new HashSet<>();

    /**
     * Liste des genres du film
     *
     * @see #getGenres()
     */
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "film_genre", joinColumns = @JoinColumn(name = "film_id"))
    private List<String> genres = new ArrayList<>();

    /**
     * Liste des acteurs du film
     *
     * @see #getActeurs()
     */
    @ManyToMany
    @JoinTable(name = "film_acteur", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "acteur_id", referencedColumnName = "id"))
    private Set<Acteur> acteurs = new HashSet<>();

    /**
     * Liste des roles du film
     *
     * @see #getRoles()
     */
    @OneToMany(mappedBy = "film")
    private Set<Role> roles = new HashSet<>();

    /**
     * Liste des acteurs principaux du film
     *
     * @see #getCastingPrincipal()
     */
    @ManyToMany
    @JoinTable(name = "casting_principal", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "acteur_id", referencedColumnName = "id"))
    private Set<Acteur> castingPrincipal = new HashSet<>();

    /**
     * Constructeur prenant tous les paramètres
     *
     * @param id               identifiant du film
     * @param nom              titre du film
     * @param url              url du film (Imdb)
     * @param idOmdb           l'ID du film dans l'API Imdb
     * @param langue           langue du film
     * @param plot             description du film
     * @param anneeSortie      année de sortie du film
     * @param lieuTournage     lieu de tournage du film
     * @param pays             pays de production du film
     * @param realisateurs     liste des réalisateurs du film
     * @param genres           liste des genres du film
     * @param acteurs          liste des acteurs du film
     * @param roles            liste des roles du film
     * @param castingPrincipal liste des acteurs principaux du film
     */
    public Film(Long id, String nom, String url, String idOmdb, String langue, String plot, String anneeSortie, LieuTournage lieuTournage, Pays pays, Set<Realisateur> realisateurs, List<String> genres, Set<Acteur> acteurs, Set<Role> roles, Set<Acteur> castingPrincipal) {
        this.id = id;
        this.nom = nom;
        this.url = url;
        this.idOmdb = idOmdb;
        this.langue = langue;
        this.plot = plot;
        this.anneeSortie = anneeSortie;
        this.lieuTournage = lieuTournage;
        this.pays = pays;
        this.realisateurs = realisateurs;
        this.genres = genres;
        this.acteurs = acteurs;
        this.roles = roles;
        this.castingPrincipal = castingPrincipal;
    }

    /**
     * Constructeur vide
     */
    public Film() {
    }

    /**
     * Getter de l'identifiant du film
     *
     * @return identifiant du film
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter de l'identifiant du film
     *
     * @param id identifiant du film
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter de la description du film
     *
     * @return description du film
     */
    @JsonProperty("plot")
    public String getPlot() {
        return plot;
    }

    /**
     * Setter de la description du film
     *
     * @param plot description du film
     */
    public void setPlot(String plot) {
        this.plot = plot;
    }


    /**
     * Getter du pays de production du film
     *
     * @return pays de production du film
     */
    @JsonProperty("pays")
    public Pays getPays() {
        return this.pays;
    }

    /**
     * Setter du pays de production du film
     *
     * @param pays pays de production du film
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }

    /**
     * Getter du titre du film
     *
     * @return titre du film
     */
    @JsonProperty("nom")
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter du titre du film
     *
     * @param nom titre du film
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter de l'URL du film
     *
     * @return URL du film
     */
    @JsonProperty("url")
    public String getUrl() {
        return this.url;
    }

    /**
     * Setter de l'URL du film
     *
     * @param url URL du film
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter de l'ID du film dans l'API Imdb
     *
     * @return ID du film dans l'API Imdb
     */
    @JsonProperty("id")
    public String getIdOmdb() {
        return this.idOmdb;
    }

    /**
     * Setter de l'ID du film dans l'API Imdb
     *
     * @param id ID du film dans l'API Imdb
     */
    public void setIdOmdb(String id) {
        this.idOmdb = id;
    }

    /**
     * Getter de la langue du film
     *
     * @return langue du film
     */
    @JsonProperty("langue")
    public String getLangue() {
        return this.langue;
    }

    /**
     * Setter de la langue du film
     *
     * @param langue langue du film
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    /**
     * Getter du lieu de tournage du film
     *
     * @return lieu de tournage du film
     */
    @JsonProperty("lieuTournage")
    public LieuTournage getLieuTournage() {
        return this.lieuTournage;
    }

    /**
     * Setter du lieu de tournage du film
     *
     * @param lieuTournage lieu de tournage du film
     */
    public void setLieuTournage(LieuTournage lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    /**
     * Getter de la liste des réalisateurs du film
     *
     * @return liste des réalisateurs du film
     */
    @JsonProperty("realisateurs")
    public Set<Realisateur> getRealisateurs() {
        return this.realisateurs;
    }

    /**
     * Setter de la liste des réalisateurs du film
     *
     * @param realisateurs liste des réalisateurs du film
     */
    public void setRealisateurs(Set<Realisateur> realisateurs) {
        this.realisateurs = realisateurs;
    }

    /**
     * Getter de l'année de sortie du film
     *
     * @return année de sortie du film
     */
    @JsonProperty("anneeSortie")
    public String getAnneeSortie() {
        return this.anneeSortie;
    }

    /**
     * Setter de l'année de sortie du film
     *
     * @param anneeSortie année de sortie du film
     */
    public void setAnneeSortie(String anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    /**
     * Getter de la liste des genres du film
     *
     * @return liste des genres du film
     */
    @JsonProperty("genres")
    public List<String> getGenres() {
        return this.genres;
    }

    /**
     * Setter de la liste des genres du film
     *
     * @param genres liste des genres du film
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * Getter de la liste des acteurs du film
     *
     * @return liste des acteurs du film
     */
    @JsonProperty("acteurs")
    public Set<Acteur> getActeurs() {
        return this.acteurs;
    }

    /**
     * Setter de la liste des acteurs du film
     *
     * @param acteurs liste des acteurs du film
     */
    public void setActeurs(Set<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    /**
     * Getter de la liste des rôles du film
     *
     * @return liste des rôles du film
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Setter de la liste des rôles du film
     *
     * @param roles liste des rôles du film
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Getter de la liste du casting principal du film
     *
     * @return liste du casting principal du film
     */
    @JsonProperty("castingPrincipal")
    public Set<Acteur> getCastingPrincipal() {
        return castingPrincipal;
    }

    /**
     * Setter de la liste du casting principal du film
     *
     * @param castingPrincipal liste du casting principal du film
     */
    public void setCastingPrincipal(Set<Acteur> castingPrincipal) {
        this.castingPrincipal = castingPrincipal;
    }

    /**
     * ToString du film
     *
     * @return String du film
     */
    @Override
    public String toString() {
        return "Film{" + " id = " + id + ", id_imdb = '" + idOmdb + '\'' + ", titre = '" + nom + '\'' + ", année de sortie = '" + anneeSortie + '\'' + ", pays d'origine = " + pays + ", lieu de tournage = " + lieuTournage + ", genres = " + genres + ", description = '" + plot + '\'' + ", langue = '" + pays + '\'' + ", realisateurs = " + realisateurs + ", acteurs = " + acteurs + '}';
    }

    /**
     * Méthode permettant d'ajouter un acteur à la liste des acteurs principaux du film
     *
     * @param acteurCasting acteur à ajouter
     */
    public void addCastingPrincipal(Acteur acteurCasting) {
        this.castingPrincipal.add(acteurCasting);
    }

    /**
     * Méthode permettant d'ajouter un réalisateur à la liste des réalisateurs du film
     *
     * @param realisateur réalisateur à ajouter
     */
    public void addRealisateur(Realisateur realisateur) {
        this.realisateurs.add(realisateur);
    }

    /**
     * Méthode permettant d'ajouter un acteur à la liste des acteurs du film
     *
     * @param acteurFilm acteur à ajouter
     */
    public void addActeurFilm(Acteur acteurFilm) {
        this.acteurs.add(acteurFilm);
    }
}
