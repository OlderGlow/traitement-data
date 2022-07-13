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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "acteur_role", joinColumns = @JoinColumn(name = "acteur_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

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
}
