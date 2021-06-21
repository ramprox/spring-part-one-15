package homeworkLesson2;

import homeworkLesson2.persist.Cart;
import homeworkLesson2.persist.Product;
import homeworkLesson2.persist.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.LinkedList;
import java.util.List;

@Configuration
@ComponentScan("homeworkLesson2.persist")
public class AppConfig {

    @Bean
    public List<Product> products() {
        List<Product> result = new LinkedList<>();
        result.add(new Product(1L, "Хлеб", 35.59f));
        result.add(new Product(2L, "Картофель", 77.69f));
        result.add(new Product(3L, "Молоко", 45.76f));
        result.add(new Product(4L, "Помидоры", 180.45f));
        result.add(new Product(5L, "Огурцы", 120.32f));
        return result;
    }

    @Bean
    public ProductRepository productRepository(List<Product> products) {
        return new ProductRepository(products);
    }

    @Bean
    @Scope("prototype")
    public Cart cart(ProductRepository productRepository) {
        return new Cart(productRepository);
    }
}
