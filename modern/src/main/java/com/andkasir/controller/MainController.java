package com.andkasir.controller;

import com.andkasir.entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    
    @FXML private BorderPane mainPane;
    @FXML private Label welcomeLabel;
    @FXML private Label userRoleLabel;
    @FXML private Label dateTimeLabel;
    
    private User currentUser;
    
    @FXML
    public void initialize() {
        // Initialize main dashboard
        updateDateTime();
        startClock();
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
        updateUserInfo();
    }
    
    private void updateUserInfo() {
        if (currentUser != null) {
            welcomeLabel.setText("Selamat datang, " + currentUser.getNama());
            userRoleLabel.setText("Role: " + currentUser.getRole().getRole());
        }
    }
    
    private void updateDateTime() {
        if (dateTimeLabel != null) {
            dateTimeLabel.setText(java.time.LocalDateTime.now()
                    .format(java.time.format.DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss")));
        }
    }
    
    private void startClock() {
        // Update clock every second
        javafx.animation.Timeline timeline = new javafx.animation.Timeline(
                new javafx.animation.KeyFrame(
                        javafx.util.Duration.seconds(1),
                        event -> updateDateTime()
                )
        );
        timeline.setCycleCount(javafx.animation.Timeline.INDEFINITE);
        timeline.play();
    }
    
    @FXML
    private void handleLogout() {
        // Implement logout logic
        System.exit(0);
    }
    
    @FXML
    private void handleProductManagement() {
        // Load product management view
    }
    
    @FXML
    private void handleUserManagement() {
        // Load user management view
    }
    
    @FXML
    private void handleTransaction() {
        // Load transaction view
    }
    
    @FXML
    private void handleReports() {
        // Load reports view
    }
}