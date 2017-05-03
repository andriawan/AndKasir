/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andriawan
 */
public class TableTransaksi extends AbstractTableModel {

    private final List<Transaksi> list;

    public TableTransaksi(List<Transaksi> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();

    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getIdTransaksi();
            case 1:
                return list.get(rowIndex).getTglTransaksi();
            case 2:
                return list.get(rowIndex).getTotalItem();
            case 3:
                return list.get(rowIndex).getTotalHarga();
            case 4:
                return list.get(rowIndex).getKasir();
            default:
                return null;
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Kode Transaksi";
            case 1:
                return "Tanggal Transaksi";
            case 2:
                return "Total Item";
            case 3:
                return "Total Harga";
            case 4:
                return "Petugas Kasir";
            default:
                return null;
        }
    }

}