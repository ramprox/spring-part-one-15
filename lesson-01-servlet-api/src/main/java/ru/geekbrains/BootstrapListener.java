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

        Product product = new Product(1L, "Хлеб", 123.45f);
        product.setDescription("Свежий хлеб");
        productRepository.save(product);

        product = new Product(2L, "Молоко", 456.78f);
        product.setDescription("Свежее молоко");
        productRepository.save(product);

        product = new Product(3L, "Картофель", 444.67f);
        product.setDescription("Молодой картофель");
        productRepository.save(product);

        product = new Product(4L, "Колбаса", 567.58f);
        product.setDescription("Свежая колбаса");
        productRepository.save(product);

        product = new Product(5L, "Сыр", 839.64f);
        product.setDescription("Свежий сыр");
        productRepository.save(product);

        product = new Product(6L, "Морковь", 937.78f);
        product.setDescription("Свежая морковь");
        productRepository.save(product);

        product = new Product(7L, "Виноград", 444.67f);
        product.setDescription("Свежий виноград");
        productRepository.save(product);

        product = new Product(8L, "Селедка", 123.78f);
        product.setDescription("Селедка слабосоленая");
        productRepository.save(product);

        product = new Product(9L, "Помидоры", 999.54f);
        product.setDescription("Свежие помидоры");
        productRepository.save(product);

        product = new Product(10L, "Огурцы", 777.77f);
        product.setDescription("Свежие огурцы");
        productRepository.save(product);

        sc.setAttribute("productRepository", productRepository);
    }
}
