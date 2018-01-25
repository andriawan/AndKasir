/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.app;

import andriawan.kasir.controller.UserLoginController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utilities.ConnectionManager;
import utilities.MigrationManager;


/**
 *
 * @author andriawan
 *
 * Starting Point Aplikasi AndKasir 
 * Latest Version : 0.5.7
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
        
        // fitur migrasi db
        // untuk anda yang tidak mau repot
        // dalam urusan database
        if(args.length != 0){
            // akan melakukan penyesuaian database yang dibutuhkan
            // anda hanya tinggal menyeiapkan databse kosong
            if (args[0].equals("db-migrate") || args[0].equals("-m")) {
                MigrationManager.migrate();
                System.exit(0);
            } else if (args[0].equals("clean") || args[0].equals("-c")) {
                MigrationManager.clean();
                System.exit(0);
            }
        }
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
                    ,"Kesalahan: File Konfigurasi atau database tidak dapat ditemukan. Silahkan Baca README"
                    ,"Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, e);
            // keluar dari aplikasi
            System.exit(0);
        }
    }

}