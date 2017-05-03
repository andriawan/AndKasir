/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.dao.impl;

import andriawan.kasir.dao.TransaksiDao;
import andriawan.kasir.model.KasirUser;
import andriawan.kasir.model.Pelanggan;
import andriawan.kasir.model.Transaksi;
import andriawan.kasir.model.User;
import andriawan.safe.password.SafePassword;
import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andriawan
 */
public class TransaksiDaoImpl implements TransaksiDao {

    private List<Transaksi> semuaTransaksi;
    private Connection con;
    private PreparedStatement preparedStatement;

    // info table
    private static final String TABLE = "transaksi";
    private static final String COLUMN_ID_TRANSAKSI = "idtransaksi";
    private static final String COLUMN_TGL_TRANSAKSI = "tgl_transaksi";
    private static final String COLUMN_TOTAL_ITEM = "total_item";
    private static final String COLUMN_TOTAL_HARGA = "total_harga";
    private static final String COLUMN_ID_PETUGAS = "idpetugas";

    // FIND ALL
    private static final String FIND_ALL
            = "SELECT * FROM "
            + TABLE;
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

    public TransaksiDaoImpl() {
        ResultSet result = null;
        List<Transaksi> semuaTransaksi = new ArrayList<Transaksi>();

        try {
            con = ConnectionManager.getConnection();
            preparedStatement = con.prepareStatement(FIND_ALL);
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

    @Override
    public Transaksi getTransaksi(int kode) {
        
        ResultSet result = null;
        List<Transaksi> semuaTransaksi = new ArrayList<Transaksi>();

        try {
            con = ConnectionManager.getConnection();
            preparedStatement = con.prepareStatement(FIND_ALL);
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
            
            con = ConnectionManager.getConnection();
            
            preparedStatement = con.prepareStatement(INSERT);
            preparedStatement.setDate(1, new java.sql.Date(transaksi.getTglTransaksi()));
            preparedStatement.setInt(2, transaksi.getTotalItem());
            preparedStatement.setInt(3, transaksi.getTotalHarga());
            preparedStatement.setInt(4, transaksi.getKasir().getId());
            
            
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
            con = ConnectionManager.getConnection();
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