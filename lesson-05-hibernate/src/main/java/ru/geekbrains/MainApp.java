package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Product;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ProductDao productDao = new ProductDao(emFactory);

        // ----- INSERT -----
        productDao.saveOrUpdate(new Product(null, "Хлеб", 35));
        productDao.saveOrUpdate(new Product(null, "Картофель", 77));
        productDao.saveOrUpdate(new Product(null, "Молоко", 45));
        productDao.saveOrUpdate(new Product(null, "Помидоры", 180));
        productDao.saveOrUpdate(new Product(null, "Огурцы", 120));

        // ----- Find by Id -----
        Product product1 = productDao.findById(1L);
        Product product2 = productDao.findById(2L);
        System.out.println(product1);
        System.out.println(product2);
        System.out.println();

        // ----- Find All -----
        List<Product> products = productDao.findAll();
        products.forEach(System.out::println);
        System.out.println();

        // ----- DELETE -----
        System.out.println("Deleting product with id = 1 and id = 3");
        productDao.deleteById(1L);
        productDao.deleteById(3L);
        products = productDao.findAll();
        products.forEach(System.out::println);

        // ----- UPDATE -----
        System.out.println("Updating products with id = 4 and id = 5");
        productDao.saveOrUpdate(new Product(4L, "Помидоры", 200));
        productDao.saveOrUpdate(new Product(5L, "Огурцы", 200));
        products = productDao.findAll();
        products.forEach(System.out::println);
    }
}
