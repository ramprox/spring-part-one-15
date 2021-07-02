package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductDao {
    private final EntityManagerFactory emFactory;

    public ProductDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public Product findById(Long id) {
        Product result;

        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        result = em.find(Product.class, id);
        em.getTransaction().commit();
        em.close();

        return result;
    }

    public List<Product> findAll() {
        List<Product> result;

        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        result = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        em.getTransaction().commit();
        em.close();

        return result;
    }

    public void deleteById(Long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Product p WHERE p.id = :id").setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public Product saveOrUpdate(Product product) {
        Product result;

        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        result = em.merge(product);
        em.getTransaction().commit();
        em.close();

        return result;
    }
}
