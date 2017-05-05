/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.dao.impl;

import andriawan.kasir.dao.UserDao;
import andriawan.kasir.model.User;
import andriawan.safe.password.SafePassword;
import utilities.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andriawan
 */
public class UserDaoImpl implements UserDao {

    private final List<User> semuaUser;
    private Connection con;
    private PreparedStatement preparedStatement;

    // info table
    private static final String TABLE = "userapp";
    private static final String COLUMN_KODE_USER = "kode_user";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NAMA_ASLI = "nama_asli";
    private static final String COLUMN_STATUS = "status";

    // FIND ALL
    private static final String FIND_ALL
            = "SELECT * FROM "
            + TABLE;
    // Delete
    private static final String DELETE
            = "DELETE FROM "
            + TABLE
            + " WHERE "
            + COLUMN_KODE_USER + "=?";

    //FIND by kode_barang
    private static final String FIND_BY_ID
            = "SELECT * FROM "
            + TABLE
            + " WHERE "
            + COLUMN_KODE_USER + "=?";
    
    private static final String FIND_BY_LEVEL
            = "SELECT * FROM "
            + TABLE
            + " WHERE "
            + COLUMN_USERNAME + "=? AND "
            + COLUMN_STATUS +"=?";

    //FIND by nama_barang
    private static final String FIND_BY_NAME
            = "SELECT * FROM "
            + TABLE
            + " WHERE "
            + COLUMN_USERNAME + "=?";
    
    //MULTI SEARCH
    private static final String MULTI_SEARCH = 
            "SELECT * FROM " 
            + TABLE + 
            " WHERE " 
            + COLUMN_KODE_USER + " like ? OR "
            + COLUMN_USERNAME + " like ? OR "
            + COLUMN_NAMA_ASLI + " like ? OR "
            + COLUMN_STATUS+ " like ?";
    //INSERT
    private static final String INSERT
            = "INSERT INTO "
            + TABLE + "("
            + COLUMN_USERNAME + ", "
            + COLUMN_PASSWORD + ", "
            + COLUMN_NAMA_ASLI + ", "
            + COLUMN_STATUS + ") VALUES(?, ?, ? , ?)";
    //UPDATE
    private static final String UPDATE = "UPDATE " + TABLE + " SET "
            + COLUMN_USERNAME + "=?, "
            + COLUMN_PASSWORD + "=?, "
            + COLUMN_NAMA_ASLI + "=?, "
            + COLUMN_STATUS + "=? "
            + "WHERE "
            + COLUMN_KODE_USER + "=?";
    
    private static final String UPDATE_NO_PASSWORD = "UPDATE " + TABLE + " SET "
            + COLUMN_USERNAME + "=?, "
            + COLUMN_NAMA_ASLI + "=?, "
            + COLUMN_STATUS + "=? "
            + "WHERE "
            + COLUMN_KODE_USER + "=?";

    public UserDaoImpl() {
        ResultSet result = null;
        List<User> users = new ArrayList<User>();

        try {
            con = ConnectionManager.getConnection();
            preparedStatement = con.prepareStatement(FIND_ALL);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int kode = result.getInt(1);
                String username = result.getString(2);
                String password = result.getString(3);
                String fullname = result.getString(4);
                String status = result.getString(5);

                users.add(new User(kode, username, password, fullname, status));
            }

            this.semuaUser = users;

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            this.close(con);
            close(preparedStatement);
        }
    }

    @Override
    public List<User> getAllUser() {
        return semuaUser;
    }

    @Override
    public User getUser(int kode) {
        ResultSet result = null;
        try {
            con = ConnectionManager.getConnection();
            preparedStatement = con.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, kode);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                String username = result.getString(2);
                String password = result.getString(3);
                String fullname = result.getString(4);
                String status = result.getString(5);
                
                User usr = new User(kode, username, password, fullname, status);

                return usr;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(preparedStatement);
        }
    }

    @Override
    public void updateUser(User user, int id) {
        try {
            con = ConnectionManager.getConnection();
            preparedStatement = con.prepareStatement(UPDATE);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, SafePassword.hashSecureBcrypt(user.getPassword()));
            preparedStatement.setString(3, user.getNama());
            preparedStatement.setString(4, user.getStatus());
            preparedStatement.setInt(5, id);
            
            preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            close(con);
            close(preparedStatement);
        }
    }
    
    public void updateUserNoPassword(User user, int id) {
        try {
            con = ConnectionManager.getConnection();
            preparedStatement = con.prepareStatement(UPDATE_NO_PASSWORD);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getNama());
            preparedStatement.setString(3, user.getStatus());
            preparedStatement.setInt(4, id);
            
            preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            close(con);
            close(preparedStatement);
        }
    }
    

    @Override
    public void deleteUser(int kodeUser) {
        try {
            con = ConnectionManager.getConnection();
            preparedStatement = con.prepareStatement(DELETE);
            preparedStatement.setInt(1, kodeUser);
            
            int status = preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            close(con);
            close(preparedStatement);
        }
    }

    @Override
    public void insertUser(User user) {
        try {
            
            con = ConnectionManager.getConnection();
            
            preparedStatement = con.prepareStatement(INSERT);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, SafePassword.hashSecureBcrypt(user.getPassword()));
            preparedStatement.setString(3, user.getNama());
            preparedStatement.setString(4, user.getStatus());
            
            int status = preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            close(con);
            close(preparedStatement);
        }
    }
    
    // CUSTOM CRUD
    
    public ArrayList<String> getUserLevel(String username, String status) {
        ResultSet result = null;
        try {
            con = ConnectionManager.getConnection();
            preparedStatement = con.prepareStatement(FIND_BY_LEVEL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, status);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                String usernamer = result.getString(2);
                String password = result.getString(3);
                String statusr = result.getString(5);
                
                ArrayList<String> container = new ArrayList<String>();
                container.add(usernamer);
                container.add(password);
                container.add(statusr);
                
                return container;
                
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(preparedStatement);
        }
    }
    
    public List<User> multiSearch(String a, String b, String c, String d) {
        ResultSet result = null;
        List<User> list = new ArrayList<>();
        try {
            con = ConnectionManager.getConnection();
            preparedStatement = con.prepareStatement(MULTI_SEARCH);
            preparedStatement.setString(1, "%" + a + "%");
            preparedStatement.setString(2, "%" + b + "%");
            preparedStatement.setString(3, "%" + c + "%");
            preparedStatement.setString(4, "%" + d + "%");
            result = preparedStatement.executeQuery();
            
            while (result.next()) {
                int kode = result.getInt(1);
                String username = result.getString(2);
                String password = result.getString(3);
                String fullname = result.getString(4);
                String status = result.getString(5);

                list.add(new User(kode, username, password, fullname, status));
            }
            
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(preparedStatement);
        }
    }

    private static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException esd) {
                // e.printStackTrace();
                throw new RuntimeException(esd);
            }
        }
    }

    private static void close(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException be) {
                // e.printStackTrace();
                throw new RuntimeException(be);
            }
        }
    }

}
