package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

public class RealisateurManager {
    public static volatile RealisateurManager instance;
    public static RealisateurDAO realisateurDAO;

    private RealisateurManager() {
        realisateurDAO = DAOFactory.getRealisateurDAO();
    }

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

    public List<Realisateur> selectAll() throws BLLException {
        try {
            return realisateurDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des réalisateurs", e);
        }
    }

    public Realisateur selectById(int id) throws BLLException {
        try {
            return realisateurDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du réalisateur", e);
        }
    }

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

    public void update(Realisateur realisateur) throws BLLException {
        try {
            realisateurDAO.update(realisateur);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour du réalisateur", e);
        }
    }

    public void delete(Realisateur realisateur) throws BLLException {
        try {
            realisateurDAO.delete(realisateur);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression du réalisateur", e);
        }
    }
}
