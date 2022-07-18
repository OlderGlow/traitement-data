package fr.diginamic.dal;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import javax.persistence.*;
import java.util.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * @see RealisateurDAO
 * @see Realisateur
 * @see EntityManager
 * @see EntityTransaction
 * @see Query
 * @see List
 * Classe RealisateurDAO qui permet de gérer les réalisateurs en base de données
 */
public class RealisateurDAO implements DAO<Realisateur> {

    /**
     * EntityManagerFactory
     */
    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

    /**
     * EntityManager
     */
    EntityManager em = emf.createEntityManager();

    /**
     * Méthode qui créer un réalisateur en base de données
     * @param objet objet à créer de type Réalisateur
     * @throws DalException exception
     */
    @Override
    public void create(Realisateur objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.persist(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la création du réalisateur", e);
        }
    }

    /**
     * Méthode qui permet de mettre à jour un réalisateur en base de données
     * @param objet objet à mettre à jour de type Réalisateur
     * @throws DalException exception
     */
    @Override
    public void update(Realisateur objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.merge(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la mise à jour du réalisateur");
        }
    }

    /**
     * Méthode qui permet de supprimer un réalisateur en base de données
     * @param objet objet à supprimer de type Réalisateur
     * @throws DalException exception
     */
    @Override
    public void delete(Realisateur objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.remove(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la suppression du réalisateur");
        }
    }

    /**
     * Méthode qui permet de récupérer tous les réalisateurs en base de données
     * @return objet réalisateur
     * @throws DalException exception
     */
    @Override
    public List<Realisateur> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT r FROM Realisateur r", Realisateur.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les réalisateurs");
        }
    }

    /**
     * Méthode qui permet de récupérer un réalisateur en base de données
     * @param id id du réalisateur
     * @return objet réalisateur
     * @throws DalException exception
     */
    @Override
    public Realisateur selectById(long id) throws DalException {
        try {
            return em.find(Realisateur.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du réalisateur");
        }
    }

    /**
     * Méthode qui permet de récupérer un réalisateur en base de données
     * @param nom nom du réalisateur
     * @return objet réalisateur
     * @throws DalException exception
     */
    public Realisateur selectByIdentite(String nom) throws DalException {
        try {
            return em.createQuery("SELECT r FROM Realisateur r WHERE r.identite = :nom", Realisateur.class).setParameter("nom", nom).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
