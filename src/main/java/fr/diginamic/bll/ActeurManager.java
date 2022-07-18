package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

public class ActeurManager {
    public static volatile ActeurManager instance;
    public static ActeurDAO acteurDAO;

    private ActeurManager() {
        acteurDAO = DAOFactory.getActeurDAO();
    }

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

    public List<Acteur> selectAll() throws BLLException {
        try {
            return acteurDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des acteurs", e);
        }
    }

    public Acteur selectById(long id) throws BLLException {
        try {
            return acteurDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération de l'acteur", e);
        }
    }

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

    public void update(Acteur acteur) throws BLLException {
        try {
            acteurDAO.update(acteur);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour de l'acteur", e);
        }
    }

    public void delete(Acteur acteur) throws BLLException {
        try {
            acteurDAO.delete(acteur);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression de l'acteur", e);
        }
    }

    public List<Acteur> selectCastingByMovie(String nom) throws BLLException {
        try {
            return acteurDAO.selectCastingByMovie(nom);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération de l'acteur", e);
        }
    }
}
