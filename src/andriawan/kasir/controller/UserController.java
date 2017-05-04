/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.controller;

import andriawan.kasir.dao.impl.UserDaoImpl;
import andriawan.kasir.model.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author andriawan
 */
public class UserController {
    
    public List<User> getAllUser() throws SQLException{
        return new UserDaoImpl().getAllUser();
    }
}
