package fr.diginamic.dal;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import javax.persistence.*;
import java.util.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * @see LieuTournageDAO
 * @see LieuTournage
 * @see EntityManager
 * @see EntityTransaction
 * @see Query
 * @see List
 * Classe LieuTournageDAO qui permet de gérer les lieux de tournage en base de données
 */
public class LieuTournageDAO implements DAO<LieuTournage> {

    /**
     * EntityManagerFactory
     */
    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

    /**
     * EntityManager
     */
    EntityManager em = emf.createEntityManager();


    /**
     * Méthode qui créer un lieu de tournage en base de données
     * @param objet objet à créer de type LieuTournage
     * @throws DalException exception
     */
    @Override
    public void create(LieuTournage objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.persist(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la création du lieu de tournage", e);
        }
    }

    /**
     * Méthode qui permet de mettre à jour un lieu de tournage en base de données
     * @param objet objet à mettre à jour de type LieuTournage
     * @throws DalException exception
     */
    @Override
    public void update(LieuTournage objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.merge(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la mise à jour du lieu de tournage");
        }
    }

    /**
     * Méthode qui permet de supprimer un lieu de tournage en base de données
     * @param objet objet à supprimer de type LieuTournage
     * @throws DalException exception
     */
    @Override
    public void delete(LieuTournage objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.remove(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la suppression du lieu de tournage");
        }
    }

    /**
     * Méthode qui permet de récupérer tous les lieux de tournage en base de données
     * @return liste de lieux de tournage
     * @throws DalException exception
     */
    @Override
    public List<LieuTournage> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT l FROM LieuTournage l", LieuTournage.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les lieux de tournage");
        }
    }

    /**
     * Méthode qui permet de récupérer un lieu de tournage en base de données à partir de son id
     * @param id id du lieu de tournage
     * @return lieu de tournage
     * @throws DalException exception
     */
    @Override
    public LieuTournage selectById(long id) throws DalException {
        try {
            return em.find(LieuTournage.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du lieu de tournage");
        }
    }

    /**
     * Méthode qui permet de récupérer un lieu de tournage en base de données à partir d'une ville donnée
     * @param ville ville du lieu de tournage
     * @return lieu de tournage
     * @throws DalException exception
     */
    public LieuTournage selectByVille(String ville) throws DalException {
        try {
            return em.createQuery("SELECT l FROM LieuTournage l WHERE l.ville = :nom", LieuTournage.class).setParameter("nom", ville).getSingleResult();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du lieu de tournage");
        }
    }
}
