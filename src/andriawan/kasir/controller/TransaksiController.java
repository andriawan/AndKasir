/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.controller;

import andriawan.kasir.dao.impl.TransaksiDaoImpl;
import andriawan.kasir.model.DetailTransaksi;
import andriawan.kasir.model.Transaksi;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andriawan
 * 
 * kelas yang mengkontrol Transaksi
 */
public class TransaksiController {
    
    // field variable untuk keperluan penerapan singleton
    public static TransaksiController tc = new TransaksiController();
    
    // mengolah pengembalian dari objek anonim TransaksiDaoImpl agar dihandle
    // controller
    public List<Transaksi> getAllTransakasi(){
        return new TransaksiDaoImpl().getAllTransaksi();
    }
    
    public List<Transaksi> getTransaksiOneDay(){
        return new TransaksiDaoImpl().getTransaksiOneDay();
    }
    
    // mengolah pengembalian dari objek anonim TransaksiDaoImpl agar dihandle
    // controller
    public List<Transaksi> getTransakasi(int kode){
        Transaksi tr = new TransaksiDaoImpl().getTransaksi(kode);
        List<Transaksi> ls = new ArrayList<>();
        ls.add(tr);
        return ls;
    }
    
    // mengolah pengembalian dari objek anonim TransaksiDaoImpl agar dihandle
    // controller
    public List<DetailTransaksi> getDetailTransakasi(int kode){
        return new TransaksiDaoImpl().getDetailTransaksi(kode);
    }
    
    
    public void insertTransaksi(Transaksi transaksi){
        new TransaksiDaoImpl().insertTransaksi(transaksi);
    }
    
    public void insertTransaksiDetail(DetailTransaksi detailTransaksi){
        new TransaksiDaoImpl().insertDetailTransaksi(detailTransaksi);
    }
    
    // mengolah pengembalian dari objek anonim TransaksiDaoImpl agar dihandle
    // controller
    public Transaksi getLastRecord(){
        return new TransaksiDaoImpl().getLastRecord();
    }
    
    // Singleton untuk penghematan memori
    public static TransaksiController getInstanceTransaksiController(){
        
        try {
            if (tc == null) {
                tc = new TransaksiController();
            }
            return tc;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    
    
}
