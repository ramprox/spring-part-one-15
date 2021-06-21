package homeworkLesson2;

import homeworkLesson2.persist.Cart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("------ Получаем 3 корзины и смотрим содержимое -------");
        Cart cart1 =  context.getBean(Cart.class);
        Cart cart2 =  context.getBean(Cart.class);
        Cart cart3 =  context.getBean(Cart.class);
        printCarts(cart1, cart2, cart3);

        System.out.println("------ Заполняем корзины разными продуктами -------");
        cart1.add(1L);
        cart1.add(2L);
        cart1.add(3L);

        cart2.add(2L);
        cart2.add(3L);
        cart2.add(4L);

        cart3.add(3L);
        cart3.add(4L);
        cart3.add(5L);

        System.out.println("------ Смотрим содержимое корзин -------");
        printCarts(cart1, cart2, cart3);

        System.out.println("------ Удаляем по одному продукту из каждой корзины -------");
        cart1.remove(3L);
        cart2.remove(3L);
        cart3.remove(3L);

        System.out.println("------ Смотрим содержимое корзин -------");
        printCarts(cart1, cart2, cart3);
    }

    private static void printCarts(Cart... carts) {
        for(int i = 0; i < carts.length; i++) {
            System.out.println("В корзине " + (i + 1) +  " есть:");
            carts[i].printContent();
        }
    }
}
