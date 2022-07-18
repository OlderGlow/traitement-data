package fr.diginamic.entite;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "acteur")
public class Acteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String identite;

    @Embedded
    private Naissance naissance;

    private String url;
    private String idOmdb;

    @OneToMany(mappedBy = "acteur")
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "castingPrincipal")
    private Set<Film> castingPrincipal = new HashSet<>();

    @ManyToMany(mappedBy = "acteurs")
    private Set<Film> films = new HashSet<>();


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

    public Acteur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("identite")
    public String getIdentite() {
        return this.identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    @JsonProperty("naissance")
    public Naissance getNaissance() {
        return this.naissance;
    }

    public void setNaissance(Naissance naissance) {
        this.naissance = naissance;
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

    @JsonProperty("roles")
    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Film> getCastingPrincipal() {
        return castingPrincipal;
    }

    public void setCastingPrincipal(Set<Film> castingPrincipal) {
        this.castingPrincipal = castingPrincipal;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

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

    public void addCastingPrincipal(Film film) {
        this.castingPrincipal.add(film);
    }

    public void addFilm(Film film) {
        this.films.add(film);
    }
}
