package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

public class LieuTournageManager {
    public static volatile LieuTournageManager instance;
    public static LieuTournageDAO lieuTournageDAO;

    private LieuTournageManager() {
        lieuTournageDAO = DAOFactory.getLieuTournageDAO();
    }

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

    public List<LieuTournage> selectAll() throws BLLException {
        try {
            return lieuTournageDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des lieux de tournage", e);
        }
    }

    public LieuTournage selectById(int id) throws BLLException {
        try {
            return lieuTournageDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du lieu de tournage", e);
        }
    }

    public void create(LieuTournage lieuTournage) throws BLLException {
        try {
            if(lieuTournageDAO.selectByVille(lieuTournage.getVille()) == null) {
                lieuTournageDAO.create(lieuTournage);
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion du lieu de tournage", e);
        }
    }

    public void update(LieuTournage lieuTournage) throws BLLException {
        try {
            lieuTournageDAO.update(lieuTournage);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour du lieu de tournage", e);
        }
    }

    public void delete(LieuTournage lieuTournage) throws BLLException {
        try {
            lieuTournageDAO.delete(lieuTournage);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression du lieu de tournage", e);
        }
    }
}
