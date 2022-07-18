package fr.diginamic.dal;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import javax.persistence.*;
import java.util.*;

public class RealisateurDAO implements DAO<Realisateur> {

    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

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

    @Override
    public List<Realisateur> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT r FROM Realisateur r", Realisateur.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les réalisateurs");
        }
    }

    @Override
    public Realisateur selectById(long id) throws DalException {
        try {
            return em.find(Realisateur.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du réalisateur");
        }
    }

    public Realisateur selectByIdentite(String nom) throws DalException {
        try {
            return em.createQuery("SELECT r FROM Realisateur r WHERE r.identite = :nom", Realisateur.class).setParameter("nom", nom).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
