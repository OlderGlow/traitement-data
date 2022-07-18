package fr.diginamic.dal;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import javax.persistence.*;
import java.util.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * @see PaysDAO
 * @see Pays
 * @see EntityManager
 * @see EntityTransaction
 * @see Query
 * @see List
 * Classe PaysDAO qui permet de gérer les pays en base de données
 */
public class PaysDAO implements DAO<Pays> {

    /**
     * EntityManagerFactory
     */
    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

    /**
     * EntityManager
     */
    EntityManager em = emf.createEntityManager();

    /**
     * Méthode qui créer un pays en base de données
     * @param objet objet à créer de type Pays
     * @throws DalException exception
     */
    @Override
    public void create(Pays objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.persist(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la création du pays", e);
        }
    }

    /**
     * Méthode qui permet de mettre à jour un pays en base de données
     * @param objet objet à mettre à jour de type Pays
     * @throws DalException exception
     */
    @Override
    public void update(Pays objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.merge(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la mise à jour du pays");
        }
    }

    /**
     * Méthode qui permet de supprimer un pays en base de données
     * @param objet objet à supprimer de type Pays
     * @throws DalException exception
     */
    @Override
    public void delete(Pays objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.remove(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la suppression du pays");
        }
    }

    /**
     * Méthode qui permet de récupérer tous les pays en base de données
     * @return liste de pays
     * @throws DalException exception
     *
     */
    @Override
    public List<Pays> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT p FROM Pays p", Pays.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les pays");
        }
    }

    /**
     * Méthode qui permet de récupérer un pays en base de données
     * @param id id du pays
     * @return pays
     * @throws DalException exception
     */
    @Override
    public Pays selectById(long id) throws DalException {
        try {
            return em.find(Pays.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du pays");
        }
    }

    /**
     * Méthode qui permet de récupérer un pays en base de données
     * @param nom nom du pays
     * @return pays
     * @throws DalException exception
     */
    public Pays selectByNom(String nom) throws DalException {
        try {
            return em.createQuery("SELECT p FROM Pays p WHERE p.nom = :nom", Pays.class).setParameter("nom", nom).getSingleResult();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du pays");
        }
    }
}
