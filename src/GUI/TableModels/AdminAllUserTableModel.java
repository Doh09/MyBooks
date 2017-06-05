/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModels;

import BE.User;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Niels, Dennis & Alex
 */
public class AdminAllUserTableModel extends AbstractTableModel{
    /**
     * Static and finals.
     */
    private static final String[] HEADER =
    {
        "Id","Name", "Address", "Email", "Administrator"
    };
    
    private static final Class[] COLUMN_TYPE =
    {
        int.class, String.class, String.class, String.class, boolean.class
    };
    
    /**
     * Instance variables.
     */
    private List<User> allUsers;
    
    public AdminAllUserTableModel(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    @Override
    public int getRowCount() {
        return allUsers.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        User user = allUsers.get(rowIndex);
        
        if (user == null) return null;
  
        switch(columnIndex){
            case 0:
                return user.getId();                
            case 1:
                return user.getName();
            case 2:
                return user.getAddress();
            case 3:
                return user.getEmail();
            case 4: 
                return user.isAdmin();
        }
        
        return null;
    }
    
    @Override
    public String getColumnName(int col){
        return HEADER[col];
    }

    @Override
    public Class<?> getColumnClass(int col){
        return COLUMN_TYPE[col];
    }
    
    public void updateTable(List<User> allUsers){
        this.allUsers = allUsers;
        fireTableDataChanged();
    }
}
