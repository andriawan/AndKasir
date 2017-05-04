/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.dao;

import andriawan.kasir.model.Barang;
import java.util.List;

/**
 *
 * @author andriawan
 */
public interface BarangDao {
    
    List<Barang> getAllBarang();
    Barang getBarang(int kode);
    void updateBarang(Barang barang);
    void deleteBarang(int kodebarang);
    void insertBarang(Barang barang);
    
}
