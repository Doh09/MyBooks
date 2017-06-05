/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModels;

import BE.Book;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Niels, Dennis & Alex
 */
public class UserLoansTableModel extends AbstractTableModel{
    /**
     * Static and finals.
     */
    private static final String[] HEADER =
    {
        "Book Id","Title", "Author", "Due Date"
    };
    
    private static final Class[] COLUMN_TYPE =
    {
        int.class, String.class, String.class, String.class
    };
    
    /**
     * Instance variables.
     */
    private List<Book> allUsersLoans;
    
    public UserLoansTableModel(List<Book> allUsersLoans) {
        this.allUsersLoans = allUsersLoans;
    }

    @Override
    public int getRowCount() {
        return allUsersLoans.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Book loandbook = allUsersLoans.get(rowIndex);
        
        if (loandbook == null) return null;
  
        switch(columnIndex){
            case 0:
                return loandbook.getId();                
            case 1:
                return loandbook.getTitle();
            case 2:
                return loandbook.getAuthor();
            case 3:
                return loandbook.getDueDate();
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
    
    public void updateTable(List<Book> allUsers){
        this.allUsersLoans = allUsers;
        fireTableDataChanged();
    }
}
