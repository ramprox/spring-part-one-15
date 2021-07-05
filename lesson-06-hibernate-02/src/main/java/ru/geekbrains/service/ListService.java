package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.Order;

import java.util.List;

@Service
public class ListService {
    private CustomerDao customerDao;
    private ProductDao productDao;

    @Autowired
    public ListService(CustomerDao customerDao, ProductDao productDao) {
        this.customerDao = customerDao;
        this.productDao = productDao;
    }

    public List<Order> getOrdersByCustomerId(Long id) {
        return customerDao.getOrdersByCustomerId(id);
    }

    public List<Order> getOrdersByProductId(Long id) {
        return productDao.getOrdersByProductId(id);
    }
}
