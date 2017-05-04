/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.dao;

import andriawan.kasir.controller.UserLoginController;
import andriawan.kasir.dao.impl.UserDaoImpl;
import andriawan.kasir.model.User;
import andriawan.kasir.view.LoginForm;

/**
 *
 * @author andriawan
 */
public class main {
    
    public static void main(String[] args) {
        
        UserDaoImpl user = new UserDaoImpl();
        LoginForm loginForm = new LoginForm();
        UserLoginController userLoginController = 
                new UserLoginController(user, loginForm);
        
//        UserDaoImpl im = new UserDaoImpl();
//        List<User> li = im.getAllUser();
//        
//        TransaksiDaoImpl g = new TransaksiDaoImpl();
//        
//        List<Transaksi> ss = g.getAllTransaksi();
//        Transaksi trsc;
//        trsc = g.getTransaksi(1);
//        
//        Date dtwe = new Date(trsc.getTglTransaksi());
//        SimpleDateFormat formattexr = new SimpleDateFormat("dd MM YY HH:mm:ss");
//        String xxx = formattexr.format(dtwe);
//        System.out.println(xxx);
//        
//        for (Transaksi s : ss) {
//            
//            java.sql.Date ssh = new java.sql.Date(s.getTglTransaksi());
//            SimpleDateFormat formatter = new SimpleDateFormat("dd MM YY HH:mm:ss");
//            String xxxs = formatter.format(ssh);
//            System.out.println(xxxs);
//            
//        }
//        
//        User s = im.getUser(9);
//        
//        String hash = s.getPassword();
//        
//        Boolean sxs = SafePassword.verifySecureBcrypt("as", hash);
//        
//        System.out.println(s.getNama());
//        System.out.println(ss);
//        while (rs.next()) {
//            int no = rs.getInt(1);
//            String nama = rs.getString(2);
//            int harga = rs.getInt(3);
//            int stok = rs.getInt(3);
//            
//            System.out.println("no : " + no);
//            System.out.println("nama : " + nama);
//            System.out.println("harga : " + harga);
//            System.out.println("stok : " + stok);
//            
//        }
//        Properties config = new Properties();
//        InputStream is;
//        
//        try {
//            is = new FileInputStream("config.properties");
//            config.load(is);
//            String url = config.getProperty("url");
//            String user = config.getProperty("username");
//            String pass = config.getProperty("password");
//            
//            System.out.println(url + " " + user + " " + pass);
//            is.close();
//        } catch (FileNotFoundException e){
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        catch (Exception e) {
//        }
        
    }
    
}
