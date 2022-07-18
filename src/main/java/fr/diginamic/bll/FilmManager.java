package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

/**
 * Classe FilmManager qui gère les opérations sur les acteurs
 * @author Julien Picquet
 * @version 1.0
 * @see Film
 * @see FilmManager
 */
public class FilmManager {

    public static volatile FilmManager instance;
    public static FilmDAO filmDAO;

    /**
     * Constructeur de la classe FilmManager
     */
    private FilmManager() {
        filmDAO = DAOFactory.getFilmDAO();
    }

    /**
     * Méthode qui retourne l'instance de la classe FilmManager
     * @return instance de la classe FilmManager
     */
    public static FilmManager getInstance() {
        if (instance == null) {
            synchronized (FilmManager.class) {
                if (instance == null) {
                    instance = new FilmManager();
                }
            }
        }
        return instance;
    }

    /**
     * Méthode qui retourne la liste des films
     * @return Liste des films
     */
    public List<Film> selectAll() throws BLLException {
        try {
            return filmDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des films", e);
        }
    }

    /**
     * Méthode qui retourne un film à partir de son id
     * @param id Identifiant du film
     * @return Film
     */
    public Film selectById(int id) throws BLLException {
        try {
            return filmDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du film", e);
        }
    }

    /**
     * Méthode qui ajoute un film à la base de données
     * @param film Film à ajouter
     * @return film ajouté
     */
    public Film create(Film film) throws BLLException {
        try {
            Film filmTrouve = filmDAO.selectByIdImdb(film.getIdOmdb());
            if(filmTrouve == null) {
                filmDAO.create(film);
            } else {
                return filmTrouve;
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion du film", e);
        }
        return film;
    }

    /**
     * Méthode qui permet de mettre à jour un film
     * @param film Film à mettre à jour
     */
    public void update(Film film) throws BLLException {
        try {
            filmDAO.update(film);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour du film", e);
        }
    }

    /**
     * Méthode qui permet de supprimer un film
     * @param film Film à supprimer
     */
    public void delete(Film film) throws BLLException {
        try {
            filmDAO.delete(film);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression du film", e);
        }
    }

    /**
     * Méthode qui retourne la liste des films d'un acteur
     * @param nom nom de l'acteur dont on veut les films
     * @return Liste des films
     */
    public List<Film> selectMovieByActor(String nom) throws BLLException {
        try {
            return filmDAO.selectFilmsByActeur(nom);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du film", e);
        }
    }

    /**
     * Méthode qui retourne la liste des films contenue entre deux dates
     * @param date1 Date de début
     * @param date2 Date de fin
     * @return Liste des films
     */
    public List<Film> selectMovieBetweenTwoYears(String date1, String date2) throws BLLException {
        try {
            return filmDAO.selectMovieBetweenTwoYears(date1, date2);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du film", e);
        }
    }

    /**
     * Méthode qui retourne la liste des films contenue entre deux acteurs
     * @param acteur1 Nom de l'acteur 1
     * @param acteur2 Nom de l'acteur 2
     * @return Liste des films
     */
    public List<Film> selectMoviesCommonActors(String acteur1, String acteur2) throws BLLException {
        try {
            return filmDAO.selectMoviesCommonActors(acteur1, acteur2);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du film", e);
        }
    }

    /**
     * Méthode qui retourne la liste des films contenue entre deux dates et un nom d'acteur
     * @param date1 Date de début
     * @param date2 Date de fin
     * @param acteur Nom de l'acteur
     * @return Liste des films
     */
    public List<Film> selectMoviesBetweenDatesAndHavingActors(String date1, String date2, String acteur) throws BLLException {
        try {
            return filmDAO.selectMoviesBetweenDatesAndHavingActors(date1, date2, acteur);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du film", e);
        }
    }
}
