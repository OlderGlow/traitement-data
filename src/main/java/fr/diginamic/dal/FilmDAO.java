package fr.diginamic.dal;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import javax.persistence.*;
import java.util.*;

public class FilmDAO implements DAO<Film> {
    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    @Override
    public void create(Film objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.persist(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la création du film", e);
        }
    }

    @Override
    public void update(Film objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.merge(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la mise à jour du film");
        }
    }

    @Override
    public void delete(Film objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.remove(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la suppression du film");
        }
    }

    @Override
    public List<Film> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT f FROM Film f", Film.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les films");
        }
    }

    @Override
    public Film selectById(long id) throws DalException {
        try {
            return em.find(Film.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du film");
        }
    }

    public Film selectByIdImdb(String idImdb) throws DalException {
        try {
            return em.createQuery("SELECT f FROM Film f WHERE f.idOmdb = :idImdb", Film.class).setParameter("idImdb", idImdb).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Film> selectFilmsByActeur(String acteur) throws DalException {
        try {
            return em.createQuery("SELECT f FROM Film f JOIN f.acteurs a WHERE a.identite = :acteur", Film.class).setParameter("acteur", acteur).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération des films de l'acteur");
        }
    }

    public List<Film> selectMovieBetweenTwoYears(String year1, String year2) throws DalException {
        try {
            return em.createQuery("SELECT f FROM Film f WHERE f.anneeSortie >= :year1 AND f.anneeSortie <= :year2", Film.class).setParameter("year1", year1).setParameter("year2", year2).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération des films entre deux années");
        }
    }

    public List<Film> selectMoviesCommonActors(String acteur1, String acteur2) throws DalException {
        try {
            return em.createQuery("SELECT f FROM Film f JOIN f.acteurs a WHERE a.identite = :acteur1 AND f.id IN (SELECT f.id FROM Film f JOIN f.acteurs a WHERE a.identite = :acteur2)", Film.class).setParameter("acteur1", acteur1).setParameter("acteur2", acteur2).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération des films des deux acteurs");
        }
    }

    public List<Film> selectMoviesBetweenDatesAndHavingActors(String date1, String date2, String acteur) throws DalException {
        try {
            return em.createQuery("SELECT f FROM Film f JOIN f.acteurs a WHERE a.identite=:acteur AND f.id IN (SELECT f.id FROM Film f WHERE f.anneeSortie BETWEEN :date1 AND :date2)", Film.class).setParameter("acteur", acteur).setParameter("date1", date1).setParameter("date2", date2).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération des films entre deux dates et ayant un acteur");
        }
    }
}
