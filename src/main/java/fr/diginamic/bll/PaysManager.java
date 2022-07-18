package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

public class PaysManager {
    public static volatile PaysManager instance;
    public static PaysDAO paysDAO;

    private PaysManager() {
        paysDAO = DAOFactory.getPaysDAO();
    }

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

    public List<Pays> selectAll() throws BLLException {
        try {
            return paysDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des pays", e);
        }
    }

    public Pays selectById(long id) throws BLLException {
        try {
            return paysDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du pays", e);
        }
    }

    public void create(Pays pays) throws BLLException {
        try {
            if(paysDAO.selectByNom(pays.getNom()) == null) {
                paysDAO.create(pays);
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion du pays", e);
        }
    }

    public void update(Pays pays) throws BLLException {
        try {
            paysDAO.update(pays);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour du pays", e);
        }
    }

    public void delete(Pays pays) throws BLLException {
        try {
            paysDAO.delete(pays);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression du pays", e);
        }
    }
}
