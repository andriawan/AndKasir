/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.dao.impl;

import andriawan.kasir.dao.BarangDao;
import utilities.ConnectionManager;
import andriawan.kasir.model.Barang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.Formater;

/**
 *
 * @author andriawan
 */
public class BarangDaoImpl implements BarangDao {

    private final List<Barang> semuaBarang;
    private Connection con;
    private PreparedStatement preparedStatement;
    private static BarangDaoImpl bmpl;
    
    // info tabel barang masuk
    private static final String TABLE_MASUK = "barang_masuk";
    private static final String COLUMN_TGL_TABLE_MASUK = "tgl_masuk";
    private static final String COLUMN_JUMLAH_TABLE_MASUK = "jumlah_barang_masuk";
    
    // info tabel barang keluar
    private static final String TABLE_KELUAR = "barang_keluar";
    private static final String COLUMN_TGL_TABLE_KELUAR = "tgl_keluar";
    private static final String COLUMN_JUMLAH_TABLE_KELUAR = "jumlah_barang_keluar";

    // info table
    private static final String TABLE = "barang";
    private static final String COLUMN_ID_BARANG = "id_barang";
    private static final String COLUMN_KODE_BARANG = "kode_barang";
    private static final String COLUMN_NAMA_BARANG = "nama_barang";
    private static final String COLUMN_HARGA = "harga";
    private static final String COLUMN_STOK = "stok";
    private static final String COLUMN_TGL_INPUT = "tgl_input";
    private static final String COLUMN_JUMLAH_MASUK = "jumlah_barang_masuk";

    // FIND ALL
    private static final String FIND_ALL = 
            "SELECT * FROM " 
            + TABLE 
            + " ORDER BY "
            + COLUMN_TGL_INPUT 
            + " DESC LIMIT 50";
    
    // FIND ALL
    private static final String FIND_PAGINATION = 
            "SELECT * FROM " 
            + TABLE 
            + " LIMIT ? OFFSET ?";
    
    // COUNT ALL RECORDS
    private static final String COUNT_RECORDS = 
            "SELECT COUNT(*) FROM " 
            + TABLE;
    
    // Delete
    private static final String DELETE = 
            "DELETE FROM " 
            + TABLE + 
            " WHERE "
            + COLUMN_ID_BARANG + "=?";
    
    //FIND by id_barang
    private static final String FIND_BY_ID = 
            "SELECT * FROM " 
            + TABLE + 
            " WHERE " 
            + COLUMN_ID_BARANG + "=?";
    
    //FIND by kode_barang
    private static final String FIND_BY_KODE = 
            "SELECT * FROM " 
            + TABLE + 
            " WHERE " 
            + COLUMN_KODE_BARANG + "=?";
    
    private static final String MULTI_SEARCH = 
            "SELECT * FROM " 
            + TABLE + 
            " WHERE " 
            + COLUMN_ID_BARANG + " like ? OR "
            + COLUMN_KODE_BARANG + " like ? OR "
            + COLUMN_NAMA_BARANG + " like ? OR "
            + COLUMN_HARGA + " like ? OR "
            + COLUMN_STOK + " like ?";
    
    //FIND by nama_barang
    private static final String FIND_BY_NAME = 
            "SELECT * FROM " 
            + TABLE + 
            " WHERE " 
            + COLUMN_NAMA_BARANG + " like ?";
    
    //FIND by nama_barang and kode_barang
    private static final String FIND_BY_NAME_AND_KODE = 
            "SELECT * FROM " 
            + TABLE + 
            " WHERE " 
            + COLUMN_NAMA_BARANG + " =? AND "
            + COLUMN_KODE_BARANG + " =?";
    
    //INSERT
    private static final String INSERT = 
            "INSERT INTO " 
            + TABLE + "("
            + COLUMN_NAMA_BARANG + ", "
            + COLUMN_KODE_BARANG + ", "
            + COLUMN_HARGA + ", "
            + COLUMN_STOK + ", "
            + COLUMN_TGL_INPUT + ", "
            + COLUMN_JUMLAH_MASUK + ") VALUES(?, ?, ?, ?, ?, ?)";
    
    //UPDATE
    private static final String UPDATE = "UPDATE " + TABLE + " SET "
            + COLUMN_NAMA_BARANG + "=?, "
            + COLUMN_KODE_BARANG + "=?, "
            + COLUMN_HARGA + "=?, "
            + COLUMN_STOK + "=?, "
            + COLUMN_TGL_INPUT + "=? , "
            + COLUMN_JUMLAH_MASUK + "=? "
            + "WHERE "
            + COLUMN_ID_BARANG + "=?";
    
