package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import ru.geekbrains.entity.*;
import java.util.List;

@Service
@Repository
public class CustomerDao {

    private ExecuteManager executeManager;

    @Autowired
    public CustomerDao(ExecuteManager executeManager) {
        this.executeManager = executeManager;
    }

    public Customer findById(Long id) {
        return executeManager.executeQuery(em -> em.find(Customer.class, id));
    }

    public void deleteById(Long id) {
        executeManager.executeTransactionalQuery(em -> em.createQuery("DELETE FROM Customer p WHERE p.id = :id")
                .setParameter("id", id)
                .executeUpdate());
    }

    public void saveOrUpdate(Customer customer) {
        executeManager.executeTransactionalQuery(em -> em.merge(customer));
    }

    public List<Order> getOrdersByCustomerId(Long id) {
        return executeManager.executeQuery(em -> em.createQuery("SELECT o FROM Order o join fetch o.customer join fetch o.product WHERE o.customer.id = :id", Order.class)
                .setParameter("id", id)
                .getResultList());
    }

    public List<Customer> findAll() {
        return executeManager.executeQuery(em -> em.createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList());
    }
}
