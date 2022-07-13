package fr.diginamic.dal;

public class DAOFactory {
    public static ActeurDAO getActeurDAO() {
        return new ActeurDAO();
    }

    public static FilmDAO getFilmDAO() {
        return new FilmDAO();
    }
}
