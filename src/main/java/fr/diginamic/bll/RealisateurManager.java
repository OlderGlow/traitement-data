package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

/**
 * Classe RealisateurManager qui permet de gérer les réalisateurs
 * @author Julien Picquet
 * @version 1.0
 * @see Realisateur
 * @see RealisateurManager
 */
public class RealisateurManager {
    public static volatile RealisateurManager instance;
    public static RealisateurDAO realisateurDAO;

    /**
     * Constructeur de la classe RealisateurManager
     */
    private RealisateurManager() {
        realisateurDAO = DAOFactory.getRealisateurDAO();
    }


    /**
     * Méthode qui permet de récupérer l'instance de la classe RealisateurManager
     * @return instance de la classe RealisateurManager
     */
    public static RealisateurManager getInstance() {
        if (instance == null) {
            synchronized (RealisateurManager.class) {
                if (instance == null) {
                    instance = new RealisateurManager();
                }
            }
        }
        return instance;
    }

    /**
     * Méthode qui permet de récupérer tous les réalisateurs
     * @return liste de réalisateurs
     */
    public List<Realisateur> selectAll() throws BLLException {
        try {
            return realisateurDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des réalisateurs", e);
        }
    }

    /**
     * Méthode qui permet de récupérer un réalisateur par son id
     * @param id id du réalisateur
     * @return réalisateur
     */
    public Realisateur selectById(int id) throws BLLException {
        try {
            return realisateurDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du réalisateur", e);
        }
    }

    /**
     * Méthode qui permet d'ajouter en base de données un réalisateur
     * @param realisateur réalisateur à ajouter
     * @return réalisateur ajouté
     * @throws BLLException exception lors de l'ajout du réalisateur
     */
    public Realisateur create(Realisateur realisateur) throws BLLException {
        try {
            Realisateur realisateurExistant = realisateurDAO.selectByIdentite(realisateur.getIdentite());
            if(realisateurExistant == null) {
                realisateurDAO.create(realisateur);
            } else {
                return realisateurExistant;
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion du réalisateur", e);
        }
        return realisateur;
    }

    /**
     * Méthode qui permet de mettre à jour en base de données un réalisateur
     * @param realisateur réalisateur à mettre à jour
     * @throws BLLException exception lors de la mise à jour du réalisateur
     */
    public void update(Realisateur realisateur) throws BLLException {
        try {
            realisateurDAO.update(realisateur);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour du réalisateur", e);
        }
    }

    /**
     * Méthode qui permet de supprimer en base de données un réalisateur
     * @param realisateur réalisateur à supprimer
     * @throws BLLException exception lors de la suppression du réalisateur
     */
    public void delete(Realisateur realisateur) throws BLLException {
        try {
            realisateurDAO.delete(realisateur);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression du réalisateur", e);
        }
    }
}
