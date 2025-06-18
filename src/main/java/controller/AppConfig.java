package controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.Library;

@Configuration
public class AppConfig {
    @Bean
    public Library library() {
        return new Library();
    }
}