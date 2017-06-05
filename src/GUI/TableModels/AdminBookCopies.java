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
public class AdminBookCopies extends AbstractTableModel{
    /**
     * Static and finals.
     */
    private static final String[] HEADER =
    {
        "Book Id","Creation Date", "Days Lend Out", "Borowers", "Status"
    };
    
    private static final Class[] COLUMN_TYPE =
    {
        int.class, String.class, String.class, String.class, String.class
    };
    
    /**
     * Instance variables.
     */
    private List<Book> allBooks;
    
    public AdminBookCopies(List<Book> allUsers) {
        this.allBooks = allUsers;
    }

    @Override
    public int getRowCount() {
        return allBooks.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Book book = allBooks.get(rowIndex);
        
        if (book == null) return null;
  
        switch(columnIndex){
            case 0:
                return book.getId();                
            case 1:
                return book.getCreationDay();
            case 2:
                return book.getDaysLentOut();
            case 3:
                return book.getBorrowers();
            case 4: 
                return book.getStatus();
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
    
    public void updateTable(List<Book> allBooks){
        this.allBooks = allBooks;
        fireTableDataChanged();
    }
}
