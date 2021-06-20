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
        productRepository.save(new Product(1L, "Хлеб", 123.45f));
        productRepository.save(new Product(2L, "Молоко", 456.78f));
        productRepository.save(new Product(3L, "Картофель", 444.67f));
        productRepository.save(new Product(4L, "Колбаса", 567.58f));
        productRepository.save(new Product(5L, "Сыр", 839.64f));
        productRepository.save(new Product(6L, "Морковь", 937.78f));
        productRepository.save(new Product(7L, "Молоко", 444.67f));
        productRepository.save(new Product(8L, "Селёдка", 123.78f));
        productRepository.save(new Product(9L, "Помидоры", 999.54f));
        productRepository.save(new Product(10L, "Огурцы", 777.77f));

        sc.setAttribute("productRepository", productRepository);
    }
}
