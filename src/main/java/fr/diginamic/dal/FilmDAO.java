package fr.diginamic.dal;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import javax.persistence.*;
import java.util.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * @see FilmDAO
 * @see Film
 * @see EntityManager
 * @see EntityTransaction
 * @see Query
 * @see List
 * Classe FilmDAO qui permet de gérer les films en base de données
 */
public class FilmDAO implements DAO<Film> {

    /**
     * EntityManagerFactory
     */
    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

    /**
     * EntityManager
     */
    EntityManager em = emf.createEntityManager();

    /**
     * Méthode qui créer un film en base de données
     *
     * @param objet objet à créer de type Film
     * @throws DalException exception
     */
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

    /**
     * Méthode qui permet de mettre à jour un film en base de données
     *
     * @param objet objet à mettre à jour de type Film
     * @throws DalException exception
     */
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

    /**
     * Méthode qui permet de supprimer un film en base de données
     *
     * @param objet objet à supprimer de type Film
     * @throws DalException exception
     */
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

    /**
     * Méthode qui permet de récupérer tous les films en base de données
     *
     * @return liste de films
     * @throws DalException exception
     */
    @Override
    public List<Film> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT f FROM Film f", Film.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les films");
        }
    }

    /**
     * Méthode qui permet de récupérer un film en base de données
     *
     * @param id id du film
     * @return film
     * @throws DalException exception
     */
    @Override
    public Film selectById(long id) throws DalException {
        try {
            return em.find(Film.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du film");
        }
    }

    /**
     * Méthode qui permet de récupérer un film en base de données à partir de son identifiant imdb
     *
     * @param idImdb identifiant imdb du film
     * @return film
     * @throws DalException exception
     */
    public Film selectByIdImdb(String idImdb) throws DalException {
        try {
            return em.createQuery("SELECT f FROM Film f WHERE f.idOmdb = :idImdb", Film.class).setParameter("idImdb", idImdb).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Méthode qui permet de récupérer un film en base de données à partir d'un nom d'acteur
     *
     * @param acteur nom d'acteur du film
     * @return film
     * @throws DalException exception
     */
    public List<Film> selectFilmsByActeur(String acteur) throws DalException {
        try {
            return em.createQuery("SELECT f FROM Film f JOIN f.acteurs a WHERE a.identite = :acteur", Film.class).setParameter("acteur", acteur).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération des films de l'acteur");
        }
    }

    /**
     * Méthode qui permet de récupérer une liste de film en base de données à partir de deux dates
     *
     * @param year1 date de début
     * @param year2 date de fin
     * @return liste de films
     * @throws DalException exception
     */
    public List<Film> selectMovieBetweenTwoYears(String year1, String year2) throws DalException {
        try {
            return em.createQuery("SELECT f FROM Film f WHERE f.anneeSortie >= :year1 AND f.anneeSortie <= :year2", Film.class).setParameter("year1", year1).setParameter("year2", year2).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération des films entre deux années");
        }
    }

    /**
     * Méthode qui permet de récupérer une liste de film en base de données à partir de deux acteurs
     *
     * @param acteur1 acteur 1
     * @param acteur2 acteur 2
     * @return liste de films
     * @throws DalException exception
     */
    public List<Film> selectMoviesCommonActors(String acteur1, String acteur2) throws DalException {
        try {
            return em.createQuery("SELECT f FROM Film f JOIN f.acteurs a WHERE a.identite = :acteur1 AND f.id IN (SELECT f.id FROM Film f JOIN f.acteurs a WHERE a.identite = :acteur2)", Film.class).setParameter("acteur1", acteur1).setParameter("acteur2", acteur2).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération des films des deux acteurs");
        }
    }

    /**
     * Méthode qui permet de récupérer une liste de film en base de données à partir de deux dates et d'un acteur
     *
     * @param acteur acteur
     * @param date1  date de début
     * @param date2  date de fin
     * @return liste de films
     * @throws DalException exception
     */
    public List<Film> selectMoviesBetweenDatesAndHavingActors(String date1, String date2, String acteur) throws DalException {
        try {
            return em.createQuery("SELECT f FROM Film f JOIN f.acteurs a WHERE a.identite=:acteur AND f.id IN (SELECT f.id FROM Film f WHERE f.anneeSortie BETWEEN :date1 AND :date2)", Film.class).setParameter("acteur", acteur).setParameter("date1", date1).setParameter("date2", date2).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération des films entre deux dates et ayant un acteur");
        }
    }
}
