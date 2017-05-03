/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andriawan
 */
public class TabelBarang extends AbstractTableModel{
    
    List<Barang> barang;

    public TabelBarang(List<Barang> barang) {
        this.barang = barang;
    }

    @Override
    public int getRowCount() {
        return barang.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return barang.get(rowIndex).getKodeBarang();
            case 1:
                return barang.get(rowIndex).getNamaBarang();
            case 2:
                return barang.get(rowIndex).getHarga();
            case 3:
                return barang.get(rowIndex).getStok();
            default:
                return null;
                    
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Kode Barang";
            case 1:
                return "Nama Barang";
            case 2:
                return "Harga";
            case 3:
                return "Status";  
            default:
                return null;
        }
    }
    
}
