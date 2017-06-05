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
public class UserAllBooks extends AbstractTableModel{
    /**
     * Static and finals.
     */
    private static final String[] HEADER =
    {
        "ISBN","Author", "Title", "Edition", "Year","Publisher"
    };
    
    private static final Class[] COLUMN_TYPE =
    {
        int.class, String.class, String.class, int.class, int.class, String.class
    };
    
    /**
     * Instance variables.
     */
    private List<Book> allBooks;
    
    public UserAllBooks(List<Book> allUsers) {
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
                return book.getISBN();                
            case 1:
                return book.getAuthor();
            case 2:
                return book.getTitle();
            case 3:
                return book.getEdition();
            case 4: 
                return book.getYear();
            case 5:
                return book.getPublisher();
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
