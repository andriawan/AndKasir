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
public class KasirUser extends Petugas{
    
    @Override
    public String getInfo(){
        return "Kasir memiliki akses untuk"
                + "\n" 
                + "1. Edit Barang Keluar (penjualan)\n"
                + "2. Cetak Struk\n"
                + "3. Melihat info Detail barang\n";
                
    }
    
    
    
}
