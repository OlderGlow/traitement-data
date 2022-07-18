package fr.diginamic.dal;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import javax.persistence.*;
import java.util.*;

public class LieuTournageDAO implements DAO<LieuTournage> {

    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();


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

    @Override
    public List<LieuTournage> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT l FROM LieuTournage l", LieuTournage.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les lieux de tournage");
        }
    }

    @Override
    public LieuTournage selectById(long id) throws DalException {
        try {
            return em.find(LieuTournage.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du lieu de tournage");
        }
    }

    public LieuTournage selectByVille(String ville) throws DalException {
        try {
            return em.createQuery("SELECT l FROM LieuTournage l WHERE l.ville = :nom", LieuTournage.class).setParameter("nom", ville).getSingleResult();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du lieu de tournage");
        }
    }
}
