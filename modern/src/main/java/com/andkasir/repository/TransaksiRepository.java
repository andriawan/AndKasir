package com.andkasir.repository;

import com.andkasir.entity.Transaksi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
    
    Optional<Transaksi> findByNomorTransaksi(String nomorTransaksi);
    
    @Query("SELECT t FROM Transaksi t WHERE t.statusTransaksi = :status ORDER BY t.createdAt DESC")
    Page<Transaksi> findByStatus(@Param("status") Transaksi.StatusTransaksi status, Pageable pageable);
    
    @Query("SELECT t FROM Transaksi t WHERE t.idKasir = :idKasir AND t.statusTransaksi = :status ORDER BY t.createdAt DESC")
    Page<Transaksi> findByKasirAndStatus(@Param("idKasir") Long idKasir, @Param("status") Transaksi.StatusTransaksi status, Pageable pageable);
    
    @Query("SELECT t FROM Transaksi t WHERE t.createdAt BETWEEN :startDate AND :endDate AND t.statusTransaksi = :status ORDER BY t.createdAt DESC")
    Page<Transaksi> findByDateRangeAndStatus(@Param("startDate") LocalDateTime startDate, 
                                           @Param("endDate") LocalDateTime endDate, 
                                           @Param("status") Transaksi.StatusTransaksi status, 
                                           Pageable pageable);
    
    @Query("SELECT t FROM Transaksi t WHERE LOWER(t.nomorTransaksi) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "AND t.statusTransaksi = :status ORDER BY t.createdAt DESC")
    Page<Transaksi> searchByNomorTransaksi(@Param("keyword") String keyword, 
                                         @Param("status") Transaksi.StatusTransaksi status, 
                                         Pageable pageable);
    
    @Query("SELECT SUM(t.grandTotal) FROM Transaksi t WHERE t.statusTransaksi = :status " +
           "AND t.createdAt BETWEEN :startDate AND :endDate")
    java.math.BigDecimal getTotalSalesByDateRange(@Param("startDate") LocalDateTime startDate, 
                                                @Param("endDate") LocalDateTime endDate, 
                                                @Param("status") Transaksi.StatusTransaksi status);
    
    @Query("SELECT COUNT(t) FROM Transaksi t WHERE t.statusTransaksi = :status " +
           "AND t.createdAt BETWEEN :startDate AND :endDate")
    Long countTransactionsByDateRange(@Param("startDate") LocalDateTime startDate, 
                                    @Param("endDate") LocalDateTime endDate, 
                                    @Param("status") Transaksi.StatusTransaksi status);
    
    @Query("SELECT SUM(t.totalItem) FROM Transaksi t WHERE t.statusTransaksi = :status " +
           "AND t.createdAt BETWEEN :startDate AND :endDate")
    Long getTotalItemsByDateRange(@Param("startDate") LocalDateTime startDate, 
                                @Param("endDate") LocalDateTime endDate, 
                                @Param("status") Transaksi.StatusTransaksi status);
    
    @Query("SELECT t FROM Transaksi t WHERE DATE(t.createdAt) = CURRENT_DATE AND t.statusTransaksi = :status ORDER BY t.createdAt DESC")
    List<Transaksi> findTodayTransactions(@Param("status") Transaksi.StatusTransaksi status);
    
    @Query("SELECT COUNT(t) FROM Transaksi t WHERE DATE(t.createdAt) = CURRENT_DATE AND t.statusTransaksi = :status")
    Long countTodayTransactions(@Param("status") Transaksi.StatusTransaksi status);
    
    @Query("SELECT SUM(t.grandTotal) FROM Transaksi t WHERE DATE(t.createdAt) = CURRENT_DATE AND t.statusTransaksi = :status")
    java.math.BigDecimal getTodaySales(@Param("status") Transaksi.StatusTransaksi status);
}