package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

/**
 * Classe LieuTournageManager qui gère les opérations sur les lieux de tournage
 *
 * @author Julien Picquet
 * @version 1.0
 * @see LieuTournage
 * @see LieuTournageManager
 */
public class LieuTournageManager {
    public static volatile LieuTournageManager instance;
    public static LieuTournageDAO lieuTournageDAO;

    /**
     * Constructeur de la classe LieuTournageManager
     */
    private LieuTournageManager() {
        lieuTournageDAO = DAOFactory.getLieuTournageDAO();
    }

    /**
     * Méthode qui retourne l'instance de la classe LieuTournageManager
     *
     * @return instance de la classe LieuTournageManager
     */
    public static LieuTournageManager getInstance() {
        if (instance == null) {
            synchronized (LieuTournageManager.class) {
                if (instance == null) {
                    instance = new LieuTournageManager();
                }
            }
        }
        return instance;
    }

    /**
     * Méthode qui retourne la liste des lieux de tournage
     *
     * @return Liste des lieux de tournage
     */
    public List<LieuTournage> selectAll() throws BLLException {
        try {
            return lieuTournageDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des lieux de tournage", e);
        }
    }

    /**
     * Méthode qui retourne un lieu de tournage à partir de son id
     *
     * @param id Identifiant du lieu de tournage
     * @return LieuTournage
     */
    public LieuTournage selectById(int id) throws BLLException {
        try {
            return lieuTournageDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du lieu de tournage", e);
        }
    }

    /**
     * Méthode qui permet d'ajouter un lieu de tournage à la base de données
     *
     * @param lieuTournage Lieu de tournage à ajouter
     */
    public void create(LieuTournage lieuTournage) throws BLLException {
        try {
            if (lieuTournageDAO.selectByVille(lieuTournage.getVille()) == null) {
                lieuTournageDAO.create(lieuTournage);
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion du lieu de tournage", e);
        }
    }

    /**
     * Méthode qui permet de mettre à jour un lieu de tournage
     *
     * @param lieuTournage Lieu de tournage à mettre à jour
     */
    public void update(LieuTournage lieuTournage) throws BLLException {
        try {
            lieuTournageDAO.update(lieuTournage);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour du lieu de tournage", e);
        }
    }

    /**
     * Méthode qui permet de supprimer un lieu de tournage
     *
     * @param lieuTournage Lieu de tournage à supprimer
     */
    public void delete(LieuTournage lieuTournage) throws BLLException {
        try {
            lieuTournageDAO.delete(lieuTournage);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression du lieu de tournage", e);
        }
    }
}
