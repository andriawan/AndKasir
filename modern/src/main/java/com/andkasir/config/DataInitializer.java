package com.andkasir.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final com.andkasir.service.AuthService authService;
    
    public DataInitializer(com.andkasir.service.AuthService authService) {
        this.authService = authService;
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initializing AndKasir Desktop application...");
        System.out.println("Creating default users...");
        
        try {
            // Initialize default admin and kasir users
            authService.initDefaultAdmin();
            
            System.out.println("Default users created successfully:");
            System.out.println("Username: admin, Password: admin");
            System.out.println("Username: kasir, Password: kasir");
            System.out.println("Application is ready to start!");
            
        } catch (Exception e) {
            System.err.println("Error initializing data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}