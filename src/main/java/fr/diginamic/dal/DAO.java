package fr.diginamic.dal;

import java.util.*;

/**
 * Classe DAO qui permet de gérer les accès aux données
 * @author Julien Picquet
 * @version 1.0
 * @param <T>
 */
public interface DAO<T> {
    /**
     * Méthode qui créer un objet dans la base de données
     *
     * @param objet objet à créer
     * @throws DalException exception
     */
    void create(T objet) throws DalException;

    /**
     * Méthode qui permet de mettre à jour un objet dans la base de données
     *
     * @param objet objet à mettre à jour
     * @throws DalException exception
     */
    void update(T objet) throws DalException;

    /**
     * Méthode qui permet de supprimer un objet dans la base de données
     *
     * @param objet objet à supprimer
     * @throws DalException exception
     */
    void delete(T objet) throws DalException;

    /**
     * Méthode qui permet de récupérer tous les objets de la base de données
     *
     * @return liste des objets
     * @throws DalException exception
     */
    List<T> selectAll() throws DalException;

    /**
     * Méthode qui permet de récupérer un objet à partir de son identifiant
     *
     * @param id identifiant de l'objet
     * @return objet
     * @throws DalException exception
     */
    T selectById(long id) throws DalException;
}
