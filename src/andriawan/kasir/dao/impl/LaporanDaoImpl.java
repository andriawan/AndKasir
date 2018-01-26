/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.dao.impl;

import andriawan.kasir.dao.LaporanDao;
import andriawan.kasir.model.Laporan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilities.ConnectionManager;

/**
 *
 * @author andriawan
 */
public class LaporanDaoImpl implements LaporanDao{
    
    private Connection con;
    private PreparedStatement preparedStatement;
    
    // info table (view mode) check sql view definition
    private static final String TABLE = "full_transaksi";
    
    //TABLE 10 KOLOM
    private static final String COLUMN_ID_TRANSAKSI = "id_transaksi";
    private static final String COLUMN_TGL_TRANSAKSI = "tgl_transaksi";
    private static final String COLUMN_ID_PETUGAS = "id_petugas";
    private static final String COLUMN_ID_BARANG = "kode_barang";
    private static final String COLUMN_NAMA_BARANG = "nama_barang";
    private static final String COLUMN_HARGA = "harga";
    private static final String COLUMN_JUMLAH = "jumlah";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_NAMA_LENGKAP = "nama_asli";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_BARANG_MASUK = "";
    
    private static final String GET_REPORT_PER_DAY
            = "SELECT * "
            + "FROM "
            + TABLE + 
            " WHERE "
            + COLUMN_TGL_TRANSAKSI + 
            " BETWEEN "
            + "DATE_FORMAT("
            + COLUMN_TGL_TRANSAKSI + 
            ",\"%d-%m-%Y\") = ? ";
    
    private static final String GET_REPORT
            = "SELECT * "
            + "FROM "
            + TABLE + 
            " WHERE "
            + COLUMN_TGL_TRANSAKSI + 
            " BETWEEN ? AND ? ORDER BY " + COLUMN_TGL_TRANSAKSI;
    
    private static final String GET_REPORT_PER_MONTH
            = "SELECT * "
            + "FROM "
            + TABLE + 
            " WHERE "
            + "DATE_FORMAT("
            + COLUMN_TGL_TRANSAKSI + 
            ",\"%m-%Y\") = ? ";
    
    private static final String GET_REPORT_PER_YEAR
            = "SELECT * "
            + "FROM "
            + TABLE + 
            " WHERE "
            + "DATE_FORMAT("
            + COLUMN_TGL_TRANSAKSI + 
            ",\"%Y\") = ? ";
    
    @Override
    public List<Laporan> getDailyReport(String tgl) {
        ResultSet result = null;
        List<Laporan> dailyReport = new ArrayList<>();

        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(GET_REPORT_PER_DAY);
            preparedStatement.setString(1, tgl);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int idTransaksi = result.getInt(1);
                long tglTransaksi = result.getTimestamp(2).getTime();
                int idPetugas = result.getInt(3);
                int kodeBarang = result.getInt(4);
                String namaBarang = result.getString(5);
                int harga = result.getInt(6);
                int jumlah = result.getInt(7);
                String username = result.getString(8);
                String namaAsli = result.getString(9);
                String status = result.getString(10);

                dailyReport.add(new Laporan(idTransaksi, idPetugas, kodeBarang, harga, jumlah, namaBarang, username, namaAsli, status, tglTransaksi));
            }

            return dailyReport;

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            LaporanDaoImpl.close(con);
            close(preparedStatement);
        }
    }

    
    public List<Laporan> getReport(String tgl, String tgl2) {
        ResultSet result = null;
        List<Laporan> report = new ArrayList<>();

        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(GET_REPORT);
            preparedStatement.setString(1, tgl);
            preparedStatement.setString(2, tgl2);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int idTransaksi = result.getInt(1);
                long tglTransaksi = result.getTimestamp(2).getTime();
                int idPetugas = result.getInt(3);
                int kodeBarang = result.getInt(4);
                String namaBarang = result.getString(5);
                int harga = result.getInt(6);
                int jumlah = result.getInt(7);
                String username = result.getString(8);
                String namaAsli = result.getString(9);
                String status = result.getString(10);

                report.add(new Laporan(idTransaksi, idPetugas, kodeBarang, harga, jumlah, namaBarang, username, namaAsli, status, tglTransaksi));
            }

            return report;

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            LaporanDaoImpl.close(con);
            close(preparedStatement);
        }
    }

    @Override
    public List<Laporan> getMonthlyReport(String tgl) {
        ResultSet result = null;
        List<Laporan> monthlyReport = new ArrayList<>();

        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(GET_REPORT_PER_MONTH);
            preparedStatement.setString(1, tgl);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int idTransaksi = result.getInt(1);
                long tglTransaksi = result.getTimestamp(2).getTime();
                int idPetugas = result.getInt(3);
                int kodeBarang = result.getInt(4);
                String namaBarang = result.getString(5);
                int harga = result.getInt(6);
                int jumlah = result.getInt(7);
                String username = result.getString(8);
                String namaAsli = result.getString(9);
                String status = result.getString(10);

                monthlyReport.add(new Laporan(idTransaksi, idPetugas, kodeBarang, harga, jumlah, namaBarang, username, namaAsli, status, tglTransaksi));
            }

            return monthlyReport;

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            LaporanDaoImpl.close(con);
            close(preparedStatement);
        }
    }

    @Override
    public List<Laporan> getYearlyReport(String tgl) {
        ResultSet result = null;
        List<Laporan> yearlyReport = new ArrayList<>();

        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(GET_REPORT_PER_YEAR);
            preparedStatement.setString(1, tgl);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int idTransaksi = result.getInt(1);
                long tglTransaksi = result.getTimestamp(2).getTime();
                int idPetugas = result.getInt(3);
                int kodeBarang = result.getInt(4);
                String namaBarang = result.getString(5);
                int harga = result.getInt(6);
                int jumlah = result.getInt(7);
                String username = result.getString(8);
                String namaAsli = result.getString(9);
                String status = result.getString(10);

                yearlyReport.add(new Laporan(idTransaksi, idPetugas, kodeBarang, harga, jumlah, namaBarang, username, namaAsli, status, tglTransaksi));
            }

            return yearlyReport;

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            LaporanDaoImpl.close(con);
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
