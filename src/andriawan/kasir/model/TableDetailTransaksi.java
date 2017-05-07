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
public class TableDetailTransaksi extends AbstractTableModel{
    
    private final List<DetailTransaksi> list;

    public TableDetailTransaksi(List<DetailTransaksi> list) {
        this.list = list;
    }
    
    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID Transaksi";
            case 1:
                return "ID Barang";
            case 2:
                return "Nama Barang";
            case 3:
                return "Harga";
            case 4:
                return "Jumlah";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return list.get(rowIndex).getId_transaksi();
            case 1:
                return list.get(rowIndex).getId_barang();
            case 2:
                return list.get(rowIndex).getNamaBarang();
            case 3:
                return Formater.setRupiahFormat(list.get(rowIndex).getHarga());
            case 4:
                return list.get(rowIndex).getJumlah();
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() {
        return list.size();
    }
}
