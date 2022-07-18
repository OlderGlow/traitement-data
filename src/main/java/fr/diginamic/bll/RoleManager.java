package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

public class RoleManager {
    public static volatile RoleManager instance;
    public static RoleDAO roleDAO;

    private RoleManager() {
        roleDAO = DAOFactory.getRoleDAO();
    }

    public static RoleManager getInstance() {
        if (instance == null) {
            synchronized (RoleManager.class) {
                if (instance == null) {
                    instance = new RoleManager();
                }
            }
        }
        return instance;
    }


    public List<Role> selectAll() throws BLLException {
        try {
            return roleDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des roles", e);
        }
    }

    public Role selectById(int id) throws BLLException {
        try {
            return roleDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du role", e);
        }
    }

    public void create(Role role) throws BLLException {
        try {
            if(roleDAO.selectByName(role.getCharacterName()) == null) {
                roleDAO.create(role);
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion du role", e);
        }
    }

    public void update(Role role) throws BLLException {
        try {
            roleDAO.update(role);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour du role", e);
        }
    }

    public void delete(Role role) throws BLLException {
        try {
            roleDAO.delete(role);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression du role", e);
        }
    }
}
