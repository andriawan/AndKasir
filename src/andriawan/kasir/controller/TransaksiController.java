/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.controller;

import andriawan.kasir.dao.impl.TransaksiDaoImpl;
import andriawan.kasir.model.DetailTransaksi;
import andriawan.kasir.model.Transaksi;

/**
 *
 * @author andriawan
 */
public class TransaksiController {
    
    public void insertTransaksi(Transaksi transaksi){
        new TransaksiDaoImpl().insertTransaksi(transaksi);
    }
    
    public void insertTransaksiDetail(DetailTransaksi detailTransaksi){
        new TransaksiDaoImpl().insertDetailTransaksi(detailTransaksi);
    }
    
    public Transaksi getLastRecord(){
        return new TransaksiDaoImpl().getLastRecord();
    }
    
    
    
}
