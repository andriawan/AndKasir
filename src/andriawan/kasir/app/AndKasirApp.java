/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.app;

import andriawan.kasir.controller.UserLoginController;
import andriawan.kasir.dao.impl.UserDaoImpl;
import andriawan.kasir.view.LoginForm;
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
        
        try{
            LoginForm loginForm = UserLoginController.getLoginFormInstance();
            UserDaoImpl user = UserLoginController.getUserInstance();
            UserLoginController userLoginController
                    = new UserLoginController(user, loginForm);   
        }catch(ExceptionInInitializerError e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null
                    ,"Kesalahan: File Konfigurasi tidak dapat ditemukan. Silahkan Baca README"
                    ,"Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

}