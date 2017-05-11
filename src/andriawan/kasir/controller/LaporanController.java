/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.controller;

import andriawan.kasir.dao.impl.LaporanDaoImpl;
import andriawan.kasir.model.Laporan;
import andriawan.kasir.view.LaporanForm;
import java.util.List;

/**
 *
 * @author andriawan
 */
public class LaporanController {
    
     // field variable untuk keperluan penerapan singleton
    public static LaporanController lc = new LaporanController();
    
    // field variable untuk keperluan penerapan singleton
    public static LaporanForm lf = new LaporanForm();
    
    public List<Laporan> getReport(String tgl1, String tgl2){
        return new LaporanDaoImpl().getReport(tgl1, tgl2);
    }
    
    public List<Laporan> getDailyReport(String sql){
        return new LaporanDaoImpl().getDailyReport(sql);
    }
    
    public List<Laporan> getMonthlyReport(String sql){
        return new LaporanDaoImpl().getMonthlyReport(sql);
    }
    
    public List<Laporan> getYearlyReport(String sql){
        return new LaporanDaoImpl().getYearlyReport(sql);
    }
    
    // Singleton untuk penghematan memori
    public static LaporanController getInstanceLaporanController(){
        
        try {
            if (lc == null) {
                lc = new LaporanController();
            }
            return lc;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    //Singleton
    public static LaporanForm getInstanceLaporanForm(){
        try {
            if(lf == null){
            lf = new LaporanForm();
        }
            return lf;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
