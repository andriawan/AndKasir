package com.andkasir.controller;

import com.andkasir.entity.User;
import com.andkasir.service.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
    
    @FXML private AnchorPane mainPane;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label statusLabel;
    @FXML private Hyperlink forgotPasswordLink;
    @FXML private ProgressBar progressBar;
    @FXML private Label versionLabel;
    
    @Autowired
    private AuthService authService;
    
    private Stage primaryStage;
    
    @FXML
    public void initialize() {
        // Initialize form
        versionLabel.setText("AndKasir v2.0.0");
        progressBar.setVisible(false);
        statusLabel.setText("");
        
        // Add enter key support
        passwordField.setOnKeyPressed(this::handleEnterKey);
        usernameField.setOnKeyPressed(this::handleEnterKey);
        
        // Focus on username field
        usernameField.requestFocus();
    }
    
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        
        // Validation
        if (username.isEmpty()) {
            showError("Username harus diisi");
            return;
        }
        
        if (password.isEmpty()) {
            showError("Password harus diisi");
            return;
        }
        
        showLoading(true);
        
        try {
            // Attempt login
            User user = authService.authenticate(username, password);
            
            if (user != null) {
                showSuccess("Login berhasil!");
                
                // Load main application
                loadMainApplication(user);
            } else {
                showError("Username atau password salah");
            }
            
        } catch (Exception e) {
            showError("Terjadi kesalahan: " + e.getMessage());
            e.printStackTrace();
        } finally {
            showLoading(false);
        }
    }
    
    @FXML
    private void handleForgotPassword(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lupa Password");
        alert.setHeaderText("Reset Password");
        alert.setContentText("Silakan hubungi administrator untuk reset password Anda.\n" +
                          "Atau gunakan default credentials:\n" +
                          "Username: admin\n" +
                          "Password: admin");
        alert.showAndWait();
    }
    
    private void handleEnterKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleLogin(null);
        }
    }
    
    private void loadMainApplication(User user) {
        try {
            // Load main dashboard
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            loader.setControllerFactory(authService.getControllerFactory());
            
            Parent root = loader.load();
            
            // Get main controller and set user
            MainController mainController = loader.getController();
            mainController.setCurrentUser(user);
            
            // Create new scene
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/modern-dark.css").toExternalForm());
            
            // Get current stage
            Stage stage = (Stage) loginButton.getScene().getWindow();
            
            // Update stage
            stage.setTitle("AndKasir Desktop - " + user.getNama());
            stage.setScene(scene);
            stage.setMaximized(true);
            
        } catch (Exception e) {
            showError("Gagal memuat aplikasi utama: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void showLoading(boolean show) {
        progressBar.setVisible(show);
        loginButton.setDisable(show);
        usernameField.setDisable(show);
        passwordField.setDisable(show);
    }
    
    private void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: #ff6b6b;");
    }
    
    private void showSuccess(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: #51cf66;");
    }
    
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}