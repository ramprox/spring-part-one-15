package homeworkLesson2.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductRepository {
    private final List<Product> products;

    @Autowired
    public ProductRepository(List<Product> products) {
        this.products = products;
    }

    public Product findById(long id) {
        for(Product product : products) {
            if(product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getProducts() {
        return products;
    }
}
