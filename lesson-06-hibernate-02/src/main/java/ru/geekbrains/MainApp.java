package ru.geekbrains;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.entity.Order;
import ru.geekbrains.service.ListService;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ListService service = context.getBean(ListService.class);
        List<Order> orders = service.getOrdersByCustomerId(2L);
        orders.forEach(System.out::println);

        System.out.println();

        orders = service.getOrdersByProductId(3L);
        orders.forEach(System.out::println);
        context.close();
    }
}
