/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.dao.impl;

import andriawan.kasir.dao.TransaksiDao;
import andriawan.kasir.model.DetailTransaksi;
import andriawan.kasir.model.KasirUser;
import andriawan.kasir.model.Transaksi;
import utilities.Formater;
import utilities.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andriawan
 */
public class TransaksiDaoImpl implements TransaksiDao {
    
    // TODO : pagination query untuk view pada MainForm
    private List<Transaksi> semuaTransaksi;
    private Connection con;
    private PreparedStatement preparedStatement;

    // info table
    private static final String TABLE = "transaksi";
    private static final String TABLE2 = "detail_transaksi";
    
    //TABLE
    private static final String COLUMN_ID_TRANSAKSI = "id_transaksi";
    private static final String COLUMN_TGL_TRANSAKSI = "tgl_transaksi";
    private static final String COLUMN_TOTAL_ITEM = "total_item";
    private static final String COLUMN_TOTAL_HARGA = "total_harga";
    private static final String COLUMN_ID_PETUGAS = "id_petugas";
    
    //TABLE2
    private static final String COLUMN_ID_DETAIL = "id_detail";
    private static final String COLUMN_ID_TRANSAKSI_DETAIL = "id_transaksi";
    private static final String COLUMN_BARANG = "id_barang";
    private static final String COLUMN_JUMLAH = "jumlah";
    private static final String COLUMN_HARGA = "harga";
    
    //INSERT DETAIL TRANSAKSI
    private static final String INSERT_DETAIL
            = "INSERT INTO "
            + TABLE2 + "("
            + COLUMN_ID_TRANSAKSI_DETAIL + ", "
            + COLUMN_BARANG + ", "
            + COLUMN_JUMLAH + ", "
            + COLUMN_HARGA + ", "
            + COLUMN_ID_PETUGAS + ") VALUES(?, ?, ?, ?, ?)";
    
    //FIND_DETAIL by kode_Transaksi JOIN with Tabel Barang
    private static final String FIND_DETAIL_BY_ID
            = "SELECT "
            + "detail_transaksi.id_transaksi, "
            + "detail_transaksi.id_barang, "
            + "barang.nama_barang,"
            + "detail_transaksi.harga,"
            + "detail_transaksi.jumlah "
            + "FROM "
            + "detail_transaksi "
            + "INNER JOIN "
            + "barang "
            + "ON "
            + "barang.id_barang=detail_transaksi.id_barang "
            + "WHERE "
            + "detail_transaksi.id_transaksi=?";

    // FIND ALL TRANSAKSI IN 1 DAY
     private static String FIND_1_DAY
            = "SELECT * FROM "
            + TABLE + " WHERE "
            + COLUMN_TGL_TRANSAKSI + " BETWEEN ? AND ? ";
   
    
    private static String FIND_ALL
            = "SELECT * FROM "
            + TABLE + " WHERE "
            + COLUMN_TGL_TRANSAKSI + " BETWEEN '"
            + Formater.setStringReadySql(
                    System.currentTimeMillis()- TimeUnit.DAYS.toMillis(1))
            + "' AND '"
            + Formater.setStringReadySql(
                    System.currentTimeMillis())
            + "'";
    // Delete
    private static final String DELETE
            = "DELETE FROM "
            + TABLE
            + " WHERE "
            + COLUMN_ID_TRANSAKSI + "=?";

    //FIND by kode_barang
    private static final String FIND_BY_ID
            = "SELECT * FROM "
            + TABLE
            + " WHERE "
            + COLUMN_ID_TRANSAKSI + "=?";

    //FIND by nama_barang
    private static final String FIND_BY_TGL
            = "SELECT * FROM "
            + TABLE
            + " WHERE "
            + COLUMN_TGL_TRANSAKSI + "=?";
    //INSERT
    private static final String INSERT
            = "INSERT INTO "
            + TABLE + "("
            + COLUMN_TGL_TRANSAKSI + ", "
            + COLUMN_TOTAL_ITEM + ", "
            + COLUMN_TOTAL_HARGA + ", "
            + COLUMN_ID_PETUGAS + ") VALUES(?, ?, ?, ?)";
    //UPDATE
    private static final String UPDATE = "UPDATE " + TABLE + " SET "
            + COLUMN_TGL_TRANSAKSI + "=?, "
            + COLUMN_TOTAL_ITEM + "=?, "
            + COLUMN_TOTAL_HARGA + "=?, "
            + COLUMN_ID_PETUGAS + "=? "
            + "WHERE "
            + COLUMN_ID_TRANSAKSI + "=?";
    
    private static final String GET_LAST_RECORD
            = "SELECT * FROM "
            + TABLE + " ORDER BY "
            + COLUMN_ID_TRANSAKSI + " DESC LIMIT 1";
    

    public TransaksiDaoImpl() {
        ResultSet result = null;
        List<Transaksi> semuaTransaksi = new ArrayList<>();

        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(FIND_ALL);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int idTransaksi = result.getInt(1);
                long tglTransaksi = result.getTimestamp(2).getTime();
                int totalItem = result.getInt(3);
                int totalHarga = result.getInt(4);
                int idPetugas = result.getInt(5);

                semuaTransaksi.add(new Transaksi(idTransaksi, totalItem, totalHarga, tglTransaksi, idPetugas));
            }

