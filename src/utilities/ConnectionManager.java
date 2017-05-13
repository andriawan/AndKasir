/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author andriawan
 * 
 * TO DO: enkrip konten dari file eksternal config.properties
 * untuk peningkatan keamanan
 */
public class ConnectionManager {

    private static String url;
    private static String database;
    private static String username;
    private static String password;
    private static String driver;

    private static Connection con;

    public static Connection getConnection() {

        LoadConfigFile();

        try {
            Class.forName(ConnectionManager.driver);

            try {
                con = DriverManager.getConnection(ConnectionManager.url
                        + ConnectionManager.database,
                        ConnectionManager.username,
                        ConnectionManager.password);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                        "Error: Terjadi Masalah dengan Koneksi Database. "
                                + "Periksa apakah database service telah berjalan ",
                        "Error",JOptionPane.ERROR_MESSAGE);
                System.out.println("Failed to Establish Database Connection");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        }
        return con;
    }
    
    // method ini akan melakukan pengecekan beberapa string yang didefinisikan
    // dalam file eksternal "config.properties". String tersebut digunakan untuk
    // membangun konfigurasi ke database driver
    private static void LoadConfigFile() {

        Properties config = new Properties();
        InputStream is;

        try {
            is = new FileInputStream("config.properties");
            config.load(is);

            String tmpUrl;
            tmpUrl = config.getProperty("mysql_url");
            String user = config.getProperty("username");
            String pass = config.getProperty("password");
            String drv = config.getProperty("mysql_driver");
            String db = config.getProperty("database");

            ConnectionManager.url = tmpUrl;
            ConnectionManager.password = pass;
            ConnectionManager.username = user;
            ConnectionManager.driver = drv;
            ConnectionManager.database = db;

            is.close();
        } catch (IOException e) {
        }

    }

}