/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.controller;

import andriawan.kasir.dao.impl.UserDaoImpl;
import andriawan.kasir.model.User;
import andriawan.kasir.view.EditorUserForm;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andriawan
 */
public class UserController {
    
    private static EditorUserForm insert = new EditorUserForm();
    private static EditorUserForm update = new EditorUserForm();
    
    public List<User> getAllUser() throws SQLException{
        return new UserDaoImpl().getAllUser();
    }
    
    public void insertUser(User user){
        new UserDaoImpl().insertUser(user);
    }
    
    public User getUser(int kode){
        return new UserDaoImpl().getUser(kode);
    }
    
    public ArrayList<String> getUserLevel(String username, String status){
        return new UserDaoImpl().getUserLevel(username, status);
    }
    
    public void updateUser(User user){
        new UserDaoImpl().updateUser(user, user.getId());
    }
    
    public void updateUserNoPassword(User user){
        new UserDaoImpl().updateUserNoPassword(user, user.getId());
    }
    
    public void deleteUser(int kode){
        new UserDaoImpl().deleteUser(kode);
    }
    
    public List<User> multiSeacrh(String a, String b, String c, String d){
        return new UserDaoImpl().multiSearch(a, b, c, d);
    }
    
    public static EditorUserForm getInsertFormInstance(){
        
        try {
            if (insert == null) {
                insert = new EditorUserForm();
            }
            return insert;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static EditorUserForm getUpdateFormInstance(){
        
        try {
            if (update == null) {
                update = new EditorUserForm();
            }
            return update;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}