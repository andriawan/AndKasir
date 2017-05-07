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
public class DetailTransaksi{
    
    int id_detail;
    int id_transaksi;
    int id_barang;
    int jumlah;
    int harga;
    
    String namaBarang;
    
    public DetailTransaksi(int id_detail, int id_transaksi, int id_barang, int jumlah, int harga) {
        this.id_detail = id_detail;
        this.id_transaksi = id_transaksi;
        this.id_barang = id_barang;
        this.jumlah = jumlah;
        this.harga = harga;
    }
    
    public DetailTransaksi(int id_transaksi, int id_barang, int jumlah, int harga) {
        this.id_transaksi = id_transaksi;
        this.id_barang = id_barang;
        this.jumlah = jumlah;
        this.harga = harga;
    }
    
    public DetailTransaksi(int id_transaksi, int id_barang, String nama, int jumlah, int harga) {
        this.id_transaksi = id_transaksi;
        this.id_barang = id_barang;
        this.jumlah = jumlah;
        this.harga = harga;
        this.namaBarang = nama;
    }    
    
    public int getId_detail() {
        return id_detail;
    }

    public void setId_detail(int id_detail) {
        this.id_detail = id_detail;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
    
    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }
}
