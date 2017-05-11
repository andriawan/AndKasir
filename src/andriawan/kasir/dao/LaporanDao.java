/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.dao;

import andriawan.kasir.model.Laporan;
import java.util.List;

/**
 *
 * @author andriawan
 */
public interface LaporanDao {
    
    List<Laporan> getDailyReport(String tgl);
    List<Laporan> getMonthlyReport(String tgl);
    List<Laporan> getYearlyReport(String tgl);
}
