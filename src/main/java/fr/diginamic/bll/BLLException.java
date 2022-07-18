package fr.diginamic.bll;

/**
 * Exception pour les erreurs de la couche BLL
 * @author Julien Picquet
 * @version 1.0
 */
public class BLLException extends Exception {

    /**
     * Constructeur de la classe BLLException
     */
    public BLLException() {
    }

    /**
     * Constructeur de la classe BLLException
     * @param message Message d'erreur
     */
    public BLLException(String message) {
        super("Erreur BLL : " + message);
    }

    /**
     * Constructeur de la classe BLLException
     * @param message Message d'erreur
     * @param cause Cause de l'erreur
     */
    public BLLException(String message, Throwable cause) {
        super("Erreur BLL : " + message, cause);
    }
}
