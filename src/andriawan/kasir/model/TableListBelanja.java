/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.model;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andriawan
 */
public class TableListBelanja extends DefaultTableModel {

    public TableListBelanja() {
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID Barang";
            case 1:
                return "Nama Barang";
            case 2:
                return "Harga";
            case 3:
                return "Jumlah";
            case 4:
                return "Stok";
            default:
                return null;
        }
    }
}
