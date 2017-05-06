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
    
    private static EditorBarangForm insert = new EditorBarangForm();
    private static EditorBarangForm update = new EditorBarangForm();

    public BarangController() {
    }
    
    public List<Barang> getAllBarang() throws SQLException{
        return new BarangDaoImpl().getAllBarang();
    }
    
    public Barang getBarang(int kodeBarang) throws SQLException{
        return new BarangDaoImpl().getBarang(kodeBarang);
    }
    
    public List<Barang> multiSearch(String a, String b, String c, String d) throws SQLException{
        return new BarangDaoImpl().multiSearch(a, b, c, d);
    }
    
    //Singleton insert Editor form
    public static EditorBarangForm getInsertFormInstance(){
        return insert;
    }
    
    //Singleton insert Editor form
    public static EditorBarangForm getUpdateFormInstance(){
        return update;
    }
    
    public void insertBarang(Barang br) throws SQLException{
        new BarangDaoImpl().insertBarang(br);
    }
    
    public void updateBarang(Barang br) throws SQLException{
        new BarangDaoImpl().updateBarang(br);
    }
    
    public void updateStok(Barang br) throws SQLException{
        new BarangDaoImpl().updateStok(br);
    }
    
    public void hapusBarang(int kode) throws SQLException{
        new BarangDaoImpl().deleteBarang(kode);
    }
    
    
}
