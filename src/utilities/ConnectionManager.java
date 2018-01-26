/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import andriawan.kasir.controller.UserLoginController;
import andriawan.kasir.view.ProgressBar;
import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp2.BasicDataSource;
import org.javalite.activejdbc.Base;
import static utilities.SwingProgressBar.MIN;

/**
 *
 * @author andriawan
 *
 * TO DO: enkrip konten dari file eksternal config.properties untuk peningkatan
 * keamanan
 */
public class ConnectionManager {

    // handle db
    public static String url;
    public static String database;
    public static String username;
    public static String password;
    public static String driver;
    // handle path
    public static String customPath;
    // handle ftp
    public static String ftpHost;
    public static String ftpPort;
    public static String ftpUser;
    public static String ftpPass;
    public static String ftpPath;
    
    public static void javaLiteConnect(){
        LoadConfigFile();
        BasicDataSource ds = new BasicDataSource();
            
        ds.setDriverClassName(ConnectionManager.driver);
        ds.setUrl(ConnectionManager.url + ConnectionManager.database);
        ds.setUsername(ConnectionManager.username);
        ds.setPassword(ConnectionManager.password);
        
        Base.open(ds);
    }
    
    public static void close(){
        Base.close(Boolean.FALSE);
    }
    
    public static Boolean isClosed(){
        return Base.hasConnection();
    }

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
    public static void LoadConfigFile() {

        Properties config = new Properties();
        InputStream is;

        try {
            is = new FileInputStream("config.properties");
            config.load(is);
            
            ConnectionManager.url = config.getProperty("mysql_url");
            ConnectionManager.password = config.getProperty("password");
            ConnectionManager.username = config.getProperty("username");
            ConnectionManager.driver = config.getProperty("mysql_driver");
            ConnectionManager.database = config.getProperty("database");
            ConnectionManager.customPath = config.getProperty("mysqldump_custom_path");
            ConnectionManager.ftpHost = config.getProperty("ftp_host");
            ConnectionManager.ftpPass = config.getProperty("ftp_pass");
            ConnectionManager.ftpPort = config.getProperty("ftp_port");
            ConnectionManager.ftpUser = config.getProperty("ftp_user");
            ConnectionManager.ftpPath = config.getProperty("ftp_folder_path");

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
            
            final String fileName = "windy_collections_db_" 
                    + utilities.Formater.getTimeStamp() + ".sql";
            String dbName = ConnectionManager.database;
            String dbUser = ConnectionManager.username;
            String dbPass = ConnectionManager.password;
            final String executeCmd;
            final String savePath;
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
                savePath = jarDir + "\\backup\\" + fileName ;
                
                if (dbPass == null){
                    dbPass = "";
                }

                /*NOTE: Used to create a cmd command*/
                executeCmd = Paths.get(ConnectionManager.customPath)  + " -u "
                        + dbUser + " " + dbName + " -r " + "\"" + savePath + "\"";
            }

            /*NOTE: Executing the command here*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    
                    final FTPClient client = new FTPClient();
                    try {
                        // setting host
                        client.connect(ftpHost, new Integer(ftpPort));
                        // setting user
                        client.login(ftpUser, ftpPass);
                        client.upload(new File(savePath), new FTPDataTransferListener() {
                        @Override
                        public void started() {
                        }

                        @Override
                        public void transferred(final int i) {
                            
                            final ProgressBar pbr = ProgressBar.getInstance();
                            pbr.setVisible(true);
                            pbr.initProgressBar(i);

                            for (int w = MIN; w <= i; w++) {
                                final int percent = w;
                                java.awt.EventQueue.invokeLater(new Runnable() {
                                    
                                    @Override
                                    public void run() {
                                        pbr.updateValue(percent);
                                        //it.updateBar(percent);
                                    }
                                });
                                
                                if (percent == i) {
                                    pbr.dispose();
                                    return;
                                }
                            }
                        }

                        @Override
                        public void completed() {
                            try {
                                client.rename(fileName, ftpPath + "/" + fileName);
                            } catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException ex) {
                                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(
                                null,
                                "Berhasil terbackup",
                                "Berhasil", JOptionPane.INFORMATION_MESSAGE
                            );
                        }

                        @Override
                        public void aborted() {
                            JOptionPane.showMessageDialog(null,
                                "Dibatalkan",
                                "Error", JOptionPane.ERROR_MESSAGE
                            );
                        }

                        @Override
                        public void failed() {
                            JOptionPane.showMessageDialog(
                                null,
                                "Terjadi kesalahan",
                                "Error", JOptionPane.ERROR_MESSAGE
                            );
                        }
                    });

                    // close connection
                    client.disconnect(true);
                    }catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException | FTPDataTransferException | FTPAbortedException ex) {
                        Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(
                                null, 
                                "Prosedur backup standart gagal. "
                    + "Periksa koneksi internet anda. Gagal menghubungi alamat " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }

                    /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
                }
            });
            t.start();
            
            
            if (processComplete == 0) {
                System.out.println("Database berhasil disimpan di " + savePath);
            } else {
                //System.out.println(savePath);
                //System.out.println(executeCmd);
                System.out.println("Gagal membackup database");
            }

        } catch (URISyntaxException | IOException | InterruptedException te) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, te);
            JOptionPane.showMessageDialog(null, "Prosedur backup standart gagal. "
                    + "Periksa koneksi internet anda. Kode error : " + te.getMessage(),
                    "Error",JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getDataSourceConnection() {

        LoadConfigFile();

        try {
            BasicDataSource ds = new BasicDataSource();
            
            ds.setDriverClassName(ConnectionManager.driver);
            ds.setUrl(ConnectionManager.url + ConnectionManager.database);
            ds.setUsername(ConnectionManager.username);
            ds.setPassword(ConnectionManager.password);
            

            try {
                con = ds.getConnection();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(UserLoginController.getLoginFormInstance(),
                        "Error: Terjadi Masalah dengan Koneksi Database. "
                        + "Periksa apakah database service telah berjalan ",
                        "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Failed to Establish Database Connection");
                System.exit(0);
            }
        } catch (HeadlessException e) {
            System.out.println("Driver not found");
        }
        return con;
    }
}