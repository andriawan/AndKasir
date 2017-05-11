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
public class Laporan {
    
    int idTransaksi, id_petugas, kode_barang, harga, jumlah;
    String namaBarang, username, NamaLengkap, status;
    long tgl_transaksi;

    public Laporan(int idTransaksi, int id_petugas, int kode_barang, int harga, int jumlah, String namaBarang, String username, String NamaLengkap, String status, long tgl_transaksi) {
        this.idTransaksi = idTransaksi;
        this.id_petugas = id_petugas;
        this.kode_barang = kode_barang;
        this.harga = harga;
        this.jumlah = jumlah;
        this.namaBarang = namaBarang;
        this.username = username;
        this.NamaLengkap = NamaLengkap;
        this.status = status;
        this.tgl_transaksi = tgl_transaksi;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public int getId_petugas() {
        return id_petugas;
    }

    public void setId_petugas(int id_petugas) {
        this.id_petugas = id_petugas;
    }

    public int getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(int kode_barang) {
        this.kode_barang = kode_barang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNamaLengkap() {
        return NamaLengkap;
    }

    public void setNamaLengkap(String NamaLengkap) {
        this.NamaLengkap = NamaLengkap;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTgl_transaksi() {
        return tgl_transaksi;
    }

    public void setTgl_transaksi(long tgl_transaksi) {
        this.tgl_transaksi = tgl_transaksi;
    }
    
    
}
