package com.andkasir.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Table(name = "detail_transaksi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"transaksi"})
public class DetailTransaksi {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transaksi", nullable = false)
    private Transaksi transaksi;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_barang", nullable = false)
    private Barang barang;
    
    @Column(name = "kode_barang", nullable = false, length = 50)
    private String kodeBarang;
    
    @Column(name = "nama_barang", nullable = false)
    private String namaBarang;
    
    @Column(nullable = false)
    private Integer jumlah;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal hargaSatuan;
    
    @Column(name = "diskon_item", precision = 5, scale = 2)
    private BigDecimal diskonItem = BigDecimal.ZERO;
    
    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;
    
    // Business logic methods
    public void calculateSubtotal() {
        BigDecimal hargaSetelahDiskon = hargaSatuan.subtract(diskonItem != null ? diskonItem : BigDecimal.ZERO);
        this.subtotal = hargaSetelahDiskon.multiply(BigDecimal.valueOf(jumlah));
    }
    
    public String getHargaSatuanFormat() {
        return String.format("Rp %,.2f", hargaSatuan);
    }
    
    public String getSubtotalFormat() {
        return String.format("Rp %,.2f", subtotal);
    }
    
    public String getDiskonFormat() {
        if (diskonItem != null && diskonItem.compareTo(BigDecimal.ZERO) > 0) {
            return String.format("-%.2f%%", diskonItem);
        }
        return "-";
    }
}