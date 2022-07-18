package fr.diginamic.dal;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import javax.persistence.*;
import java.util.*;

public class RoleDAO implements DAO<Role> {
    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    @Override
    public void create(Role objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.persist(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la création du role", e);
        }
    }

    @Override
    public void update(Role objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.merge(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la mise à jour du role");
        }
    }

    @Override
    public void delete(Role objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.remove(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la suppression du role");
        }
    }

    @Override
    public List<Role> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les roles");
        }
    }

    @Override
    public Role selectById(long id) throws DalException {
        try {
            return em.find(Role.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du role");
        }
    }

    public Role selectByName(String name) throws DalException {
        try {
            return em.createQuery("SELECT r FROM Role r WHERE r.characterName = :name", Role.class).setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
