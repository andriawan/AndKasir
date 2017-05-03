/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.model;

/**
 *
 * @author andriawan
 */
public class AdminUser extends Petugas{
    
    public String x;
    
    @Override
    public String getInfo(){
        return "Admin memiliki akses untuk"
                + "\n" 
                + "1. Edit Barang Masuk Barang Keluar\n"
                + "2. Menambah maupun mengurangi Stok Barang\n"
                + "3. Melihat Laporan Barang Harian Bulanan\n";
                
    }
    
    
}
