package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

public class FilmManager {

    public static volatile FilmManager instance;
    public static FilmDAO filmDAO;

    private FilmManager() {
        filmDAO = DAOFactory.getFilmDAO();
    }

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

    public List<Film> selectAll() throws BLLException {
        try {
            return filmDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des films", e);
        }
    }

    public Film selectById(int id) throws BLLException {
        try {
            return filmDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du film", e);
        }
    }

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

    public void update(Film film) throws BLLException {
        try {
            filmDAO.update(film);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour du film", e);
        }
    }

    public void delete(Film film) throws BLLException {
        try {
            filmDAO.delete(film);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression du film", e);
        }
    }

    public List<Film> selectMovieByActor(String nom) throws BLLException {
        try {
            return filmDAO.selectFilmsByActeur(nom);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du film", e);
        }
    }

    public List<Film> selectMovieBetweenTwoYears(String date1, String date2) throws BLLException {
        try {
            return filmDAO.selectMovieBetweenTwoYears(date1, date2);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du film", e);
        }
    }

    public List<Film> selectMoviesCommonActors(String acteur1, String acteur2) throws BLLException {
        try {
            return filmDAO.selectMoviesCommonActors(acteur1, acteur2);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du film", e);
        }
    }

    public List<Film> selectMoviesBetweenDatesAndHavingActors(String date1, String date2, String acteur) throws BLLException {
        try {
            return filmDAO.selectMoviesBetweenDatesAndHavingActors(date1, date2, acteur);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du film", e);
        }
    }
}
