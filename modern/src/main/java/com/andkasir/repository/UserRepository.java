package com.andkasir.repository;

import com.andkasir.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsernameAndDeletedFalse(String username);
    
    boolean existsByUsernameAndDeletedFalse(String username);
    
    boolean existsByEmailAndDeletedFalse(String email);
    
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u " +
           "WHERE u.email = :email AND u.id != :id AND u.deleted = false")
    boolean existsByEmailAndIdNotAndDeletedFalse(@Param("email") String email, @Param("id") Long id);
    
    @Query("SELECT u FROM User u WHERE u.deleted = false AND " +
           "(LOWER(u.nama) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<User> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT u FROM User u WHERE u.deleted = false AND u.role = :role")
    List<User> findByRole(@Param("role") User.UserRole role);
    
    @Query("SELECT u FROM User u WHERE u.deleted = false AND u.status = :status")
    List<User> findByStatus(@Param("status") User.UserStatus status);
    
    @Query("SELECT u FROM User u WHERE u.deleted = false AND u.status = :status AND u.role = :role")
    List<User> findByStatusAndRole(@Param("status") User.UserStatus status, @Param("role") User.UserRole role);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.deleted = false AND u.status = :status")
    Long countByStatus(@Param("status") User.UserStatus status);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.deleted = false AND u.role = :role")
    Long countByRole(@Param("role") User.UserRole role);
    
    @Query("SELECT u FROM User u WHERE u.deleted = false ORDER BY u.lastLogin DESC")
    List<User> findRecentUsers(Pageable pageable);
    
    @Query("UPDATE User u SET u.lastLogin = CURRENT_TIMESTAMP WHERE u.id = :userId")
    void updateLastLogin(@Param("userId") Long userId);
}