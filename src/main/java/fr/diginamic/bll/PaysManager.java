package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

/**
 * Classe PaysManager qui gère les opérations sur les pays
 * @author Julien Picquet
 * @version 1.0
 * @see Pays
 * @see PaysManager
 */
public class PaysManager {
    public static volatile PaysManager instance;
    public static PaysDAO paysDAO;

    /**
     * Constructeur de la classe PaysManager
     */
    private PaysManager() {
        paysDAO = DAOFactory.getPaysDAO();
    }

    /**
     * Méthode qui retourne l'instance de la classe PaysManager
     * @return instance de la classe PaysManager
     */
    public static PaysManager getInstance() {
        if (instance == null) {
            synchronized (PaysManager.class) {
                if (instance == null) {
                    instance = new PaysManager();
                }
            }
        }
        return instance;
    }

    /**
     * Méthode qui retourne la liste des pays
     * @return Liste des pays
     */
    public List<Pays> selectAll() throws BLLException {
        try {
            return paysDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des pays", e);
        }
    }

    /**
     * Méthode qui retourne un pays à partir de son id
     * @param id Identifiant du pays
     * @return Pays
     */
    public Pays selectById(long id) throws BLLException {
        try {
            return paysDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du pays", e);
        }
    }

    /**
     * Méthode qui ajoute un pays en base de données
     * @param pays Pays à ajouter
     */
    public void create(Pays pays) throws BLLException {
        try {
            if(paysDAO.selectByNom(pays.getNom()) == null) {
                paysDAO.create(pays);
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion du pays", e);
        }
    }

    /**
     * Méthode qui permet de mettre à jour un pays
     * @param pays Pays à mettre à jour
     */
    public void update(Pays pays) throws BLLException {
        try {
            paysDAO.update(pays);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour du pays", e);
        }
    }

    /**
     * Méthode qui permet de supprimer un pays
     * @param pays Pays à supprimer
     */
    public void delete(Pays pays) throws BLLException {
        try {
            paysDAO.delete(pays);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression du pays", e);
        }
    }
}
