package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

/**
 * Classe ActeurManager qui gère les opérations sur les acteurs
 * @author Julien Picquet
 * @version 1.0
 * @see Acteur
 * @see ActeurManager
 */
public class ActeurManager {
    public static volatile ActeurManager instance;
    public static ActeurDAO acteurDAO;

    /**
     * Constructeur de la classe ActeurManager
     */
    private ActeurManager() {
        acteurDAO = DAOFactory.getActeurDAO();
    }

    /**
     * Méthode qui retourne l'instance de la classe ActeurManager
     * @return instance de la classe ActeurManager
     */
    public static ActeurManager getInstance() {
        if (instance == null) {
            synchronized (ActeurManager.class) {
                if (instance == null) {
                    instance = new ActeurManager();
                }
            }
        }
        return instance;
    }

    /**
     * Méthode qui retourne la liste des acteurs
     * @return Liste des acteurs
     */
    public List<Acteur> selectAll() throws BLLException {
        try {
            return acteurDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des acteurs", e);
        }
    }

    /**
     * Méthode qui retourne un acteur à partir de son id
     * @param id Identifiant de l'acteur
     * @return Acteur
     */
    public Acteur selectById(long id) throws BLLException {
        try {
            return acteurDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération de l'acteur", e);
        }
    }

    /**
     * Méthode qui permet d'ajouter un acteur
     * @param acteur Acteur à ajouter
     * @return Acteur ajouté
     */
    public Acteur create(Acteur acteur) throws BLLException {
        try {
            Acteur acteurTrouve = acteurDAO.selectByIdImdb(acteur.getIdOmdb());
            if(acteurTrouve == null) {
                acteurDAO.create(acteur);
            } else {
                return acteurTrouve;
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion de l'acteur", e);
        }
        return acteur;
    }

    /**
     * Méthode qui permet de mettre à jour un acteur
     * @param acteur Acteur à mettre à jour
     */
    public void update(Acteur acteur) throws BLLException {
        try {
            acteurDAO.update(acteur);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour de l'acteur", e);
        }
    }

    /**
     * Méthode qui permet de supprimer un acteur
     * @param acteur Acteur à supprimer
     */
    public void delete(Acteur acteur) throws BLLException {
        try {
            acteurDAO.delete(acteur);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression de l'acteur", e);
        }
    }

    /**
     * Méthode qui permet de rechercher la distribution d'un film à partir du nom de l'acteur
     * @param nom Nom de l'acteur
     */
    public List<Acteur> selectCastingByMovie(String nom) throws BLLException {
        try {
            return acteurDAO.selectCastingByMovie(nom);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération de l'acteur", e);
        }
    }

    /**
     * Méthode qui permet de récupérer les acteurs communs à deux films
     * @param movie1 Film 1
     * @param movie2 Film 2
     * @return Liste des acteurs communs
     * @throws BLLException Exception
     */
    public List<Acteur> selectActorsByTwoMovies(String movie1, String movie2) throws BLLException {
        try {
            return acteurDAO.selectActorsByTwoMovies(movie1, movie2);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération de l'acteur", e);
        }
    }
}
