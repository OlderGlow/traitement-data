package fr.diginamic.dal;

/**
 * Exception levée lors d'une erreur dans la base de données
 * @author Julien Picquet
 * @version 1.0
 */
public class DalException extends Exception {

    /**
     * Constructeur vide de la classe
     */
    public DalException() {
    }

    /**
     * Constructeur de la classe
     * @param message Message de l'exception
     */
    public DalException(String message) {
        super("Erreur DAL " + message);
    }

    /**
     * Constructeur de la classe
     * @param message Message de l'exception
     * @param cause Cause de l'exception
     */
    public DalException(String message, Throwable cause) {
        super("Erreur DAL " + message, cause);
    }
}