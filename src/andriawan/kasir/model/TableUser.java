/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andriawan
 */
public class TableUser extends AbstractTableModel{
    
    List<User> users;

    public TableUser(List<User> users) {
        this.users = users;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return users.get(rowIndex).getId();
            case 1:
                return users.get(rowIndex).getUsername();
            case 2:
                return users.get(rowIndex).getPassword();
            case 3:
                return users.get(rowIndex).getNama();
            case 4:
                return users.get(rowIndex).getStatus();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
         switch (column) {
            case 0:
                return "ID User";
            case 1:
                return "Username";
            case 2:
                return "Password";
            case 3:
                return "Nama";
            case 4:
                return "Status";  
            default:
                return null;
        }
    }
    
    
    
}