    //UPDATE WITHOUT DATE
    private static final String UPDATE2 = "UPDATE " + TABLE + " SET "
            + COLUMN_NAMA_BARANG + "=?, "
            + COLUMN_KODE_BARANG + "=?, "
            + COLUMN_HARGA + "=?, "
            + COLUMN_STOK + "=? , "
            + COLUMN_JUMLAH_MASUK + "=? "
            + "WHERE "
            + COLUMN_ID_BARANG + "=?";
    
    // UPDATE STOK    
    private static final String UPDATE_STOK = "UPDATE " + TABLE + " SET "
            + COLUMN_STOK + "=stok-? "
            + "WHERE "
            + COLUMN_ID_BARANG + "=?";
    
    // GET RECORD BARANG MASUK
    private static final String GET_RECORD_BARANG_MASUK = 
            "SELECT * FROM " 
            + TABLE_MASUK
            + " WHERE "
            + COLUMN_TGL_TABLE_MASUK + 
            " BETWEEN ? AND ? ORDER BY " + COLUMN_TGL_TABLE_MASUK;
    
    // INSERT BARANG MASUK
    private static final String INSERT_BARANG_MASUK = 
            "INSERT INTO " 
            + TABLE_MASUK + "("
            + COLUMN_ID_BARANG + ", "
            + COLUMN_TGL_TABLE_MASUK + ", "
            + COLUMN_JUMLAH_TABLE_MASUK + ") VALUES(?, ?, ?)";
    
    // GET JUMLAH BARANG MASUK TOTAL IN CERTAIN DATE
    private static final String GET_BARANG_MASUK
            = "SELECT SUM("
            + COLUMN_JUMLAH_TABLE_MASUK
            + ") FROM "
            + TABLE_MASUK + 
            " WHERE "
            + COLUMN_TGL_TABLE_MASUK + 
            " BETWEEN ? AND ?";
    
    // GET RECORD BARANG KELUAR
    private static final String GET_BARANG_KELUAR
           = "SELECT SUM("
            + COLUMN_JUMLAH_TABLE_KELUAR
            + ") FROM "
            + TABLE_KELUAR + 
            " WHERE "
            + COLUMN_TGL_TABLE_KELUAR + 
            " BETWEEN ? AND ?";
    
    // INSERT BARANG KELUAR
    private static final String INSERT_BARANG_KELUAR = 
            "INSERT INTO " 
            + TABLE_KELUAR + "("
            + COLUMN_ID_BARANG + ", "
            + COLUMN_TGL_TABLE_KELUAR + ", "
            + COLUMN_JUMLAH_TABLE_KELUAR + ") VALUES(?, ?, ?)";

    public BarangDaoImpl() throws SQLException {
        ResultSet result = null;
        List<Barang> barangs = new ArrayList<Barang>();

        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(FIND_ALL);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int kode = result.getInt(1);
                String kodeBarang = result.getString(2);
                String nama_barang = result.getString(3);
                int harga = result.getInt(4);
                int stok = result.getInt(5);
                long date = result.getTimestamp(6).getTime();
                int jmlahBarangMasuk = result.getInt(7);

                barangs.add(new Barang(kode, kodeBarang, nama_barang, 
                        Formater.setRupiahFormat(harga), stok, date, jmlahBarangMasuk));
            }

