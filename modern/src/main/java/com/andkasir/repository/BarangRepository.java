package com.andkasir.repository;

import com.andkasir.entity.Barang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarangRepository extends JpaRepository<Barang, Long> {
    
    Optional<Barang> findByKodeBarangAndDeletedFalse(String kodeBarang);
    
    List<Barang> findByNamaBarangContainingIgnoreCaseAndDeletedFalse(String namaBarang);
    
    List<Barang> findByKodeBarangContainingIgnoreCaseAndDeletedFalse(String kodeBarang);
    
    @Query("SELECT b FROM Barang b WHERE b.deleted = false AND " +
           "(LOWER(b.namaBarang) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.kodeBarang) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Barang> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT b FROM Barang b WHERE b.deleted = false AND b.stok < :minStock")
    List<Barang> findLowStockItems(@Param("minStock") Integer minStock);
    
    @Query("SELECT b FROM Barang b WHERE b.deleted = false AND b.stok > 0 ORDER BY b.namaBarang")
    List<Barang> findAllAvailable();
    
    @Query("SELECT COUNT(b) FROM Barang b WHERE b.deleted = false AND b.stok < :minStock")
    Long countLowStockItems(@Param("minStock") Integer minStock);
    
    boolean existsByKodeBarangAndDeletedFalse(String kodeBarang);
    
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Barang b " +
           "WHERE b.kodeBarang = :kodeBarang AND b.id != :id AND b.deleted = false")
    boolean existsByKodeBarangAndIdNotAndDeletedFalse(@Param("kodeBarang") String kodeBarang, @Param("id") Long id);
    
    @Query("SELECT COALESCE(SUM(b.stok), 0) FROM Barang b WHERE b.deleted = false")
    Long getTotalStock();
    
    @Query("SELECT COUNT(b) FROM Barang b WHERE b.deleted = false")
    Long getTotalActiveProducts();
}