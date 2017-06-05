/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModels;

import BE.Book;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Niels, Dennis & Alex
 */
public class UserBookCopies extends AbstractTableModel{
    /**
     * Static and finals.
     */
    private static final String[] HEADER =
    {
        "Book Id", "Status"
    };
    
    private static final Class[] COLUMN_TYPE =
    {
        int.class, String.class
    };
    
    /**
     * Instance variables.
     */
    private List<Book> allBooks;
    
    public UserBookCopies(List<Book> BookCopies) {
        List<Book> books = new ArrayList<>();
        if(BookCopies!=null&& BookCopies.size()>0){
            for (Book book: allBooks) {
                if(!book.getStatus().equals("Lend out")){
                    books.add(book);
                }       
            }
        }
        this.allBooks = books;
    }

    @Override
    public int getRowCount() {
        return getAllBooks().size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Book book = getAllBooks().get(rowIndex);
        
        if (book == null) return null;
  
        switch(columnIndex){
            case 0:
                return book.getId();                
            case 1: 
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
        List<Book> books = new ArrayList<>();
        for (Book book: allBooks) {
            if(!book.getStatus().equals("Lend out")){
                books.add(book);
            }       
        }
        this.allBooks = books;
        fireTableDataChanged();
    }

    /**
     * @return the allBooks
     */
    public List<Book> getAllBooks() {
        return allBooks;
    }
}
