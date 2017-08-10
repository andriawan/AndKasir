/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.controller;

import andriawan.kasir.dao.impl.BarangDaoImpl;
import andriawan.kasir.model.Barang;
import andriawan.kasir.view.EditorBarangForm;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author andriawan
 */
public class BarangController {
    
    // Field Variable EditorBarangForm (JFrame)
    private static EditorBarangForm insert = new EditorBarangForm();
    
    // Field Variable EditorBarangForm (JFrame)
    private static EditorBarangForm update = new EditorBarangForm();
    
    // Field Variable singleton
    private static BarangController br = new BarangController();

    public BarangController() {
    }
    
    // mengambil fungsi pada BarangDaoImpl untuk diolah lebih lanjut pada 
    // view objcet (JFrame MainForm)
    public List<Barang> getAllBarang() throws SQLException{
        return new BarangDaoImpl().getAllBarang();
    }
    
    // mengambil fungsi pada BarangDaoImpl untuk diolah lebih lanjut pada 
    // view object (JFrame MainForm)
    public Barang getBarang(int kodeBarang) throws SQLException{
        return new BarangDaoImpl().getBarang(kodeBarang);
    }
    
    // mengambil fungsi pada BarangDaoImpl untuk diolah lebih lanjut pada 
    // view object (JFrame MainForm)
    public Barang getBarangByName(String name) throws SQLException{
        return new BarangDaoImpl().getBarangByName(name);
    }
    
    // mengambil fungsi pada BarangDaoImpl untuk diolah lebih lanjut pada 
    // view object (JFrame MainForm)
    public List<Barang> getBarangByKode(String kode) throws SQLException{
        return new BarangDaoImpl().getBarangByKode(kode);
    }
    
    // mengambil fungsi pada BarangDaoImpl untuk diolah lebih lanjut pada 
    // view object (JFrame MainForm)
    public List<Barang> multiSearch(String a, String b, String c, 
            String d, String e) throws SQLException{
        return new BarangDaoImpl().multiSearch(a, b, c, d, e);
    }
    
    //Singleton insert Editor form
    public static EditorBarangForm getInsertFormInstance(){
        
        try {
            if(insert == null){
            insert = new EditorBarangForm();
        }
            return insert;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    //Singleton insert Editor form
    public static EditorBarangForm getUpdateFormInstance(){
        try {
            if(update == null){
            update = new EditorBarangForm();
        }
            return update;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static BarangController getInstance(){
        try {
            if(br == null){
            br = new BarangController();
        }
            return br;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    // mengambil fungsi pada BarangDaoImpl untuk diolah lebih lanjut pada 
    // view objcet (JFrame MainForm)
    public void insertBarang(Barang br) throws SQLException{
        new BarangDaoImpl().insertBarang(br);
    }
    
    // mengambil fungsi pada BarangDaoImpl untuk diolah lebih lanjut pada 
    // view objcet (JFrame MainForm)
    public void updateBarang(Barang br) throws SQLException{
        new BarangDaoImpl().updateBarang(br);
    }
    
    // mengambil fungsi pada BarangDaoImpl untuk diolah lebih lanjut pada 
    // view objcet (JFrame MainForm) tanpa date
    public void updateBarangNoDate(Barang br) throws SQLException{
        new BarangDaoImpl().updateBarangNoDate(br);
    }
    
    // mengambil fungsi pada BarangDaoImpl untuk diolah lebih lanjut pada 
    // view objcet (JFrame MainForm)
    public void updateStok(Barang br, int jumlah) throws SQLException{
        new BarangDaoImpl().updateStok(br,jumlah);
    }
    
    // mengambil fungsi pada BarangDaoImpl untuk diolah lebih lanjut pada 
    // view objcet (JFrame MainForm)
    public void hapusBarang(int kode) throws SQLException{
        new BarangDaoImpl().deleteBarang(kode);
    }
    
    // mengambil total barang masuk dalam rentang tanggal tertentu
    public Barang getJumlahBarangMasuk(String tgl1, String tgl2) throws SQLException{
        return new BarangDaoImpl().getJumlahBarangMasuk(tgl1, tgl2);
    }
    
    
}