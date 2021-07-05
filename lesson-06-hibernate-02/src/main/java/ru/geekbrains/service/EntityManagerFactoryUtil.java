package ru.geekbrains.service;

import org.hibernate.cfg.Configuration;

import javax.annotation.*;
import javax.persistence.EntityManagerFactory;

public class EntityManagerFactoryUtil {
    private EntityManagerFactory emFactory;

    public EntityManagerFactory getEmFactory() {
        return emFactory;
    }

    @PostConstruct
    public void init() {
        emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        System.out.println("EntityManagerFactory builded");
    }

    @PreDestroy
    public void close() {
        emFactory.close();
        System.out.println("EntityManagerFactory closed");
    }
}
