/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.dao;

import andriawan.kasir.model.Transaksi;
import java.util.List;

/**
 *
 * @author andriawan
 */
public interface TransaksiDao {
    
    List<Transaksi> getAllTransaksi();
    Transaksi getTransaksi(int kode);
    void updateTransaksi(Transaksi transaksi, int id);
    void deleteTransaksi(int kodeTransaksi);
    void insertTransaksi(Transaksi transaksi);
}
