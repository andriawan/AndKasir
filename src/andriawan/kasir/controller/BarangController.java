/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.controller;

import andriawan.kasir.dao.impl.BarangDaoImpl;
import andriawan.kasir.model.Barang;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author andriawan
 */
public class BarangController {
    
    public List<Barang> getAllBarang() throws SQLException{
        return new BarangDaoImpl().getAllBarang();
    }
}