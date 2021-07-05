package ru.geekbrains;

import org.springframework.context.annotation.*;
import ru.geekbrains.service.*;

@Configuration
@ComponentScan("ru.geekbrains.service")
public class AppConfig {
    @Bean
    public EntityManagerFactoryUtil entityManagerFactory() {
        return new EntityManagerFactoryUtil();
    }

    @Bean
    public ExecuteManager executeManager(EntityManagerFactoryUtil emfUtil) {
        return new ExecuteManager(emfUtil.getEmFactory());
    }
}
