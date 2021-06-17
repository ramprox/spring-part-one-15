package ru.geekbrains;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        ProductRepository productRepository = new ProductRepository();
        productRepository.save(new Product(1L, "Product 1", 123.45f));
        productRepository.save(new Product(2L, "Product 2", 456.78f));
        productRepository.save(new Product(3L, "Product 3", 444.67f));
        productRepository.save(new Product(4L, "Product 4", 567.58f));
        productRepository.save(new Product(5L, "Product 5", 839.64f));
        productRepository.save(new Product(6L, "Product 6", 937.78f));
        productRepository.save(new Product(7L, "Product 7", 444.67f));
        productRepository.save(new Product(8L, "Product 8", 123.78f));
        productRepository.save(new Product(9L, "Product 9", 999.54f));
        productRepository.save(new Product(10L, "Product 10", 777.77f));

        sc.setAttribute("productRepository", productRepository);
    }
}
