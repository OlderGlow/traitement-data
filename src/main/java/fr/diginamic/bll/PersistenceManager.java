package fr.diginamic.bll;

import javax.persistence.*;

public class PersistenceManager {
    private static PersistenceManager instance;
    private final EntityManagerFactory emf;

    private PersistenceManager() {
        emf = javax.persistence.Persistence.createEntityManagerFactory("imdb-tp");
    }

    public static PersistenceManager getInstance() {
        if (instance == null) {
            instance = new PersistenceManager();
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
