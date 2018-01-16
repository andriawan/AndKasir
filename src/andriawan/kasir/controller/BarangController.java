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
    
    // mengambil fungsi pada BarangDaoImpl untuk diolah lebih lanjut pada 
    // view objcet (JFrame MainForm)

    /**
     *
     * Mengambil semua record barang, tidak disarankan untuk digunakan secara langsung.
     * gunakan untuk keperluan testing. Sebaikanya gunakan fungsi getBarangPagination()
     * untuk menghemat resource
     * @return List of Barang Objects
     * @throws SQLException
     */
    public List<Barang> getAllBarang() throws SQLException{
        return new BarangDaoImpl().getAllBarang();
    }

    /**
     *
     * Mengambil record dari database barang melalui Barang Data Access Object class
     * sesuai dengan parameter ID barang
     * @param kodeBarang
     * @return single Barang Object
     * @throws SQLException
     */
    public Barang getBarang(int kodeBarang) throws SQLException{
        return new BarangDaoImpl().getBarang(kodeBarang);
    }
    
    /**
     *
     * Mengambil record dari database barang melalui Barang Data Access Object class
     * sesuai dengan Nama Barang
     * @param name
     * @return single Barang Object
     * @throws SQLException
     */
    public Barang getBarangByName(String name) throws SQLException{
        return new BarangDaoImpl().getBarangByName(name);
    }

    /**
     *
     * Mengambil record dari database barang melalui Barang Data Access Object class
     * sesuai dengan Kode barang
     * @param kode
     * @return List of Barang Objects
     * @throws SQLException
     */
    public List<Barang> getBarangByKode(String kode) throws SQLException{
        return new BarangDaoImpl().getBarangByKode(kode);
    }
    
    /**
     *
     * @param name
     * @param kode
     * @return single Barang Object
     * @throws SQLException
     */
    public Barang getBarangByNameAndKode(String name, String kode) throws SQLException{
        return new BarangDaoImpl().getBarangByNameAndKode(name, kode);
    }
    
    /**
     *
     * @param limit
     * @param offset
     * @return List of Barang Objects
     */
    public List<Barang> getBarangPagination(int limit, int offset){
        return BarangDaoImpl.getInstance().getBarangPagination(limit, offset);
    }
    
    /**
     *
     * Mengembalikan jumlah record dari Query Database Barang
     * @return integer records of Barang
     */
    public Integer countRecords(){
        return BarangDaoImpl.getInstance().countRecords();
    }
    
    /**
     *
     * fungsi ini akan mengembalikan List dari Object dengan banyak parameter untuk mencari
     * keseluruhan kemungkinan dari setiap kolom dari database, tidak disarankan digunakan
     * secara langsung karena akan performa query yang kurang bagus
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @return
     * @throws SQLException
     */
    public List<Barang> multiSearch(String a, String b, String c, 
            String d, String e) throws SQLException{
        return new BarangDaoImpl().multiSearch(a, b, c, d, e);
    }

    /**
     *
     * Pembuatan objek EditorBarangForm  dengan teknik singleton
     * untuk penggunaan resource secara efektif
     * @return object dari EditorBarangForm menggunakan teknik singleton
     */
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

    /**
     *
     * Pembuatan objek EditorBarangForm  dengan teknik singleton
     * untuk penggunaan resource secara efektif
     * @return object dari EditorBarangForm menggunakan teknik singleton
     */
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
    
    /**
     *
     * Pembuatan objek BarangController dengan teknik singleton
     * untuk penggunaan resource secara efektif
     * @return object dari BarangController menggunakan teknik singleton
     */
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

    /**
     *
     * Insert barang ke database barang
     * @param br adalah object dari kelas Barang
     * @throws SQLException
     */
    public void insertBarang(Barang br) throws SQLException{
        new BarangDaoImpl().insertBarang(br);
    }
    
    /**
     *
     * update barang ke database barang
     * @param br adalah object dari kelas Barang
     * @throws SQLException
     */
    public void updateBarang(Barang br) throws SQLException{
        new BarangDaoImpl().updateBarang(br);
    }
    
    /**
     *update barang ke database barang tanpa mengupdate kolom tanggal
     * @param br adalah object dari kelas Barang
     * @throws SQLException
     */
    public void updateBarangNoDate(Barang br) throws SQLException{
        new BarangDaoImpl().updateBarangNoDate(br);
    }
    
    /**
     *
     * @param br adalah object dari kelas Barang
     * @param jumlah adalah jumlah dari stok barang yang akan diupdate
     * @throws SQLException
     */
    public void updateStok(Barang br, int jumlah) throws SQLException{
        new BarangDaoImpl().updateStok(br,jumlah);
    }
    
    /**
     *
     * menghapus barang berdasarkan ID barang (primary key)
     * @param kode
     * @throws SQLException
     */
    public void hapusBarang(int kode) throws SQLException{
        new BarangDaoImpl().deleteBarang(kode);
    }
    
    /**
     *
     * mendapatkan jumlah barang masuk dalam rentang waktu tertentu
     * 
     * @param tgl1 tanggal mulai (tanggal sekarang)
     * @param tgl2 hingga tanggal (tanggal lalu)
     * @return single Object of Barang
     * @throws SQLException
     */
    public Barang getJumlahBarangMasuk(String tgl1, String tgl2) throws SQLException{
        return new BarangDaoImpl().getJumlahBarangMasuk(tgl1, tgl2);
    }
    
    /**
     *
     * memasukan jumlah barang masuk ke database barang_masuk berdasarkan id dari barang serta 
     * tanggal barang masuk diinput
     * 
     * @param id id bareang
     * @param date tanggal barang masuk
     * @param jumlah
     * @throws SQLException
     */
    public void insertBarangMasuk(int id, String date, int jumlah) throws SQLException{
        new BarangDaoImpl().insertBarangMasuk(id, date, jumlah);
    }

    /**
     *
     * mendapatkan jumlah barang keluar dalam rentang waktu tertentu
     * 
     * @param tgl1 tanggal mulai (tanggal sekarang)
     * @param tgl2 hingga tanggal (tanggal lalu)
     * @return
     * @throws SQLException
     */
    public Barang getJumlahBarangKeluar(String tgl1, String tgl2) throws SQLException{
        return new BarangDaoImpl().getJumlahBarangKeluar(tgl1, tgl2);
    }
    
    /**
     *
     * memasukan jumlah barang keluar ke database barang_keluar berdasarkan id dari barang serta 
     * tanggal barang masuk diinput
     * 
     * @param id ID barang
     * @param date tanggal keluar
     * @param jumlah
     * @throws SQLException
     */
    public void insertBarangKeluar(int id, String date, int jumlah) throws SQLException{
        new BarangDaoImpl().insertBarangKeluar(id, date, jumlah);
    }
}