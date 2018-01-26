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
 * 
 * Class yang mengimplementasikan koneksi ke database untuk mengakses resource
 * yang telah didefinisikan dalam Model User
 */
public class UserDaoImpl implements UserDao {
    
    // TODO : pagination query untuk view pada MainForm

    // List yang akan menangkap hasil query ke variable semuaUser
    private final List<User> semuaUser;
    
    // Objek Koneksi ke Databases
    private Connection con;
    
    // PreparedStatement digunakan untuk menghindari Sql Injection dan Optimasi Query ke database
    private PreparedStatement preparedStatement;

    // info table SQL
    private static final String TABLE = "userapp";
    
    // info Kolom SQL
    private static final String COLUMN_KODE_USER = "kode_user";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NAMA_ASLI = "nama_asli";
    private static final String COLUMN_STATUS = "status";
    
    // Perintah Query SQL

    // FIND ALL
    private static final String FIND_ALL
            = "SELECT * FROM "
            + TABLE;
    
    // DELETE (1 preparedstatement input filter)
    private static final String DELETE
            = "DELETE FROM "
            + TABLE
            + " WHERE "
            + COLUMN_KODE_USER + "=?";

    //FIND by kode_barang (2 preparedstatement input filter)
    private static final String FIND_BY_ID
            = "SELECT * FROM "
            + TABLE
            + " WHERE "
            + COLUMN_KODE_USER + "=?";
    
    //FIND by status (2 preparedstatement input filter)
    private static final String FIND_BY_LEVEL
            = "SELECT * FROM "
            + TABLE
            + " WHERE "
            + COLUMN_USERNAME + "=? AND "
            + COLUMN_STATUS +"=?";

    //FIND by nama_barang (1 preparedstatement input filter)
    private static final String FIND_BY_NAME
            = "SELECT * FROM "
            + TABLE
            + " WHERE "
            + COLUMN_USERNAME + "=? AND "
            + COLUMN_STATUS + "=kasir";
    
    //MULTI SEARCH (4 preparedstatement input filter)
    private static final String MULTI_SEARCH = 
            "SELECT * FROM " 
            + TABLE + 
            " WHERE " 
            + COLUMN_KODE_USER + " like ? OR "
            + COLUMN_USERNAME + " like ? OR "
            + COLUMN_NAMA_ASLI + " like ? OR "
            + COLUMN_STATUS+ " like ?";
    
    //INSERT (4 preparedstatement input filter)
    private static final String INSERT
            = "INSERT INTO "
            + TABLE + "("
            + COLUMN_USERNAME + ", "
            + COLUMN_PASSWORD + ", "
            + COLUMN_NAMA_ASLI + ", "
            + COLUMN_STATUS + ") VALUES(?, ?, ? , ?)";
    
    //UPDATE (5 preparedstatement input filter)
    private static final String UPDATE = "UPDATE " + TABLE + " SET "
            + COLUMN_USERNAME + "=?, "
            + COLUMN_PASSWORD + "=?, "
            + COLUMN_NAMA_ASLI + "=?, "
            + COLUMN_STATUS + "=? "
            + "WHERE "
            + COLUMN_KODE_USER + "=?";
    
    //UPDATE USER WITH NO PASSWORD
    private static final String UPDATE_NO_PASSWORD = "UPDATE " + TABLE + " SET "
            + COLUMN_USERNAME + "=?, "
            + COLUMN_NAMA_ASLI + "=?, "
            + COLUMN_STATUS + "=? "
            + "WHERE "
            + COLUMN_KODE_USER + "=?";
    
    // Konstruktor akan memanggil query semua user
    
    // untuk membatasi konsumsi memori
    public UserDaoImpl() {
        
        // Inisialisasi Object ResultSet untuk penampung hasil query
        ResultSet result = null;
        
        // List Kosong untuk penampung Object User
        List<User> users = new ArrayList<User>();
        
        // Try Catch meminimalisir error SQL
        try {
            // Assignment Object con (field variable) dari static Object 
            // Connection manager
            con = ConnectionManager.getDataSourceConnection();
            
            // Assignment Object PreparedStatement dari Object con 
            // yang memanggil fungsi prepareStatement dengan parameter String FIND_ALL 
            preparedStatement = con.prepareStatement(FIND_ALL);
            
            // object Resultset Assignment dari Objeck preparedStatement yang memanggil
            /// fungsi executQuery dengan pengembalian Object Resultset
            result = preparedStatement.executeQuery();
            
            // While loop untuk mengambil semua hasil dari result set dengan
            // kondisi boolean yang dihasilkan fungsi next
            while (result.next()) {
                
                // Assignment variable dari hasil result tabel sql
                int kode = result.getInt(1);
                String username = result.getString(2);
                String password = result.getString(3);
                String fullname = result.getString(4);
                String status = result.getString(5);
                
                // menyimpan variable ke User Objek (Anonim) dengan konstruktor 
                // yang mengambil parameter dari variable di atas (hasil resultset)
                // ke ArrayList
                users.add(new User(kode, username, password, fullname, status));
            }
            
            // Assignment field variable semuaUser dari value users
            this.semuaUser = users;
            
        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            // Setelah prosedur fungsi selesai, selalu diakhiri dengan
            // fungsi close untuk membebaskan hasil query dari memori sebagai
            // parktek keamanan yang baik
            close(con);
            close(preparedStatement);
        }
    }

