/*
 * BooksPersistenceManager.
 * This class saves and loads TreeMaps containing the library books and amount 
 * of copies available of each book. The saving/loading is done to/from the HDD.
 * The filepath used is given as a parameter in the class constructor.
 */
package DAL;

import BE.Book;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 *
 * @author Alex, Dennis & Niels.
 */
public class BooksPersistenceManager
{

    /**
     * Variables.
     */
    private String pathBooks;

    /**
     * Constructor. Sets the default paths for saving and loading the book HashMaps.
     *
     * @param pathBooks
     */
    public BooksPersistenceManager(String pathBooks)
    {
        //Set default path for this UserPersistenceManager.
        this.pathBooks = pathBooks;
    }

    /**
     * Saves a list of all the books + the amount of each book available to the
     * HDD.
     *
     * @param mapBooks
     * @throws IOException
     */
    public void saveBooklistsToHDD(Map<String, Book> mapBooks) throws IOException
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathBooks)))
        {
            oos.writeObject(mapBooks);
            oos.close();
        }
    }

    /**
     *
     * @return a HashMap of books in the library system, from a file on the HDD.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Map<String, Book> loadBookListFromHDD() throws IOException, ClassNotFoundException
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathBooks)))
        {
            return (Map<String, Book>) ois.readObject();
        }
    }
}
