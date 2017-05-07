/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andriawan
 */
public class Struk {
    
    private String idTransaksi;
    private List<ItemStruk> listItemStruk= new ArrayList<>();
    private String totalHarga;
    private String totalBayar;
    private String totalKembalian;
    private String petugasKasir;
    private String tanggal;

    public Struk(String idTransaksi, String totalHarga, String totalBayar, 
            String totalKembalian, List<ItemStruk> is, 
            String petugas, String tanggal) {
        this.idTransaksi = idTransaksi;
        this.totalHarga = totalHarga;
        this.totalBayar = totalBayar;
        this.totalKembalian = totalKembalian;
        this.listItemStruk = is;
        this.petugasKasir = petugas;
        this.tanggal = tanggal;
    }

    public String getPetugasKasir() {
        return petugasKasir;
    }

    public void setPetugasKasir(String petugasKasir) {
        this.petugasKasir = petugasKasir;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public List<ItemStruk> getListItemStruk() {
        return listItemStruk;
    }

    public void setListItemStruk(List<ItemStruk> listItemStruk) {
        this.listItemStruk = listItemStruk;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(String totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getTotalKembalian() {
        return totalKembalian;
    }

    public void setTotalKembalian(String totalKembalian) {
        this.totalKembalian = totalKembalian;
    }
    
    
    
    
}
