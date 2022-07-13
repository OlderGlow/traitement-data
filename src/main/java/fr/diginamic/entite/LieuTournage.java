package fr.diginamic.entite;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
@Table(name = "lieu_tournage")
public class LieuTournage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String ville;
    private String etatDept;
    private String pays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("ville")
    public String getVille() {
        return this.ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @JsonProperty("etatDept")
    public String getEtatDept() {
        return this.etatDept;
    }

    public void setEtatDept(String etatDept) {
        this.etatDept = etatDept;
    }

    @JsonProperty("pays")
    public String getPays() {
        return this.pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "LieuTournage{" +
                "ville='" + ville + '\'' +
                ", etatDept='" + etatDept + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
}
