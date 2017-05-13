/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.app;

import andriawan.kasir.controller.UserLoginController;
import andriawan.kasir.dao.impl.UserDaoImpl;
import andriawan.kasir.view.LoginForm;

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
     */
    public static void main(String[] args) {

        UserDaoImpl user = UserLoginController.getUserInstance();
        LoginForm loginForm = UserLoginController.getLoginFormInstance();
        UserLoginController userLoginController
                = new UserLoginController(user, loginForm);
    }

}