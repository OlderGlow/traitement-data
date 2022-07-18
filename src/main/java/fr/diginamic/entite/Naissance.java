package fr.diginamic.entite;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * Classe Naissance : classe représentant une date et un lieu de naissance
 * Cette classe est annotée avec l'annotation @Embeddable pour qu'elle soit mappée dans la base de données comme une entité embarquée
 */
@Embeddable
public class Naissance {

    /**
     * Attribut dateNaissance : date de naissance
     */
    private String dateNaissance;

    /**
     * Attribut lieuNaissance : lieu de naissance
     */
    private String lieuNaissance;

    /**
     * Getter de l'attribut dateNaissance
     * @return dateNaissance
     */
    @JsonProperty("dateNaissance")
    public String getDateNaissance() {
        return this.dateNaissance;
    }

    /**
     * Setter de l'attribut dateNaissance
     * @param dateNaissance date de naissance
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Getter de l'attribut lieuNaissance
     * @return lieuNaissance
     */
    @JsonProperty("lieuNaissance")
    public String getLieuNaissance() {
        return this.lieuNaissance;
    }

    /**
     * Setter de l'attribut lieuNaissance
     * @param lieuNaissance lieu de naissance
     */
    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    /**
     * Méthode toString : affiche les informations de la classe
     * @return une chaîne de caractères contenant les informations de la classe
     */
    @Override
    public String toString() {
        return "Naissance{" + "dateNaissance='" + dateNaissance + '\'' + ", lieuNaissance='" + lieuNaissance + '\'' + '}';
    }
}
