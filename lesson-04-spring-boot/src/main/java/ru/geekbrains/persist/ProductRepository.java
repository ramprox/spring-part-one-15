package ru.geekbrains.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private Map<Long, Product> productMap = new ConcurrentHashMap<Long, Product>();
    private AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        insert(new Product(1L, "Хлеб", 35.59f));
        insert(new Product(2L, "Картофель", 77.69f));
        insert(new Product(3L, "Молоко", 45.76f));
        insert(new Product(4L, "Помидоры", 180.45f));
        insert(new Product(5L, "Огурцы", 120.32f));
    }

    public List<Product> findAll() {
        return new ArrayList<Product>(productMap.values());
    }

    public Product delete(long id) {
        return productMap.remove(id);
    }

    public Optional<Product> findById(long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public void insert(Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    public void update(Product product) {
        productMap.put(product.getId(), product);
    }

    public void save(Product product) {
        if(product.getId() == null) {
            insert(product);
        } else {
            update(product);
        }
    }
}
