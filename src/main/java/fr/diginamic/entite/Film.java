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

    private String nom;
    private String url;
    private String idOmdb;
    private String langue;
    private String plot;
    private String anneeSortie;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lieu_tournage_id")
    private LieuTournage lieuTournage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pays_id")
    private Pays pays;

    @ManyToMany
    @JoinTable(name = "film_realisateur",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "realisateur_id", referencedColumnName = "id"))
    private Set<Realisateur> realisateurs = new HashSet<>();

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "film_genre", joinColumns = @JoinColumn(name = "film_id"))
    private List<String> genres = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "film_acteur", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "acteur_id", referencedColumnName = "id"))
    private Set<Acteur> acteurs = new HashSet<>();

    @OneToMany(mappedBy = "film")
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "casting_principal",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "acteur_id", referencedColumnName = "id"))
    private Set<Acteur> castingPrincipal = new HashSet<>();

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

    public Film() {
    }

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @JsonProperty("castingPrincipal")
    public Set<Acteur> getCastingPrincipal() {
        return castingPrincipal;
    }

    public void setCastingPrincipal(Set<Acteur> castingPrincipal) {
        this.castingPrincipal = castingPrincipal;
    }

    @Override
    public String toString() {
        return "Film{" +
                " id = " + id +
                ", id_imdb = '" + idOmdb + '\'' +
                ", titre = '" + nom + '\'' +
                ", année de sortie = '" + anneeSortie + '\'' +
                ", pays d'origine = " + pays +
                ", lieu de tournage = " + lieuTournage +
                ", genres = " + genres +
                ", description = '" + plot + '\'' +
                ", langue = '" + pays + '\'' +
                ", realisateurs = " + realisateurs +
                ", acteurs = " + acteurs +
                '}';
    }

    public void addCastingPrincipal(Acteur acteurCasting) {
        this.castingPrincipal.add(acteurCasting);
    }

    public void addRealisateur(Realisateur realisateur) {this.realisateurs.add(realisateur);}

    public void addActeurFilm(Acteur acteurFilm){
        this.acteurs.add(acteurFilm);
    }
}
