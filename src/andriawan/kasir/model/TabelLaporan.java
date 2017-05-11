/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import utilities.Formater;

/**
 *
 * @author andriawan
 */
public class TabelLaporan extends AbstractTableModel {
    
    List<Laporan> laporan;

    public TabelLaporan(List<Laporan> laporan) {
        this.laporan = laporan;
    }

    @Override
    public int getRowCount() {
        return laporan.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return laporan.get(rowIndex).getIdTransaksi();
            case 1:
                return Formater.setNiceIndonesianDate(
                        laporan.get(rowIndex).tgl_transaksi);
            case 2:
                return laporan.get(rowIndex).getId_petugas();
            case 3:
                return laporan.get(rowIndex).getKode_barang();
            case 4:
                return laporan.get(rowIndex).getNamaBarang();
            case 5:
                return Formater.setRupiahFormat(
                        laporan.get(rowIndex).getHarga());
            case 6:
                return laporan.get(rowIndex).getJumlah();
            case 7:
                return laporan.get(rowIndex).getUsername();
            case 8:
                return laporan.get(rowIndex).getStatus();
            default:
                return null;
                    
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID Transaksi";
            case 1:
                return "Tanggal Transaksi";
            case 2:
                return "ID Petugas";
            case 3:
                return "Kode Barang";
            case 4:
                return "Nama Barang";  
            case 5:
                return "Harga";
            case 6:
                return "Jumlah";
            case 7:
                return "Username";
            case 8:
                return "Nama asli";  
            case 9:
                return "Jabatan";  
            default:
                return null;
        }
    }     
}
