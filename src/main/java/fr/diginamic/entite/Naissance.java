package fr.diginamic.entite;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Embeddable
public class Naissance {
    private String dateNaissance;
    private String lieuNaissance;

    @JsonProperty("dateNaissance")
    public String getDateNaissance() {
        return this.dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @JsonProperty("lieuNaissance")
    public String getLieuNaissance() {
        return this.lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    @Override
    public String toString() {
        return "Naissance{" +
                "dateNaissance='" + dateNaissance + '\'' +
                ", lieuNaissance='" + lieuNaissance + '\'' +
                '}';
    }
}
