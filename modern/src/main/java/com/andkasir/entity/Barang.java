package com.andkasir.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "barang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Barang {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "kode_barang", unique = true, nullable = false, length = 50)
    private String kodeBarang;
    
    @Column(name = "nama_barang", nullable = false)
    private String namaBarang;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal harga;
    
    @Column(nullable = false)
    private Integer stok;
    
    @Column(name = "jumlah_barang_masuk")
    private Integer jumlahBarangMasuk;
    
    @Column(name = "jumlah_barang_keluar")
    private Integer jumlahBarangKeluar;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "is_deleted")
    private Boolean deleted = false;
    
    // Business logic methods
    public void tambahStok(Integer jumlah) {
        this.stok += jumlah;
        this.jumlahBarangMasuk = (this.jumlahBarangMasuk != null ? this.jumlahBarangMasuk : 0) + jumlah;
    }
    
    public void kurangiStok(Integer jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
            this.jumlahBarangKeluar = (this.jumlahBarangKeluar != null ? this.jumlahBarangKeluar : 0) + jumlah;
        } else {
            throw new IllegalArgumentException("Stok tidak mencukupi");
        }
    }
    
    public String getHargaFormat() {
        return String.format("Rp %,.2f", harga);
    }
    
    public Boolean isStokCukup(Integer jumlah) {
        return this.stok >= jumlah;
    }
}