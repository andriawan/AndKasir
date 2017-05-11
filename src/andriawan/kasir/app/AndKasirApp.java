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
 * Starting Point Aplikasi AndKasir
 */
public class AndKasirApp {

    /**
     * 
     * Memanggil User Login Controller, Menampilkan View LoginForm 
     * Mengadopsi Paradigma MVC Namun belum sempurna
     *
     */
    public static void main(String[] args) {

        UserDaoImpl user = new UserDaoImpl();
        LoginForm loginForm = new LoginForm();
        UserLoginController userLoginController
                = new UserLoginController(user, loginForm);
    }

}
