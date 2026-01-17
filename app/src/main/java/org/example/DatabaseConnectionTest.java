package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DatabaseConnectionTest implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("========================================");
            System.out.println("✅ DATABASE CONNECTED SUCCESSFULLY!");
            System.out.println("========================================");
            System.out.println("Database Name: " + connection.getCatalog());
            System.out.println("Database URL: " + connection.getMetaData().getURL());
            System.out.println("Database User: " + connection.getMetaData().getUserName());
            System.out.println("========================================");
        } catch (Exception e) {
            System.out.println("========================================");
            System.out.println("❌ DATABASE CONNECTION FAILED!");
            System.out.println("========================================");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.out.println("========================================");
        }
    }
}
