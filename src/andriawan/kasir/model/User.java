/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.model;

/**
 *
 * @author andriawan
 */
public class User {
    
    int id;
    int levelPermission;
    
    private String nama;
    private String username;
    private String kategori;
    private String email;
    private String password;
    private String status;
    
    
    
    public User(int id, String nama, String username, String kategori, String email, String password, int levelPermission) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.kategori = kategori;
        this.email = email;
        this.password = password;
        this.levelPermission = levelPermission;
    }
    
    public User(int id, String username, String password, String namaAsli, String status) {
        this.id = id;
        this.nama = namaAsli;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public User(String username, String password, String namaAsli, String status) {
        this.nama = namaAsli;
        this.username = username;
        this.password = password;
        this.status = status;
    }
    
    public User(int id, String username, String namaAsli, String status) {
        this.id = id;
        this.nama = namaAsli;
        this.username = username;
        this.status = status;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getLevelPermission() {
        return levelPermission;
    }

    public void setLevelPermission(int levelPermission) {
        this.levelPermission = levelPermission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String Nama) {
        this.nama = Nama;
    }
    
    public String getInfo(){
        return "Default";
    }
    
}
