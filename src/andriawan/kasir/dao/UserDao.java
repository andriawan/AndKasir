/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.dao;

import andriawan.kasir.model.User;
import java.util.List;

/**
 *
 * @author andriawan
 */
public interface UserDao {
    
    List<User> getAllUser();
    User getUser(int kode);
    void updateUser(User user, int id);
    void deleteUser(int kodeUser);
    void insertUser(User user);
    
}
