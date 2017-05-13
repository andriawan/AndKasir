/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.controller;

import andriawan.kasir.dao.impl.UserDaoImpl;
import andriawan.kasir.view.KasirForm;
import andriawan.kasir.view.LoginForm;
import andriawan.kasir.view.MainForm;
import andriawan.safe.password.SafePassword;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author andriawan
 */
public class UserLoginController {
    
    private static LoginForm loginForm = new LoginForm();
    private static UserDaoImpl user = new UserDaoImpl();
    private static MainForm main;
    private static KasirForm kasir = new KasirForm();
    

    public UserLoginController(UserDaoImpl user, LoginForm form) {
        UserLoginController.user = user;
        UserLoginController.loginForm = form;
    }
    //Singleton
    public static UserDaoImpl getUserInstance(){
        if(user == null){
            user = new UserDaoImpl();
        }
        return user;
    }
    
    //Singleton
    public static LoginForm getLoginFormInstance(){
        if(loginForm == null){
            loginForm = new LoginForm();
        }
        return loginForm;
    }
    
    //Singleton
    public static MainForm getMainFormInstance() throws SQLException{
        if(main == null){
            main = new MainForm();
        }
        return main;
    }
    
    //Singleton
    public static KasirForm getKasirFormInstance(){
        if(kasir == null){
            kasir = new KasirForm();
        }
        return kasir;
    }
    
    public boolean isValidAdminUser(String User, String pass){
        ArrayList<String> array = user.getUserLevel(User, "admin");
        String userd = array.get(0);
        String passd = array.get(3);
        
        return User.equals(userd) && SafePassword.verifySecureBcrypt(pass, passd);
    }
    
    public boolean isValidKasirUser(String User, String pass){
        ArrayList<String> array = user.getUserLevel(User, "kasir");
        String userd = array.get(0);
        String passd = array.get(3);
        String status = array.get(2);
        
        return User.equals(userd) && SafePassword.verifySecureBcrypt(pass, passd);
    }
    
}
