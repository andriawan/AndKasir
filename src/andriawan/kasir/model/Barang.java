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
    
    private int idBarang;
    private String kodeBarang;
    private String namaBarang;
    private String hargaFormat;
    private int harga;
    private int Stok;
    private int jumlahBarangMasuk;
    private int jumlahBarangKeluar;
    private long dateInput;

    // Default Constructor
    public Barang() {
    }
    
    // get barang
    public Barang(int idBarang, String kodeBarang, String namaBarang, int harga, int Stok, long getDate, int jmlah) {
        this.idBarang = idBarang;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.Stok = Stok;
        this.dateInput = getDate;
        this.jumlahBarangMasuk = jmlah;
    }
    
    // for updating into database
    public Barang(int idBarang, String kodeBarang, String namaBarang, int harga, int Stok, long getDate) {
        this.idBarang = idBarang;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.Stok = Stok;
        this.dateInput = getDate;
    }
    
    // for updating into database alternative
    public Barang(int idBarang, String kodeBarang, String namaBarang, int harga, int Stok, int jumlah) {
        this.idBarang = idBarang;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.Stok = Stok;
        this.jumlahBarangMasuk = jumlah;
    }
    
    // get all
    public Barang(int idBarang, String kodeBarang, String namaBarang, String harga, int Stok, long getDate, int jmlah) {
        this.idBarang = idBarang;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaFormat = harga;
        this.Stok = Stok;
        this.dateInput = getDate;
        this.jumlahBarangMasuk = jmlah;
    }
    
    public Barang(int kodeBarang){
        this.idBarang = kodeBarang;
    }
    
    // for inserting into database
    public Barang(String namaBarang, String kodeBarang, int harga, int Stok , long getDate, int jmlah) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.Stok = Stok;
        this.dateInput = getDate;
        this.jumlahBarangMasuk = jmlah;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
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

    public String getHargaFormat() {
        return hargaFormat;
    }

    public void setHargaFormat(String hargaFormat) {
        this.hargaFormat = hargaFormat;
    }

    public long getDateInput() {
        return dateInput;
    }

    public void setDateInput(long dateInput) {
        this.dateInput = dateInput;
    }

    public int getJumlahBarangMasuk() {
        return jumlahBarangMasuk;
    }

    public void setJumlahBarangMasuk(int jumlahBarangMasuk) {
        this.jumlahBarangMasuk = jumlahBarangMasuk;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }    

    public int getJumlahBarangKeluar() {
        return jumlahBarangKeluar;
    }

    public void setJumlahBarangKeluar(int jumlahBarangKeluar) {
        this.jumlahBarangKeluar = jumlahBarangKeluar;
    }
    
    
    
}
