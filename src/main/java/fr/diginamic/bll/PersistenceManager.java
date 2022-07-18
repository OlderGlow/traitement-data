package fr.diginamic.bll;

import javax.persistence.*;

/**
 * Classe PersistenceManager : classe qui permet de gérer la persistence de l'application (EntityManager)
 * @author Julien Picquet
 * @version 1.0
 * @see EntityManagerFactory
 * @see PersistenceManager
 * @see Persistence
 */
public class PersistenceManager {
    private static PersistenceManager instance;
    private final EntityManagerFactory emf;

    /**
     * Constructeur de la classe PersistenceManager
     */
    private PersistenceManager() {
        emf = javax.persistence.Persistence.createEntityManagerFactory("imdb-tp");
    }

    /**
     * Méthode qui permet de récupérer l'instance de la classe PersistenceManager
     * @return instance de la classe PersistenceManager
     */
    public static PersistenceManager getInstance() {
        if (instance == null) {
            instance = new PersistenceManager();
        }
        return instance;
    }

    /**
     * Méthode qui permet de récupérer l'EntityManagerFactory de l'application
     * @return EntityManagerFactory de l'application
     */
    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
