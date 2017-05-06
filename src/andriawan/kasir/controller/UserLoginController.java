/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.controller;

import andriawan.kasir.dao.impl.UserDaoImpl;
import andriawan.kasir.view.LoginForm;
import andriawan.safe.password.SafePassword;
import java.util.ArrayList;

/**
 *
 * @author andriawan
 */
public class UserLoginController {
    
    LoginForm loginForm;
    UserDaoImpl user;

    public UserLoginController(UserDaoImpl user, LoginForm form) {
        this.user = user;
        this.loginForm = form;
    }
    
    public void onButtonLoginClicked(){
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
        
        if (User.equals(userd) && SafePassword.verifySecureBcrypt(pass, passd)) {
            return true;
        }else{
            return false;
        }
    }
    
}
