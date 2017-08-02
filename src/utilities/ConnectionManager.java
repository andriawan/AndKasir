/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import andriawan.kasir.controller.UserLoginController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author andriawan
 *
 * TO DO: enkrip konten dari file eksternal config.properties untuk peningkatan
 * keamanan
 */
public class ConnectionManager {

    private static String url;
    private static String database;
    private static String username;
    private static String password;
    private static String driver;
    private static String customPath;

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
                JOptionPane.showMessageDialog(UserLoginController.getLoginFormInstance(),
                        "Error: Terjadi Masalah dengan Koneksi Database. "
                        + "Periksa apakah database service telah berjalan ",
                        "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Failed to Establish Database Connection");
                System.exit(0);
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
            String mysqldumpCustomPath = config.getProperty("mysqldump_custom_path");

            ConnectionManager.url = tmpUrl;
            ConnectionManager.password = pass;
            ConnectionManager.username = user;
            ConnectionManager.driver = drv;
            ConnectionManager.database = db;
            ConnectionManager.customPath = mysqldumpCustomPath;

            is.close();
        } catch (IOException e) {
        }

    }

    // https://stackoverflow.com/questions/14924770/simple-backup-and-restore-for-mysql-database-from-java
    public static void backupDB() {

        try {
            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource cs;
            cs = ConnectionManager.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(cs.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            /*NOTE: Creating Database Constraints*/
            LoadConfigFile();
            
            String fileName = "windy_collections_db_" 
                    + utilities.Formater.getTimeStamp() + ".sql";
            String dbName = ConnectionManager.database;
            String dbUser = ConnectionManager.username;
            String dbPass = ConnectionManager.password;
            String executeCmd;
            String savePath;
            String folderPath;
            
            // excute when OS env is Linux-based
            if ("Linux".equals(System.getProperty("os.name"))) {
                /*NOTE: Creating Path Constraints for folder saving*/
                /*NOTE: Here the backup folder is created for saving inside it*/
                folderPath = jarDir + "/backup";

                /*NOTE: Creating Folder if it does not exist*/
                File file = new File(folderPath);
                file.mkdir();

                /*NOTE: Creating Path Constraints for backup saving*/
                /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
                savePath = jarDir + "/backup/" + fileName;

                /*NOTE: Used to create a cmd command*/
                executeCmd = "mysqldump -u" + dbUser + " -p" + dbPass
                        + " --database " + dbName + " -r " + savePath;
            } else{
                /*NOTE: Creating Path Constraints for folder saving*/
                /*NOTE: Here the backup folder is created for saving inside it*/
                folderPath = jarDir + "\\backup";

                /*NOTE: Creating Folder if it does not exist*/
                File f1 = new File(folderPath);
                f1.mkdir();

                /*NOTE: Creating Path Constraints for backup saving*/
                /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
                savePath = "\"" + jarDir + "\\backup\\" + fileName + "\"";

                /*NOTE: Used to create a cmd command*/
                executeCmd = ConnectionManager.customPath + " -u" + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + savePath;
            }

            /*NOTE: Executing the command here*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                System.out.println("database berhasil disimpan di   " + savePath);
            } else {
                System.out.println("Backup Failure");
            }

        } catch (URISyntaxException | IOException | InterruptedException te) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + te.getMessage());
        }

    }

}
