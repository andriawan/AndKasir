package com.andkasir.service;

import com.andkasir.entity.User;
import com.andkasir.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsernameAndDeletedFalse(username);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // Check if password matches
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Update last login
                user.updateLastLogin();
                userRepository.save(user);
                
                return user;
            }
        }
        
        return null;
    }
    
    public User createUser(String username, String password, String nama, User.UserRole role) {
        // Check if username already exists
        if (userRepository.existsByUsernameAndDeletedFalse(username)) {
            throw new IllegalArgumentException("Username sudah digunakan");
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setNama(nama);
        user.setRole(role);
        user.setStatus(User.UserStatus.ACTIVE);
        
        return userRepository.save(user);
    }
    
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Optional<User> userOptional = userRepository.findByUsernameAndDeletedFalse(username);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                return true;
            }
        }
        
        return false;
    }
    
    public void initDefaultAdmin() {
        // Check if admin user exists
        if (!userRepository.existsByUsernameAndDeletedFalse("admin")) {
            // Create default admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setNama("Administrator");
            admin.setRole(User.UserRole.ADMIN);
            admin.setStatus(User.UserStatus.ACTIVE);
            admin.setEmail("admin@andkasir.com");
            
            userRepository.save(admin);
            System.out.println("Default admin user created: admin/admin");
        }
        
        // Check if kasir user exists
        if (!userRepository.existsByUsernameAndDeletedFalse("kasir")) {
            // Create default kasir user
            User kasir = new User();
            kasir.setUsername("kasir");
            kasir.setPassword(passwordEncoder.encode("kasir"));
            kasir.setNama("Kasir Default");
            kasir.setRole(User.UserRole.KASIR);
            kasir.setStatus(User.UserStatus.ACTIVE);
            kasir.setEmail("kasir@andkasir.com");
            
            userRepository.save(kasir);
            System.out.println("Default kasir user created: kasir/kasir");
        }
    }
    
    // This method provides controller factory for FXML loading
    public javafx.util.Callback<Class<?>, Object> getControllerFactory() {
        return clazz -> com.andkasir.config.ApplicationContextProvider.getApplicationContext().getBean(clazz);
    }
}