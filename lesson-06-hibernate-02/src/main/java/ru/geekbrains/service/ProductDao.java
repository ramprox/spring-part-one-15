package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import ru.geekbrains.entity.*;
import java.util.List;

@Service
@Repository
public class ProductDao {

    private ExecuteManager executeManager;

    @Autowired
    public ProductDao(ExecuteManager executeManager) {
        this.executeManager = executeManager;
    }

    public Product findById(Long id) {
        return executeManager.executeQuery(em -> em.find(Product.class, id));
    }

    public List<Order> getOrdersByProductId(Long id) {
        return executeManager.executeQuery(em -> em.createQuery("SELECT o FROM Order o join fetch o.customer join fetch o.product WHERE o.product.id = :id", Order.class)
                .setParameter("id", id)
                .getResultList());
    }

    public void deleteById(Long id) {
        executeManager.executeTransactionalQuery(em -> em.createQuery("DELETE FROM Product p WHERE p.id = :id")
                .setParameter("id", id)
                .executeUpdate());
    }

    public void saveOrUpdate(Product product) {
        executeManager.executeTransactionalQuery(em -> em.merge(product));
    }

    public List<Customer> findAll() {
        return executeManager.executeQuery(em -> em.createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList());
    }
}
