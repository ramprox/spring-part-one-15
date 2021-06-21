package homeworkLesson2.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Cart {
    private final ProductRepository productRepository;
    private final List<Product> products = new LinkedList<>();

    @Autowired
    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void add(long id) {
        products.add(productRepository.findById(id));
    }

    public void remove(long id) {
        for(Product product : products) {
            if(product.getId() == id) {
                products.remove(product);
                return;
            }
        }
    }

    public void printContent() {
        products.forEach(product -> {
            System.out.print("ID: " + product.getId() + "; ");
            System.out.print("Имя: " + product.getName() + "; ");
            System.out.println("Цена: " + product.getCost());
        });
    }
}
