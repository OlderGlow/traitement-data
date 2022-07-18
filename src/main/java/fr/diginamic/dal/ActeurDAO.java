package fr.diginamic.dal;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import javax.persistence.*;
import java.util.*;

/**
 * @author Julien Picquet
 * @version 1.0
 * @see ActeurDAO
 * @see Acteur
 * @see EntityManager
 * @see EntityTransaction
 * @see Query
 * @see List
 * Classe ActeurDAO qui permet de gérer les acteurs en base de données
 */
public class ActeurDAO implements DAO<Acteur> {

    /**
     * EntityManagerFactory
     */
    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

    /**
     * EntityManager
     */
    EntityManager em = emf.createEntityManager();

    /**
     * Méthode qui créer un acteur en base de données
     * @param objet objet à créer de type Acteur
     * @throws DalException exception
     */
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

    /**
     * Méthode qui permet de mettre à jour un acteur en base de données
     * @param objet objet à mettre à jour de type Acteur
     * @throws DalException exception
     */
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

    /**
     * Méthode qui permet de supprimer un acteur en base de données
     * @param objet objet à supprimer de type Acteur
     * @throws DalException exception
     */
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

    /**
     * Méthode qui permet de récupérer tous les acteurs en base de données
     * @return liste des acteurs
     * @throws DalException exception
     */
    @Override
    public List<Acteur> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT a FROM Acteur a", Acteur.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les acteurs");
        }
    }

    /**
     * Méthode qui permet de récupérer un acteur à partir de son identifiant
     * @param id identifiant de l'acteur
     * @return objet acteur
     * @throws DalException exception
     */
    @Override
    public Acteur selectById(long id) throws DalException {
        try {
            return em.find(Acteur.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de l'acteur");
        }
    }

    /**
     * Méthode qui permet de récupérer un acteur à partir de son identifiant IMDB
     * @param idImdb identifiant IMDB de l'acteur
     * @return objet acteur
     * @throws DalException exception
     */
    public Acteur selectByIdImdb(String idImdb) throws DalException {
        try {
            return em.createQuery("SELECT a FROM Acteur a WHERE a.idOmdb = :idImdb", Acteur.class).setParameter("idImdb", idImdb).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Méthode qui permet de récupérer tous les acteurs d'un film
     * @param movie film
     * @return liste des acteurs
     * @throws DalException exception
     */
    public List<Acteur> selectCastingByMovie(String movie) throws DalException {
        try {
            return em.createQuery("SELECT a FROM Acteur a JOIN a.roles c WHERE c.film.nom = :movie", Acteur.class).setParameter("movie", movie).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération des acteurs du film");
        }
    }
}
