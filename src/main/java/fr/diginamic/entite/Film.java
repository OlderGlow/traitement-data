package fr.diginamic.entite;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pays_id")
    private Pays pays;

    private String nom;
    private String url;
    private String idOmdb;
    private String langue;
    private String plot;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "lieu_tournage_id")
    private LieuTournage lieuTournage;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "film_realisateur", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "realisateur_id"))
    private Set<Realisateur> realisateurs;
    private String anneeSortie;
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "film_genre", joinColumns = @JoinColumn(name = "film_id"))
    private List<String> genres;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "film_acteur", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "acteur_id"))
    private Set<Acteur> acteurs;

    @JsonProperty("plot")
    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("pays")
    public Pays getPays() {
        return this.pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    @JsonProperty("nom")
    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonProperty("url")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("id")
    public String getIdOmdb() {
        return this.idOmdb;
    }

    public void setIdOmdb(String id) {
        this.idOmdb = id;
    }

    @JsonProperty("langue")
    public String getLangue() {
        return this.langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    @JsonProperty("lieuTournage")
    public LieuTournage getLieuTournage() {
        return this.lieuTournage;
    }

    public void setLieuTournage(LieuTournage lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    @JsonProperty("realisateurs")
    public Set<Realisateur> getRealisateurs() {
        return this.realisateurs;
    }

    public void setRealisateurs(Set<Realisateur> realisateurs) {
        this.realisateurs = realisateurs;
    }

    @JsonProperty("anneeSortie")
    public String getAnneeSortie() {
        return this.anneeSortie;
    }

    public void setAnneeSortie(String anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    @JsonProperty("genres")
    public List<String> getGenres() {
        return this.genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @JsonProperty("acteurs")
    public Set<Acteur> getActeurs() {
        return this.acteurs;
    }

    public void setActeurs(Set<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    @Override
    public String toString() {
        return "Film{" + "pays=" + pays + ", nom='" + nom + '\'' + ", url='" + url + '\'' + ", id='" + idOmdb + '\'' + ", langue='" + langue + '\'' + ", lieuTournage=" + lieuTournage + ", realisateurs=" + realisateurs + ", anneeSortie='" + anneeSortie + '\'' + ", genres=" + genres + ", acteurs=" + acteurs + '}';
    }
}
