/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author andriawan
 */
public class RupiahFormat {
    
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
    
    public static int rupiahToInteger(String rupiah){
        
        String replace1 = rupiah.replace("Rp. ", "");
        String replace2 = replace1.replace(".", "");
        String replace3 = replace2.replace(",00", "");

        return new Integer(replace3);
    }
    
}
