/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.BooklistSearch;

import BE.Book;

/**
 * SearchTypeArtistName class. For more information look in the
 * AbstractSearchType class.
 * @author Niels, Dennis & Alex.
 */
public class SearchTypeAuthorName extends AbstractSearchType {
    
    /**
     * Constructor.
     */
    public SearchTypeAuthorName() {
        
        //Call parent constructor.
        super();
    }
    
    /**
     * For more information look in the AbstractSearchType class.
     */
    @Override
    public String get(Object obj) {
        
        if (obj instanceof Book)
            return ((Book)obj).getAuthor();
        else
            return "";
    }
}