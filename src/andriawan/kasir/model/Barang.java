/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.model;

/**
 *
 * @author andriawan
 */
public class Barang {
    private int kodeBarang;
    private String namaBarang;
    private int harga;
    private int Stok;

    public Barang(int kodeBarang, String namaBarang, int harga, int Stok) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.Stok = Stok;
    }
    
    public Barang(String namaBarang, int harga, int Stok) {
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.Stok = Stok;
    }

    public int getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(int kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return Stok;
    }

    public void setStok(int Stok) {
        this.Stok = Stok;
    }
    
}
