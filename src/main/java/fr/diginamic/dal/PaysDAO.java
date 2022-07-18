package fr.diginamic.dal;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import javax.persistence.*;
import java.util.*;

public class PaysDAO implements DAO<Pays> {
    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

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

    @Override
    public List<Pays> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT p FROM Pays p", Pays.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les pays");
        }
    }

    @Override
    public Pays selectById(long id) throws DalException {
        try {
            return em.find(Pays.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du pays");
        }
    }

    public Pays selectByNom(String nom) throws DalException {
        try {
            return em.createQuery("SELECT p FROM Pays p WHERE p.nom = :nom", Pays.class).setParameter("nom", nom).getSingleResult();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du pays");
        }
    }
}
