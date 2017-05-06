/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author andriawan
 */
public class Formater {
    
    public static String setRupiahFormat(int kurs){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.
                getCurrencyInstance();
        
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        
        return kursIndonesia.format(kurs);
    }
    
    public static int setRupiahToInteger(String rupiah){
        
        String replace1 = rupiah.replace("Rp. ", "");
        String replace2 = replace1.replace(".", "");
        String replace3 = replace2.replace(",00", "");

        return new Integer(replace3);
    }
    
    private static String setTerbilang(Long angka) {
        
        String[] angkaTerbilang = {"", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan", "Sepuluh", "Sebelas"};
        if (angka < 12) {
            return angkaTerbilang[angka.intValue()];
        }
        if (angka >= 12 && angka <= 19) {
            return angkaTerbilang[angka.intValue() % 10] + " Belas";
        }
        if (angka >= 20 && angka <= 99) {
            return setTerbilang(angka / 10) + " Puluh " + angkaTerbilang[angka.intValue() % 10];
        }
        if (angka >= 100 && angka <= 199) {
            return "Seratus " + setTerbilang(angka % 100);
        }
        if (angka >= 200 && angka <= 999) {
            return setTerbilang(angka / 100) + " Ratus " + setTerbilang(angka % 100);
        }
        if (angka >= 1000 && angka <= 1999) {
            return "Seribu " + setTerbilang(angka % 1000);
        }
        if (angka >= 2000 && angka <= 999999) {
            return setTerbilang(angka / 1000) + " Ribu " + setTerbilang(angka % 1000);
        }
        if (angka >= 1000000 && angka <= 999999999) {
            return setTerbilang(angka / 1000000) + " Juta " + setTerbilang(angka % 1000000);
        }
        if (angka >= 1000000000 && angka <= 999999999999L) {
            return setTerbilang(angka / 1000000000) + " Milyar " + setTerbilang(angka % 1000000000);
        }
        if (angka >= 1000000000000L && angka <= 999999999999999L) {
            return setTerbilang(angka / 1000000000000L) + " Triliun " + setTerbilang(angka % 1000000000000L);
        }
        if (angka >= 1000000000000000L && angka <= 999999999999999999L) {
            return setTerbilang(angka / 1000000000000000L) + " Quadrilyun " + setTerbilang(angka % 1000000000000000L);
        }
        return "0";
    }
    
    public static String setRupiahTerbilang(int l){
        String d = Integer.toString(l);
        Long s = new Long(d);
        return setTerbilang(s) + "Rupiah";
    }
    
    public static String setStringReadSql(long l){
        Date dt = new java.sql.Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateF = sdf.format(dt);
        return dateF;
    }
    
    public static void main(String[] args) {
    }

}