            this.semuaTransaksi = semuaTransaksi;

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            this.close(con);
            close(preparedStatement);
        }

    }

    @Override
    public List<Transaksi> getAllTransaksi() {
        return semuaTransaksi;
    }
    
    public List<DetailTransaksi> getDetailTransaksi(int kode) {
        
        ResultSet result = null;
        
        List<DetailTransaksi> semuaTransaksi = new ArrayList<>();

        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(FIND_DETAIL_BY_ID);
            preparedStatement.setInt(1, kode);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int idTransaksi = result.getInt(1);
                int idBarang = result.getInt(2);
                String namaBarang = result.getString(3);
                int harga = result.getInt(4);
                int jumlah = result.getInt(5);
                
                semuaTransaksi.add(
                        new DetailTransaksi(idTransaksi, idBarang, namaBarang, jumlah, harga));
            }
            
            return semuaTransaksi;

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            this.close(con);
            close(preparedStatement);
        }
    }
    
    public List<Transaksi> getTransaksiOneDay() {
        
        ResultSet result = null;
        
        List<Transaksi> transaksiInADay = new ArrayList<>();

        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(FIND_1_DAY);
            preparedStatement.setString(1, Formater.setStringReadySql(
                    System.currentTimeMillis()- TimeUnit.DAYS.toMillis(1)));
            preparedStatement.setString(2, Formater.setStringReadySql(
                    System.currentTimeMillis()));
            
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int idTransaksi = result.getInt(1);
                long date = result.getTimestamp(2).getTime();
                int totalItem = result.getInt(3);
                int totalHarga = result.getInt(4);
                int idPetugas = result.getInt(5);
                
                
                KasirUser ku = new KasirUser();
                ku.setId(idPetugas);
                
                transaksiInADay.add(
                        new Transaksi(idTransaksi, totalItem, totalHarga, 
                                date, ku.getId()));
            }
            
            return transaksiInADay;

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            this.close(con);
            close(preparedStatement);
        }
    }
    
    @Override
    public Transaksi getTransaksi(int kode) {
        
        ResultSet result = null;

        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, kode);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                int idTransaksi = result.getInt(1);
                long date = result.getTimestamp(2).getTime();
                int totalItem = result.getInt(3);
                int totalHarga = result.getInt(4);
                int idPetugas = result.getInt(5);
                
                
                KasirUser ku = new KasirUser();
                ku.setId(idPetugas);
                
                Transaksi tr = new Transaksi(idTransaksi, totalItem, totalHarga, date, ku.getId() );
                
                return tr;
                
            } else{
                return null;
            }

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            this.close(con);
            close(preparedStatement);
        }
    }
    
    

    @Override
    public void updateTransaksi(Transaksi transaksi, int id) {
        // TO DO
    }

    @Override
    public void deleteTransaksi(int kodeTransaksi) {
        // TO DO
    }

    @Override
    public void insertTransaksi(Transaksi transaksi) {
        try {
            
            con = ConnectionManager.getDataSourceConnection();
            
            preparedStatement = con.prepareStatement(INSERT);
            preparedStatement.setString(1, Formater.setStringReadySql(transaksi.getTglTransaksi()));
            preparedStatement.setInt(2, transaksi.getTotalItem());
            preparedStatement.setInt(3, transaksi.getTotalHarga());
            preparedStatement.setInt(4, transaksi.getIdKasir());
            
            
            int status = preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            close(con);
            close(preparedStatement);
        }
    }
    
    
    // CUSTOM CRUD
  
    public List<Transaksi> getAllTransaksiForTabel() {
        ResultSet result = null;
        List<Transaksi> semuaTransaksi = new ArrayList<Transaksi>();

        try {
            con = ConnectionManager.getDataSourceConnection();
            String query = "select * from pembelian inner join userapp on pembelian.kode_user=userapp.kode_user;";
            preparedStatement = con.prepareStatement(query);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int idTransaksi = result.getInt(1);
                long tglTransaksi = result.getTimestamp(2).getTime();
                int totalItem = result.getInt(3);
                int totalHarga = result.getInt(4);
                int idPetugas = result.getInt(5);
                
                KasirUser ku = new KasirUser();
                ku.setId(idPetugas);

                semuaTransaksi.add(new Transaksi(idTransaksi, totalItem, totalHarga, tglTransaksi, ku));
            }

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            this.close(con);
            close(preparedStatement);
        }
        return semuaTransaksi;
    }
    
    public Transaksi getLastRecord() {
        
        ResultSet result = null;

        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(GET_LAST_RECORD);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                int idTransaksi = result.getInt(1);
                long date = result.getDate(2).getTime();
                int totalItem = result.getInt(3);
                int totalHarga = result.getInt(4);
                int idPetugas = result.getInt(5);
                
                
                KasirUser ku = new KasirUser();
                ku.setId(idPetugas);
                
                Transaksi tr = new Transaksi(idTransaksi, totalItem, totalHarga, date, ku );
                
                return tr;
                
            } else{
                return null;
            }

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            TransaksiDaoImpl.close(con);
            close(preparedStatement);
        }
    }
    
    public void insertDetailTransaksi(DetailTransaksi detailTransaksi) {
        try {
            
            con = ConnectionManager.getDataSourceConnection();
            
            preparedStatement = con.prepareStatement(INSERT_DETAIL);
            preparedStatement.setInt(1, detailTransaksi.getId_transaksi());
            preparedStatement.setInt(2, detailTransaksi.getId_barang());
            preparedStatement.setInt(3, detailTransaksi.getJumlah());
            preparedStatement.setInt(4, detailTransaksi.getHarga());
            preparedStatement.setInt(5, detailTransaksi.getId_petugas());
            
            
            int status = preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
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