/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.app;

import andriawan.kasir.controller.UserLoginController;
import javax.swing.JOptionPane;


/**
 *
 * @author andriawan
 *
 * Starting Point Aplikasi AndKasir v1
 */
public class AndKasirApp {

    /**
     * 
     * Memanggil User Login Controller, Menampilkan View LoginForm 
     * Mengadopsi Paradigma MVC
     *
     * @param args
     */
    public static void main(String[] args) {
        // wrap dengan try catch untuk menghandle execption
        try{
            // standart penanganan backup database, setiap applikasi dipanggil, procedure
            // backup standart via ftp akan dipanggil
            utilities.ConnectionManager.backupDB();
            // instansiasi class LoginForm (view) via UserLoginController.getLoginFormInstance()
            // menerapkan design pattern singleton untuk pengehematan resource
            UserLoginController.getLoginFormInstance();
        }catch(ExceptionInInitializerError e){
            // memeberi info pada user jika terjadi kesalahan/error
            JOptionPane.showMessageDialog(null
                    ,"Kesalahan: File Konfigurasi tidak dapat ditemukan. Silahkan Baca README"
                    ,"Error", JOptionPane.ERROR_MESSAGE);
            // keluar dari aplikasi
            System.exit(0);
        }
    }

}