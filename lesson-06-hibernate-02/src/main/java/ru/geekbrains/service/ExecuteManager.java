package ru.geekbrains.service;

import javax.persistence.*;
import java.util.function.*;

public class ExecuteManager {

    private EntityManagerFactory emFactory;

    public ExecuteManager(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public <R> R executeQuery(Function<EntityManager, R> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            em.close();
        }
    }

    public void executeTransactionalQuery(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
