package com.andkasir.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transaksi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaksi {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nomor_transaksi", unique = true, nullable = false, length = 20)
    private String nomorTransaksi;
    
    @Column(name = "total_item", nullable = false)
    private Integer totalItem;
    
    @Column(name = "total_harga", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalHarga;
    
    @Column(name = "diskon", precision = 5, scale = 2)
    private BigDecimal diskon = BigDecimal.ZERO;
    
    @Column(name = "pajak", precision = 5, scale = 2)
    private BigDecimal pajak = BigDecimal.ZERO;
    
    @Column(name = "grand_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal grandTotal;
    
    @Column(name = "bayar", nullable = false, precision = 12, scale = 2)
    private BigDecimal bayar;
    
    @Column(name = "kembalian", nullable = false, precision = 12, scale = 2)
    private BigDecimal kembalian;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kasir", nullable = false)
    @JsonIgnore
    private User kasir;
    
    @Column(name = "id_kasir", nullable = false)
    private Long idKasir;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "keterangan", length = 500)
    private String keterangan;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status_transaksi", nullable = false)
    private StatusTransaksi statusTransaksi = StatusTransaksi.SELESAI;
    
    @JdbcTypeCode(SqlTypes.JSON)
    private List<DetailTransaksi> detailTransaksi;
    
    public enum StatusTransaksi {
        DRAFT,
        PROSES,
        SELESAI,
        BATAL
    }
    
    // Business logic methods
    public void calculateTotals() {
        if (detailTransaksi != null && !detailTransaksi.isEmpty()) {
            BigDecimal subtotal = detailTransaksi.stream()
                    .map(DetailTransaksi::getSubtotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            
            BigDecimal totalSetelahDiskon = subtotal.subtract(diskon != null ? diskon : BigDecimal.ZERO);
            BigDecimal totalSetelahPajak = totalSetelahDiskon.add(
                    pajak != null ? pajak.multiply(totalSetelahDiskon).divide(BigDecimal.valueOf(100)) : BigDecimal.ZERO);
            
            this.totalHarga = subtotal;
            this.grandTotal = totalSetelahPajak;
            this.totalItem = detailTransaksi.stream()
                    .mapToInt(DetailTransaksi::getJumlah)
                    .sum();
            
            if (bayar != null) {
                this.kembalian = bayar.subtract(grandTotal);
            }
        }
    }
    
    public String getNomorTransaksiFormat() {
        return "TRX-" + String.format("%06d", id) + "-" + createdAt.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
    
    public String getGrandTotalFormat() {
        return String.format("Rp %,.2f", grandTotal);
    }
    
    public String getBayarFormat() {
        return String.format("Rp %,.2f", bayar);
    }
    
    public String getKembalianFormat() {
        return String.format("Rp %,.2f", kembalian);
    }
}