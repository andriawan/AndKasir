package com.andkasir.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    
    public enum UserRole {
        ADMIN("ADMIN"),
        KASIR("KASIR");
        
        private final String role;
        
        UserRole(String role) {
            this.role = role;
        }
        
        public String getRole() {
            return role;
        }
    }
    
    public enum UserStatus {
        ACTIVE("ACTIVE"),
        INACTIVE("INACTIVE"),
        SUSPENDED("SUSPENDED");
        
        private final String status;
        
        UserStatus(String status) {
            this.status = status;
        }
        
        public String getStatus() {
            return status;
        }
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(name = "nama", nullable = false)
    private String nama;
    
    @Column(length = 100)
    private String email;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.KASIR;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status = UserStatus.ACTIVE;
    
    @Column(name = "level_permission")
    private Integer levelPermission;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    
    @Column(name = "is_deleted")
    private Boolean deleted = false;
    
    // UserDetails implementation
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return status != UserStatus.SUSPENDED;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return status == UserStatus.ACTIVE && !deleted;
    }
    
    // Business logic methods
    public boolean hasRole(UserRole requiredRole) {
        return this.role == requiredRole;
    }
    
    public boolean isAdmin() {
        return hasRole(UserRole.ADMIN);
    }
    
    public boolean isKasir() {
        return hasRole(UserRole.KASIR);
    }
    
    public void updateLastLogin() {
        this.lastLogin = LocalDateTime.now();
    }
}