/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author andriawan
 * Kelas ini menangani Format yang sering ditemui. dibuat agar kodenya reuse ( dapat dipakai kembali )
 */

public class Formater {


   /**
        *
        * fungsi setRupiahFormat memudahkan untuk membuat tampilan integer menjadi
        * Format mata uang rupiah parameter : int kurs return : string berformat
        * rupiah (misal Rp. 100.000, 00) modifier : public static
        */
    
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

   /**
        *
        * fungsi setRupiahToInteger memudahkan untuk mengambil nilai integer dari
        * string hasil format dengan funsi setRupiahFormat() parameter : String
        * rupiah return : string berformat rupiah (misal Rp. 100.000, 00) akan
        * menjadi 100000 modifier : public static
        */
    
    public static int setRupiahToInteger(String rupiah){
        
        String replace1 = rupiah.replace("Rp. ", "");
        String replace2 = replace1.replace(".", "");
        String replace3 = replace2.replace(",00", "");

        return new Integer(replace3);
    }

   /**
        *
        * fungsi setTerbilang mengubah inputan Long menjadi String terbilang
        * parameter : Long angka return : string berformat misal 1200 menjadi
        * "Seribu Dua Ratus" modifier : private static
        */
    
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

   /**
          *
         * fungsi setRupiahTerbilang mengolah fungsi setTerbilang menjadi lebih sempurna denga akhiran "Rupiah"
         * parameter : int l
         * return 	: string berformat misal 1200 menjadi "Seribu Dua Ratus Rupiah"
         * modifier : private static
         */
    
    public static String setRupiahTerbilang(int l){
        String d = Integer.toString(l);
        Long s = new Long(d);
        return setTerbilang(s) + "Rupiah";
    }
    
    /**
	 *
	 * fungsi setStringReadySql mengolah long l yang biasanya diambil dari timestamp menjadi tanggal yang siap
	 * diolah sql sebagai tipe DATETIME
	 * parameter : long l
	 * return 	: string berformat yyyy-MM-dd HH:mm:ss misal 2017-10-11 10:55:20
	 * modifier : public static
	 */
    
    public static String setStringReadySql(long l){
        Date dt = new java.sql.Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateF = sdf.format(dt);
        return dateF;
    }
    
    public static String setDateComboSql1(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:01:00");
        String dateF = sdf.format(d);
        return dateF;
    }
    
    public static String setDateComboSql2(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:00");
        String dateF = sdf.format(d);
        return dateF;
    }
    
    public static String filterOnlyNumber(String filter){
        // Mengganti semua karakter selain numerik (0-9)
        String replaceAll = filter.replaceAll("[^\\d]", "");
        return replaceAll;
    }

    /**
	 *
	 * fungsi setNiceIndonesianDate mengolah long l yang biasanya diambil dari timestamp menjadi tanggal dalam
	 * format indonesia
	 * parameter : long l
	 * return 	: string berformat dd MMMM yyyy HH:mm:ss misal 11 Maret 2017 10:55:20
	 * modifier : public static
	 */
    
    public static String setNiceIndonesianDate(long l){
        Date dt = new java.sql.Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat(
                "dd MMMM yyyy HH:mm:ss", new java.util.Locale("id"));
        String dateF = sdf.format(dt);
        return dateF;
    }
    
    public static String setNiceIndonesianDateShort(long l){
        Date dt = new java.sql.Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat(
                "dd MMMM yyyy", new java.util.Locale("id"));
        String dateF = sdf.format(dt);
        return dateF;
    }
    
    
    // format laporan untuk keperluan cetak dan cari laporan
    public static String setDateForReport(long l){
        Date dt = new java.sql.Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat(
                "dd-MM-YYYY", new java.util.Locale("id"));
        String dateF = sdf.format(dt);
        return dateF;
    }
    
    public static String getTimeStamp(){
        return Long.toString(System.currentTimeMillis());
    }

}