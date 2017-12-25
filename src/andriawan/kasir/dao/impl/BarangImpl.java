/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.dao.impl;

import andriawan.kasir.dao.DatabaseDao;
import java.util.List;
import andriawan.kasir.model.database.Barang;
import utilities.ConnectionManager;
import utilities.Formater;
/**
 *
 * @author andriawan
 */
public class BarangImpl implements DatabaseDao{
    
    private static BarangImpl barangImpl = new BarangImpl();
    
    public BarangImpl() {
        ConnectionManager.javaLiteConnect();
    }
        
    private final String TABLE_COLUMN[] = {
            "kode_barang","nama_barang","harga","stok","tgl_input",
            "jumlah_barang_masuk"  
        };
    
    @Override
    public List<Barang> getAll() {
        List<Barang> list = Barang.findAll();
        return list;
    }

    @Override
    public Barang getById(String obj) {
        Barang br = Barang.findFirst("id_barang = ?", obj);
        return br;
    }

    @Override
    public void update(Object... obj) {
        Barang br = Barang.getInstance();
        // kode barang
        br.set(TABLE_COLUMN[0], obj[0].toString());
        // nama_barang
        br.set(TABLE_COLUMN[1], br.getString(obj[1].toString()));
        // harga
        br.set(TABLE_COLUMN[2], br.getInteger(obj[2].toString()));
        // stok
        br.set(TABLE_COLUMN[3], br.getInteger(obj[3].toString()));
        // tgl_input
        br.set(TABLE_COLUMN[4], Formater.setStringReadySql(
                new Long (obj[4].toString()))
        );
        // jumlah_barang_masuk
        br.set(TABLE_COLUMN[5], br.getInteger(obj[5].toString()));
        
    }

    @Override
    public void delete(Object obj) {
        Barang br = Barang.findFirst("id_barang = ?", obj.toString());
        br.delete();
    }

    @Override
    public void insert(Object... obj) {
        Barang br = Barang.getInstance();
        // kode barang
        br.set(TABLE_COLUMN[0], obj[0].toString());
        // nama_barang
        br.set(TABLE_COLUMN[1], br.getString(obj[1].toString()));
        // harga
        br.set(TABLE_COLUMN[2], br.getInteger(obj[2].toString()));
        // stok
        br.set(TABLE_COLUMN[3], br.getInteger(obj[3].toString()));
        // tgl_input
        br.set(TABLE_COLUMN[4], Formater.setStringReadySql(
                new Long (obj[4].toString()))
        );
        // jumlah_barang_masuk
        br.set(TABLE_COLUMN[5], br.getInteger(obj[5].toString()));
    }

    @Override
    public Long countBy(String query, String param) {
        return Barang.count(query, param);
    }

    @Override
    public Long countRecord() {
        return Barang.count();
    }
    
    //Singleton BarangImp
    public static BarangImpl getInstance(){
        try {
            if(barangImpl == null){
            barangImpl = new BarangImpl();
        }
            return barangImpl;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
}