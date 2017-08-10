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

    // info table
    private static final String TABLE = "barang";
    private static final String COLUMN_ID_BARANG = "id_barang";
    private static final String COLUMN_KODE_BARANG = "kode_barang";
    private static final String COLUMN_NAMA_BARANG = "nama_barang";
    private static final String COLUMN_HARGA = "harga";
    private static final String COLUMN_STOK = "stok";
    private static final String COLUMN_TGL_INPUT = "tgl_input";
    private static final String COLUMN_JUMLAH_MASUK = "jumlah_barang_masuk";
    
    // GET JUMLAH BARANG MASUK TOTAL IN CERTAIN DATE
    private static final String GET_BARANG_MASUK
            = "SELECT SUM("
            + COLUMN_JUMLAH_MASUK
            + ") FROM "
            + TABLE + 
            " WHERE "
            + COLUMN_TGL_INPUT + 
            " BETWEEN ? AND ?";

    // FIND ALL
    private static final String FIND_ALL = 
            "SELECT * FROM " 
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
            + COLUMN_TGL_INPUT + "=? "
            + "WHERE "
            + COLUMN_ID_BARANG + "=?";
    
    private static final String UPDATE_STOK = "UPDATE " + TABLE + " SET "
            + COLUMN_STOK + "=stok-? "
            + "WHERE "
            + COLUMN_ID_BARANG + "=?";

    public BarangDaoImpl() throws SQLException {
        ResultSet result = null;
        List<Barang> barangs = new ArrayList<Barang>();

        try {
            con = ConnectionManager.getConnection();
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
            con = ConnectionManager.getConnection();
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
            con = ConnectionManager.getConnection();
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
            con = ConnectionManager.getConnection();
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
    
    
    
    public List<Barang> multiSearch(String a, String b, String c, String d, String e) {
        ResultSet result = null;
        List<Barang> barangs = new ArrayList<>();
        try {
            con = ConnectionManager.getConnection();
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
            con = ConnectionManager.getConnection();
            preparedStatement = con.prepareStatement(UPDATE);
            preparedStatement.setString(1, barang.getNamaBarang());
            preparedStatement.setString(2, barang.getKodeBarang().toUpperCase());
            preparedStatement.setInt(3, barang.getHarga());
            preparedStatement.setInt(4, barang.getStok());
            preparedStatement.setString(5, Formater.setStringReadySql(
                    barang.getDateInput()));
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
            con = ConnectionManager.getConnection();
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
            
            con = ConnectionManager.getConnection();
            
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
            con = ConnectionManager.getConnection();
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
            con = ConnectionManager.getConnection();
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
