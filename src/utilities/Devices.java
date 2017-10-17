/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import javax.print.PrintServiceLookup;

/**
 *
 * @author andriawan
 */
public class Devices {
    
    public static boolean isPrinterAvailable(){
        
        boolean result = true;
        if(PrintServiceLookup.lookupDefaultPrintService()== null) {
            result = false;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(Devices.isPrinterAvailable());
    }
    
}
