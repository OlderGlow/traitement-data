package fr.diginamic.dal;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import javax.persistence.*;
import java.util.*;

public class ActeurDAO implements DAO<Acteur> {

    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    @Override
    public void create(Acteur objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.persist(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la création de l'acteur", e);
        }
    }

    @Override
    public void update(Acteur objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.merge(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la mise à jour de l'acteur");
        }
    }

    @Override
    public void delete(Acteur objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.remove(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la suppression de l'acteur");
        }
    }

    @Override
    public List<Acteur> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT a FROM Acteur a", Acteur.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les acteurs");
        }
    }

    @Override
    public Acteur selectById(long id) throws DalException {
        try {
            return em.find(Acteur.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de l'acteur");
        }
    }

    public Acteur selectByIdentite(String identite) throws DalException {
        try {
            return em.createQuery("SELECT a FROM Acteur a WHERE a.identite = :identite", Acteur.class).setParameter("identite", identite).getSingleResult();
        } catch (Exception e) {
           return null;
        }
    }

    public List<Acteur> selectCastingByMovie(String movie) throws DalException {
        try {
            return em.createQuery("SELECT a FROM Acteur a JOIN a.roles c WHERE c.film.nom = :movie", Acteur.class).setParameter("movie", movie).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération des acteurs du film");
        }
    }
}
