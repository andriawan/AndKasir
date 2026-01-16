package com.andkasir;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.andkasir")
@EnableJpaRepositories(basePackages = "com.andkasir.repository")
public class AndKasirApplication extends Application {
    
    private ConfigurableApplicationContext springContext;
    private static final String APPLICATION_NAME = "AndKasir Desktop v2.0";
    private static final double WINDOW_WIDTH = 1200;
    private static final double WINDOW_HEIGHT = 800;
    
    @Override
    public void init() {
        // Initialize Spring context
        springContext = new SpringApplicationBuilder(AndKasirApplication.class)
                .headless(false) // Important for JavaFX
                .run();
    }
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load login screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            loader.setControllerFactory(springContext::getBean);
            
            Parent root = loader.load();
            
            // Configure primary stage
            primaryStage.setTitle(APPLICATION_NAME);
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(600);
            primaryStage.setWidth(WINDOW_WIDTH);
            primaryStage.setHeight(WINDOW_HEIGHT);
            primaryStage.centerOnScreen();
            
            // Create scene with CSS
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/modern-dark.css").toExternalForm());
            
            primaryStage.setScene(scene);
            primaryStage.show();
            
            // Setup close handler
            primaryStage.setOnCloseRequest(event -> {
                Platform.exit();
                closeSpringContext();
            });
            
        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
            e.printStackTrace();
            Platform.exit();
        }
    }
    
    @Override
    public void stop() {
        closeSpringContext();
    }
    
    private void closeSpringContext() {
        if (springContext != null) {
            springContext.close();
        }
    }
    
    public static void main(String[] args) {
        // Set JavaFX system properties for better rendering
        System.setProperty("javafx.autoprogrammatic", "true");
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");
        
        launch(args);
    }
    
    public static ConfigurableApplicationContext getSpringContext() {
        // This can be used by controllers to access Spring context if needed
        return ((AndKasirApplication) Application.getUserData()).springContext;
    }
}