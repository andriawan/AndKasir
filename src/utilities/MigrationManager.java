/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;
import andriawan.kasir.controller.BarangController;
import org.flywaydb.core.Flyway;

/**
 *
 * @author andriawan
 */
public class MigrationManager {
    
    private static Flyway flyway;

    private MigrationManager() {
        
    }
    
    private static void init(){
        ConnectionManager.LoadConfigFile();
        getInstance().setLocations("filesystem:db/migration");
        getInstance().setDataSource(ConnectionManager.url + ConnectionManager.database, 
                ConnectionManager.username, 
                ConnectionManager.password);
    }
    
    public static void migrate(){
        init();
        getInstance().migrate();
    }
    
    public static Flyway getInstance(){
        try {
            if(flyway == null){
            flyway = new Flyway();
        }
            return flyway;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static void setBasline(){
        init();
        getInstance().baseline();
    }
    
    public static void clean(){
        init();
        getInstance().clean();
    }
    
    
}
