/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.BooklistSearch;


import BE.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * BooklistSearch class, this class is designed to search through a BookList
 * there are containing Books.
 * @author Niels, Dennis & Alex.
 */
public class BooklistSearch {
    
    /**
     * Static and finals.
     */
    private static BooklistSearch instance;
    
    /**
     * Constructor.
     */
    private BooklistSearch() {}
    
    /**
     * Gets the instance of PlaylistSearch singletone.
     */
    public static BooklistSearch getInstance() {
        if (instance == null)
            instance = new BooklistSearch();
        return instance;
    }
    
    /**
     * Searches through the playlist and collects songs there is matching the
     * search query.
     * @param playlist - Playlist to search through.
     * @param searchType - SearchType to use in the search.
     * @param matcher - SearchMatch to tuse in the search.
     * @return a list of songs, which match the query.
     */
    public List<Book> search(List<Book> booklist, ISearchType searchType, ISearchMatch matcher) {
        
        //Temporary list for the result.
        List<Book> result = new ArrayList<>();
        
        //Loop through each song in the playlist.
        for (Book book : booklist) {
            
            //Get the search type value from the song.
            String searchTypeValue = searchType.get(book);
            
            //Check if searchTypeValue equals the search query from the matcher.
            if (matcher.match(searchTypeValue)) {
                
                //Add song to result if not already added.
                if (!result.contains(book)) {
                    
                    result.add(book);
                }
            }
        }
        
        return result;
    }
    
    /**
     * Searches through the playlist and collects songs there is matching the
     * search query.
     * @param playlist - Playlist to search through.
     * @param searchType - SearchType to use in the search.
     * @param matchers - Multiple Matchers to use in search.
     * @return a list of songs, which match the query.
     */
    public List<Book> search(List<Book> booklist, ISearchType searchType, ISearchMatch... matchers) {
        
        //List of songs which matched the given query.
        List<Book> result = new ArrayList<>();
        
        //Loop throug each matcher.
        for (ISearchMatch matcher : matchers) {
            
            //Search with the current matcher.
            List<Book> searchResult = search(booklist, searchType, matcher);
            
            //Loop through each song in the search result list to add to result
            //list.
            for (Book book : searchResult) {
                
                //Add song if do not already exist.
                if (!result.contains(book)) {
                    
                    result.add(book);
                }
            }
        }
        
        return result;
    }

    /**
     * mixes two lists togheter so one song cant appear twice.
     * @param list1
     * @param list2
     * @return
     */
    public List<Book> mixList(List<Book> list1, List<Book> list2)
    {
        List<Book> results = new ArrayList(list1);
        for(Book b: list2)
        {
            if(!results.contains(b))
            {
                results.add(b);
            }
        }
        return results;
    }
}
