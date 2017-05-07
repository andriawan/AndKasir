/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.model;

import java.util.Date;

/**
 *
 * @author andriawan
 */
public class Transaksi {
    
    private int idTransaksi;
    private int totalItem;
    private int totalHarga;
    private int idKasir;
    private long tglTransaksi;
    private KasirUser kasir;
    
    public Transaksi(int idTransaksi, int totalItem ,int totalHarga, long tglTransaksi, KasirUser kasir) {
        this.idTransaksi = idTransaksi;
        this.totalItem = totalItem;
        this.totalHarga = totalHarga;
        this.tglTransaksi = tglTransaksi;
        this.kasir = kasir;
    }
    
    public Transaksi(int idTransaksi, int totalItem ,int totalHarga, long tglTransaksi, int idKasir) {
        this.idTransaksi = idTransaksi;
        this.totalItem = totalItem;
        this.totalHarga = totalHarga;
        this.tglTransaksi = tglTransaksi;
        this.idKasir = idKasir;
    }
    
    public Transaksi(int totalItem ,int totalHarga, long tglTransaksi, int idKasir) {
        this.totalItem = totalItem;
        this.totalHarga = totalHarga;
        this.tglTransaksi = tglTransaksi;
        this.idKasir = idKasir;
    }
    

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public long getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(long  date) {
        this.tglTransaksi = date;
    }

    public KasirUser getKasir() {
        return kasir;
    }

    public void setKasir(KasirUser kasir) {
        this.kasir = kasir;
    }

    public int getIdKasir() {
        return idKasir;
    }

    public void setIdKasir(int idKasir) {
        this.idKasir = idKasir;
    }    
    
    
}
