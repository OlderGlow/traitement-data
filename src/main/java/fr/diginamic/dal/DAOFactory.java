package fr.diginamic.dal;

/**
 * @author Julien Picquet
 * @version 1.0
 * @see DAOFactory
 * Classe DAOFactory qui permet de créer des DAO pour les différents types de données
 */
public class DAOFactory {

    /**
     * Méthode qui permet de créer un DAO pour les acteurs
     *
     * @return un DAO pour les acteurs
     */
    public static ActeurDAO getActeurDAO() {
        return new ActeurDAO();
    }

    /**
     * Méthode qui permet de créer un DAO pour les films
     *
     * @return un DAO pour les films
     */
    public static FilmDAO getFilmDAO() {
        return new FilmDAO();
    }

    /**
     * Méthode qui permet de créer un DAO pour les pays
     *
     * @return un DAO pour les pays
     */
    public static PaysDAO getPaysDAO() {
        return new PaysDAO();
    }

    /**
     * Méthode qui permet de créer un DAO pour les réalisateurs
     *
     * @return un DAO pour les réalisateurs
     */
    public static RealisateurDAO getRealisateurDAO() {
        return new RealisateurDAO();
    }

    /**
     * Méthode qui permet de créer un DAO pour les types de roles
     *
     * @return un DAO pour les types de roles
     */
    public static RoleDAO getRoleDAO() {
        return new RoleDAO();
    }

    /**
     * Méthode qui permet de créer un DAO pour les lieux de tournage
     *
     * @return un DAO pour les lieux de tournage
     */
    public static LieuTournageDAO getLieuTournageDAO() {
        return new LieuTournageDAO();
    }
}
