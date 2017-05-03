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
public class Petugas extends User{

    public Petugas() {
        super(0, null, null, null, null);
    }
    
    public String petugasInfo() {
        return "Petugas terdiri dari\n"
                + "1.Admin\n"
                + "2.Kasir\n";
        
    }
}
