package fr.diginamic.dal;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import javax.persistence.*;
import java.util.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * @see Role
 * @see RoleDAO
 * @see EntityManager
 * @see EntityManagerFactory
 * @see EntityTransaction
 * @see Query
 * @see List
 * Classe RoleDAO qui permet de gérer les accès à la base de données pour les objets de type Role
 */
public class RoleDAO implements DAO<Role> {

    /**
     * EntityManagerFactory
     */
    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

    /**
     * EntityManager
     */
    EntityManager em = emf.createEntityManager();

    /**
     * Méthode qui permet de créer un nouvel objet de type Role dans la base de données
     * @param objet objet à créer
     * @throws DalException exception levée si une erreur est survenue lors de la création de l'objet
     */
    @Override
    public void create(Role objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.persist(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la création du role", e);
        }
    }

    /**
     * Méthode qui permet de mettre à jour un objet de type Role dans la base de données
     * @param objet objet à mettre à jour
     * @throws DalException exception levée si une erreur est survenue lors de la mise à jour de l'objet
     */
    @Override
    public void update(Role objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.merge(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la mise à jour du role");
        }
    }

    /**
     * Méthode qui permet de supprimer un objet de type Role dans la base de données
     * @param objet objet à supprimer
     * @throws DalException exception levée si une erreur est survenue lors de la suppression de l'objet
     */
    @Override
    public void delete(Role objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.remove(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la suppression du role");
        }
    }

    /**
     * Méthode qui permet de récupérer une liste d'objets de type Role dans la base de données
     * @return liste d'objets de type Role
     * @throws DalException exception levée si une erreur est survenue lors de la récupération de l'objet
     */
    @Override
    public List<Role> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les roles");
        }
    }

    /**
     * Méthode qui permet de récupérer un objet de type Role dans la base de données
     * @param id id de l'objet à récupérer
     * @return objet de type Role
     * @throws DalException exception levée si une erreur est survenue lors de la récupération de l'objet
     */
    @Override
    public Role selectById(long id) throws DalException {
        try {
            return em.find(Role.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du role");
        }
    }

    /**
     * Méthode qui permet de récupérer une liste d'objets de type Role dans la base de données
     * @param name nom de l'objet à récupérer
     * @return objet de type Role
     * @throws DalException exception levée si une erreur est survenue lors de la récupération de l'objet
     */
    public Role selectByName(String name) throws DalException {
        try {
            return em.createQuery("SELECT r FROM Role r WHERE r.characterName = :name", Role.class).setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
