package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

/**
 * Classe RoleManager qui permet de gérer les opérations sur les rôles
 * @author Julien Picquet
 * @version 1.0
 * @see Role
 * @see RoleManager
 */
public class RoleManager {
    public static volatile RoleManager instance;
    public static RoleDAO roleDAO;

    /**
     * Constructeur de la classe RoleManager
     */
    private RoleManager() {
        roleDAO = DAOFactory.getRoleDAO();
    }

    /**
     * Méthode qui permet de récupérer l'instance de la classe RoleManager
     * @return instance de la classe RoleManager
     */
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


    /**
     * Méthode qui permet de récupérer tous les rôles
     * @return liste de rôles
     */
    public List<Role> selectAll() throws BLLException {
        try {
            return roleDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des roles", e);
        }
    }

    /**
     * Méthode qui permet de récupérer un rôle par son id
     * @param id id du rôle
     * @return rôle
     */
    public Role selectById(int id) throws BLLException {
        try {
            return roleDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du role", e);
        }
    }

    /**
     * Méthode qui permet de créer un role dans la base de données
     * @param role role à créer
     */
    public void create(Role role) throws BLLException {
        try {
            if(roleDAO.selectByName(role.getCharacterName()) == null) {
                roleDAO.create(role);
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion du role", e);
        }
    }

    /**
     * Méthode qui permet de mettre à jour un role dans la base de données
     * @param role role à mettre à jour
     */
    public void update(Role role) throws BLLException {
        try {
            roleDAO.update(role);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour du role", e);
        }
    }

    /**
     * Méthode qui permet de supprimer un role dans la base de données
     * @param role role à supprimer
     */
    public void delete(Role role) throws BLLException {
        try {
            roleDAO.delete(role);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression du role", e);
        }
    }
}