    // fungsi dari interface. menangani pengembalian List Object User
    @Override
    public List<User> getAllUser() {
        return semuaUser;
    }
    
    // fungsi dari interface. menangani pengembalian Object User
    // Parameter int merujuk pada id user dalam kolom tabel sql
    @Override
    public User getUser(int kode) {
        
        // Inisialisasi Object ResultSet untuk penampung hasil query
        ResultSet result = null;
        
        // Try Catch meminimalisir error SQL
        try {
            
            // Assignment Object con (field variable) dari static Object 
            // Connection manager
            con = ConnectionManager.getDataSourceConnection();
            
            // Assignment Object PreparedStatement dari Object con 
            // yang memanggil fungsi prepareStatement dengan parameter String FIND_BY_ID
            preparedStatement = con.prepareStatement(FIND_BY_ID);
            
            // metode setInt akan mengambil parameter ke 1 dari variabel FIND_BY_ID
            // dari tanda "?"  dan variable kedua kode sebagai valuenya. kode merupakan
            // parameter dari metode getUser
            preparedStatement.setInt(1, kode);
            
            // object Resultset Assignment dari Objeck preparedStatement yang memanggil
            /// fungsi executQuery dengan pengembalian Object Resultset
            result = preparedStatement.executeQuery();
            
            // kondisi jika resultset menghasilkan objek. jika ada maka akan di
            // tampung ke variable dibawah
            if (result.next()) {
                String username = result.getString(2);
                String password = result.getString(3);
                String fullname = result.getString(4);
                String status = result.getString(5);
                
                // mengembalikan object User (anonim) dengan konstruktor berparameter
                // variable diatas
                return new User(kode, username, password, fullname, status);
                
            // return null jika tidak ada objek yang dihasilkan
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Setelah prosedur fungsi selesai, selalu diakhiri dengan
            // fungsi close untuk membebaskan hasil query dari memori sebagai
            // parktek keamanan yang baik
            close(con);
            close(preparedStatement);
        }
    }

    @Override
    public void updateUser(User user, int id) {
        try {
            con = ConnectionManager.getDataSourceConnection();
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
            // Setelah prosedur selesai, selalu diakhiri dengan
            // fungsi close untuk membebaskan hasil query dari memori sebagai
            // parktek keamanan yang baik
            close(con);
            close(preparedStatement);
        }
    }
    
    public void updateUserNoPassword(User user, int id) {
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(UPDATE_NO_PASSWORD);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getNama());
            preparedStatement.setString(3, user.getStatus());
            preparedStatement.setInt(4, id);
            
            preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            // Setelah prosedur selesai, selalu diakhiri dengan
            // fungsi close untuk membebaskan hasil query dari memori sebagai
            // parktek keamanan yang baik
            close(con);
            close(preparedStatement);
        }
    }
    

    @Override
    public void deleteUser(int kodeUser) {
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(DELETE);
            preparedStatement.setInt(1, kodeUser);
            
            int status = preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            // Setelah prosedur fungsi selesai, selalu diakhiri dengan
            // fungsi close untuk membebaskan hasil query dari memori sebagai
            // parktek keamanan yang baik
            close(con);
            close(preparedStatement);
        }
    }

    @Override
    public void insertUser(User user) {
        try {
            
            con = ConnectionManager.getDataSourceConnection();
            
            preparedStatement = con.prepareStatement(INSERT);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, SafePassword.hashSecureBcrypt(user.getPassword()));
            preparedStatement.setString(3, user.getNama());
            preparedStatement.setString(4, user.getStatus());
            
            int status = preparedStatement.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            // Setelah prosedur fungsi selesai, selalu diakhiri dengan
            // fungsi close untuk membebaskan hasil query dari memori sebagai
            // parktek keamanan yang baik
            close(con);
            close(preparedStatement);
        }
    }
    
    // CUSTOM CRUD
    
    public ArrayList<String> getUserLevel(String username, String status) {
        ResultSet result = null;
        try {
            con = ConnectionManager.getDataSourceConnection();
            preparedStatement = con.prepareStatement(FIND_BY_LEVEL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, status);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                String usernamer = result.getString(2);
                String id = result.getString(1);
                String pass = result.getString(3);
                String statusr = result.getString(5);
                
                ArrayList<String> container = new ArrayList<>();
                container.add(usernamer);
                container.add(id);
                container.add(statusr);
                container.add(pass);
                
                return container;
                
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Setelah prosedur fungsi selesai, selalu diakhiri dengan
            // fungsi close untuk membebaskan hasil query dari memori sebagai
            // parktek keamanan yang baik
            close(con);
            close(preparedStatement);
        }
    }
    
    public List<User> multiSearch(String a, String b, String c, String d) {
        ResultSet result = null;
        List<User> list = new ArrayList<>();
        try {
            con = ConnectionManager.getDataSourceConnection();
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
            // Setelah prosedur fungsi selesai, selalu diakhiri dengan
            // fungsi close untuk membebaskan hasil query dari memori sebagai
            // parktek keamanan yang baik
            close(con);
            close(preparedStatement);
        }
    }
    
    //Melepaskan resource Connection dari memori
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
    
    //Melepaskan resource PreparedStatement dari memori
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
