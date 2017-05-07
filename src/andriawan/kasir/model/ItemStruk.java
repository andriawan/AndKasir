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
public class ItemStruk {
    private String namaBarang;
    private String jumlah;
    private String harga;
    private String totalPerItem;

    public ItemStruk(String namaBarang, String jumlah, String harga, String totalPerItem) {
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.harga = harga;
        this.totalPerItem = totalPerItem;
    }

    public String getTotalPerItem() {
        return totalPerItem;
    }

    public void setTotalPerItem(String totalPerItem) {
        this.totalPerItem = totalPerItem;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