            this.semuaBarang = barangs;

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            this.close(con);
            close(preparedStatement);
        }

    }

    @Override
    public List<Barang> getAllBarang() {
        return semuaBarang;
    }

    @Override
    public Barang getBarang(int idBarang) {
        ResultSet result = null;
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, idBarang);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                int kode = result.getInt(1);
                String kodeBarang = result.getString(2);
                String nama_barang = result.getString(3);
                int harga = result.getInt(4);
                int stok = result.getInt(5);
                long date = result.getTimestamp(6).getTime();
                int jmlahBarangMasuk = result.getInt(7);
                Barang br = new Barang(kode, kodeBarang, nama_barang, 
                        Formater.setRupiahFormat(harga), stok, date, jmlahBarangMasuk);

                return br;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(preparedStatement);
        }
    }
    
    public Barang getBarangByName(String name) {
        ResultSet result = null;
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1, "%" + name + "%");
            result = preparedStatement.executeQuery();

            if (result.next()) {
                int kode = result.getInt(1);
                String kodeBarang = result.getString(2);
                String nama_barang = result.getString(3);
                int harga = result.getInt(4);
                int stok = result.getInt(5);
                long date = result.getTimestamp(6).getTime();
                int jmlahBarangMasuk = result.getInt(7);
                Barang br = new Barang(kode, kodeBarang, nama_barang, 
                        Formater.setRupiahFormat(harga), stok, date, jmlahBarangMasuk);

                return br;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(preparedStatement);
        }
    }
    
    public List<Barang> getBarangByKode(String name) {
        List<Barang> barangs = new ArrayList<>();
        ResultSet result = null;
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(FIND_BY_KODE);
            preparedStatement.setString(1, name);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int idBarang = result.getInt(1);
                String kodeBarang = result.getString(2);
                String nama_barang = result.getString(3);
                int harga = result.getInt(4);
                int stok = result.getInt(5);
                long date = result.getTimestamp(6).getTime();
                int jmlahBarangMasuk = result.getInt(7);
                
                barangs.add(new Barang(idBarang, kodeBarang, nama_barang, 
                        Formater.setRupiahFormat(harga), stok, date, jmlahBarangMasuk));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(preparedStatement);
        }
        
        return barangs;
    }
    
    public Barang getBarangByNameAndKode(String name, String code) {
        ResultSet result = null;
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(FIND_BY_NAME_AND_KODE);
            preparedStatement.setString(1, name );
            preparedStatement.setString(2, code );
            result = preparedStatement.executeQuery();

            if (result.next()) {
                int kode = result.getInt(1);
                String kodeBarang = result.getString(2);
                String nama_barang = result.getString(3);
                int harga = result.getInt(4);
                int stok = result.getInt(5);
                long date = result.getTimestamp(6).getTime();
                int jmlahBarangMasuk = result.getInt(7);
                Barang br = new Barang(kode, kodeBarang, nama_barang, 
                        Formater.setRupiahFormat(harga), stok, date, jmlahBarangMasuk);

                return br;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(preparedStatement);
        }
    }
    
    
    
    public List<Barang> multiSearch(String a, String b, String c, String d, String e) {
        ResultSet result = null;
        List<Barang> barangs = new ArrayList<>();
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(MULTI_SEARCH);
            preparedStatement.setString(1, "%" + a + "%");
            preparedStatement.setString(2, "%" + b + "%");
            preparedStatement.setString(3, "%" + c + "%");
            preparedStatement.setString(4, "%" + d + "%");
            preparedStatement.setString(5, "%" + d + "%");
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int kode = result.getInt(1);
                String kodeBarang = result.getString(2);
                String nama_barang = result.getString(3);
                int harga = result.getInt(4);
                int stok = result.getInt(5);
                long date = result.getTimestamp(6).getTime();
                int jmlahBarangMasuk = result.getInt(7);
                Barang brx = new Barang(kode, kodeBarang, nama_barang, 
                        Formater.setRupiahFormat(harga), stok, date, jmlahBarangMasuk);
                barangs.add(brx);
            }

        } catch (SQLException es) {
            throw new RuntimeException(es);
        } finally {
            close(con);
            close(preparedStatement);
        }
        return barangs;
    }
    

    @Override
    public void updateBarang(Barang barang) {
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(UPDATE);
            preparedStatement.setString(1, barang.getNamaBarang());
            preparedStatement.setString(2, barang.getKodeBarang().toUpperCase());
            preparedStatement.setInt(3, barang.getHarga());
            preparedStatement.setInt(4, barang.getStok());
            preparedStatement.setString(5, Formater.setStringReadySql(
                    barang.getDateInput()));
            preparedStatement.setInt(6, barang.getJumlahBarangMasuk());
            preparedStatement.setInt(7, barang.getIdBarang());
            
            int status = preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            close(con);
            close(preparedStatement);
        }

    }
    
    public void updateBarangNoDate(Barang barang) {
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(UPDATE2);
            preparedStatement.setString(1, barang.getNamaBarang());
            preparedStatement.setString(2, barang.getKodeBarang().toUpperCase());
            preparedStatement.setInt(3, barang.getHarga());
            preparedStatement.setInt(4, barang.getStok());
            preparedStatement.setInt(5, barang.getJumlahBarangMasuk());
            preparedStatement.setInt(6, barang.getIdBarang());
            
            int status = preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            close(con);
            close(preparedStatement);
        }

    }

    @Override
    public void deleteBarang(int kodebarang) {
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(DELETE);
            preparedStatement.setInt(1, kodebarang);
            
            int status = preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            close(con);
            close(preparedStatement);
        }
    }
    
    @Override
    public void insertBarang(Barang barang) {
        try {
            
            con = ConnectionManager.getDataSourceConnection();
            
            preparedStatement = con.prepareStatement(INSERT);
            preparedStatement.setString(1, barang.getNamaBarang());
            preparedStatement.setString(2, barang.getKodeBarang().toUpperCase());
            preparedStatement.setInt(3, barang.getHarga());
            preparedStatement.setInt(4, barang.getStok());
            preparedStatement.setString(5, Formater.setStringReadySql(
                    barang.getDateInput()));
            preparedStatement.setInt(6, barang.getJumlahBarangMasuk());
            
            int status = preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            close(con);
            close(preparedStatement);
        }
    }
    
    // CUSTOM CRUD
    public void updateStok(Barang barang, int jumlah) {
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(UPDATE_STOK);
            preparedStatement.setInt(1, jumlah);
            preparedStatement.setInt(2, barang.getIdBarang());
            
            int status = preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            close(con);
            close(preparedStatement);
        }

    }
    
    public Barang getJumlahBarangMasuk(String tgl1, String tgl2) {
        ResultSet result = null;
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(GET_BARANG_MASUK);
            preparedStatement.setString(1, tgl1);
            preparedStatement.setString(2, tgl2);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                int total = result.getInt(1);
                Barang br = new Barang();
                br.setJumlahBarangMasuk(total);

                return br;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(preparedStatement);
        }
    }
    
    public Integer countRecords(){
        
        ResultSet result;
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(COUNT_RECORDS);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                return result.getInt(1);
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(preparedStatement);
        }
    }
    
    public List<Barang> getBarangPagination(int Limit, int Offset){
        ResultSet result = null;
        List<Barang> barangs = new ArrayList<>();

        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(FIND_PAGINATION);
            preparedStatement.setInt(1, Limit);
            preparedStatement.setInt(2, Offset);
            
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int kode = result.getInt(1);
                String kodeBarang = result.getString(2);
                String nama_barang = result.getString(3);
                int harga = result.getInt(4);
                int stok = result.getInt(5);
                long date = result.getTimestamp(6).getTime();
                int jmlahBarangMasuk = result.getInt(7);

                barangs.add(new Barang(kode, kodeBarang, nama_barang, 
                        Formater.setRupiahFormat(harga), stok, date, jmlahBarangMasuk));
            }

            return barangs;

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            BarangDaoImpl.close(con);
            close(preparedStatement);
        }
    }
    
    public void insertBarangMasuk(int id, String date, int jumlah) {
        try {
            
            con = ConnectionManager.getDataSourceConnection();
            
            preparedStatement = con.prepareStatement(INSERT_BARANG_MASUK);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, date);
            preparedStatement.setInt(3, jumlah);
            
            int status = preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            close(con);
            close(preparedStatement);
        }
    }
    
    public Barang getJumlahBarangKeluar(String tgl1, String tgl2) {
        ResultSet result = null;
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(GET_BARANG_KELUAR);
            preparedStatement.setString(1, tgl1);
            preparedStatement.setString(2, tgl2);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                int total = result.getInt(1);
                Barang br = new Barang();
                br.setJumlahBarangKeluar(total);

                return br;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(preparedStatement);
        }
    }
    
    public void insertBarangKeluar(int id, String date, int jumlah) {
        try {
            
            con = ConnectionManager.getDataSourceConnection();
            
            preparedStatement = con.prepareStatement(INSERT_BARANG_KELUAR);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, date);
            preparedStatement.setInt(3, jumlah);
            
            int status = preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            close(con);
            close(preparedStatement);
        }
    }
    
    
    //Singleton insert Editor form
    public static BarangDaoImpl getInstance(){
        try {
            if(bmpl == null){
            bmpl = new BarangDaoImpl();
        }
            return bmpl;
            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    

    private static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException esd) {
                // e.printStackTrace();
                throw new RuntimeException(esd);
            }
        }
    }

    private static void close(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException be) {
                // e.printStackTrace();
                throw new RuntimeException(be);
            }
        }
    }

}
