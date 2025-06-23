package com.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OOPApplication {
    public static void main(String[] args) {
        // Load biến môi trường từ file .env (nếu có)
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        // Set biến môi trường thủ công nếu dùng trong application.properties
        String dbPassword = dotenv.get("DB_PASSWORD");
        if (dbPassword != null) {
            System.setProperty("DB_PASSWORD", dbPassword);
        }

        SpringApplication.run(OOPApplication.class, args);
    }
}