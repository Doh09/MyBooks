/*
 * BooksManager.
 * This class holds a TreeMap that are loaded from and saved to the HDD. 
 * The TreeMap contains the books in the library system, and within them, their copies.
 * The class has options to load, save, add, edit and remove books from the system, as well as
 * editing the amount of copies available of each book type.
 * A method also exists to get the TreeMap as a list, if this is needed.
 * A singleton pattern is used as the class is a manager type.
 */
package BLL;

import BE.Book;
import DAL.BooksPersistenceManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex, Dennis & Niels.
 */
public class BooksManager
{

    /**
     * Variables
     */
    private static BooksManager instance;
    private BooksPersistenceManager BPM;

    private Map<String, Book> mapBooks; //Variable that holds all the book types and their copies.

    /**
     * Constructor
     */
    private BooksManager()
    {
        //Setup reference to DAL layer and file path to save/load from.
        BPM = new BooksPersistenceManager("bookList.dat");
        //Attempt to load the book list upon startup.
        loadBookListFromHDD();
    }

    /**
     * Singleton pattern that returns an instance/object of the class
     * BooksManager.
     *
     * @return an instance of the class.
     */
    public static synchronized BooksManager getInstance()
    {
        if (instance == null)
        {
            instance = new BooksManager();
            return instance;
        }
        return instance;
    }

    /**
     * Method used to add a book or if the book already exists, add a copy of it
     * to its copy list. The book is registered based on its ISBN String. The
     * updated book list is afterwards saved to the HDD.
     *
     * @param book
     */
    public void addBook(Book book)
    {
        //If book type is not found in TreeMap.
        if (mapBooks == null || !mapBooks.containsKey(book.getISBN()))
        {
            //Add book type to TreeMap
            mapBooks.put(book.getISBN(), book);
            //Save map to HDD before adding copy to avoid nullPointerException
            saveBooklistsToHDD();
            //Repeat method to add a copy, otherwise the book type has no copies to be lent out.
            addBook(book);
        }
        //add book to the book TreeMap
        else
        {
            //Re-create the book with a generated copy id.
            Book bookCopy = new Book(book.getISBN(), book.getTitle(), book.getAuthor(), book.getEdition(), book.getYear(), book.getPublisher(), generateNewBookCopiesId(book), book.getStatus(), book.getDueDate(), book.getBorrowers(), book.getDaysLentOut(), book.getCreationDay());
            //Make extra check to ensure that ISBN matches.
            if (book.getISBN().equalsIgnoreCase(bookCopy.getISBN()))
            {
                //Ensure that the book added doesn't also hold a list of books.
                //This is to avoid multiple lists of the same book type.
                bookCopy.setBookCopiesNull();
                //Add the book copy to the list.
                mapBooks.get(book.getISBN()).getBookCopies().add(bookCopy);
            }
        }
        //save book map to HDD
        saveBooklistsToHDD();
    }

    /**
     * Method used to edit a book and its copies. The edited book + copies are
     * saved to the HDD.
     *
     * @param book
     */
    public void editBookAndCopies(Book book)
    {
        //Make new book type reset its copy list.
        book.setBookCopiesNull();
        //Get existing copies from HDD
        List<Book> tempList = mapBooks.get(book.getISBN()).getBookCopies();
        //merge old book with edited book information, this is to retain id.
        Book oldBook = mapBooks.get(book.getISBN());
        mapBooks.put(book.getISBN(), new Book(book.getISBN(), book.getTitle(), book.getAuthor(), book.getEdition(), book.getYear(), book.getPublisher(), oldBook.getId(), book.getStatus(), oldBook.getDueDate(), oldBook.getBorrowers(), oldBook.getDaysLentOut(), oldBook.getCreationDay()));
        //save map to HDD before adding copy to avoid nullPointerException
        saveBooklistsToHDD();
        //change old copies into new copies and add them to new book.
        for (Book b : tempList)
        {
            //change only status && daysLentOut for the selected book copy.
            String status = b.getStatus();
            String daysLentOut = b.getDaysLentOut();
            if (book.getId() == b.getId())
            {
                status = book.getStatus();
                daysLentOut = book.getDaysLentOut();
            }
            //merge old copy with edited book information, this is to retain id, status etc.
            b = new Book(book.getISBN(), book.getTitle(), book.getAuthor(), book.getEdition(), book.getYear(), book.getPublisher(), b.getId(), status, b.getDueDate(), b.getBorrowers(), daysLentOut, b.getCreationDay());
            //Ensure that the book added doesn't also hold a list of books.
            //This is to avoid multiple lists of the same book type.            
            b.setBookCopiesNull();
            //Add copy to book types copy list
            mapBooks.get(book.getISBN()).getBookCopies().add(b);
        }
        //Save copies added to the HDD.
        saveBooklistsToHDD();
    }

    /**
     * Removes a book copy with the given ISBN String from the book list, but
     * only if it does not have a status of being "Lent out". The status check
     * ignores case.
     *
     * If the book copy is the last copy of its type, the book type is deleted
     * along with the copy.
     *
     * @param ISBN
     * @param id
     */
    public void removeBookCopy(String ISBN, int id)
    {
        boolean lentOut = false;
        //ensure book is not lent out.
        for (Book b : getSingleBook(ISBN).getBookCopies())
        {
            if (b.getStatus().equalsIgnoreCase("Lend out"))
            {
                lentOut = true;
            }
        }
        if (!lentOut)
        {
            //If copy list has more than 1 copy.
            if (getSingleBook(ISBN).getBookCopies().size() > 1)
            {
                //Go through copy list
                for (Book b : getSingleBook(ISBN).getBookCopies())
                {
                    if (!b.getStatus().equalsIgnoreCase("Lend out") && b.getId() == id)
                    {
                        //If book is not lent out and correct copy is found, remove book.
                        getSingleBook(ISBN).getBookCopies().remove(b);
                    }
                }
            }
            //If only 1 copy is in the copy list.
            else if (getSingleBook(ISBN).getBookCopies().size() <= 1)
            {
                //remove the book type from the TreeMap.
                mapBooks.remove(ISBN);
            }
            //save TreeMap with books.
            saveBooklistsToHDD();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Book is lent out, cannot delete lent out books!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Saves TreeMap of all the books + the amount of book copies available of
     * them to the HDD.
     */
    public void saveBooklistsToHDD()
    {
        try
        {
            BPM.saveBooklistsToHDD(mapBooks);
        }
        catch (IOException ex)
        {
            throw new IllegalArgumentException("Unable to correctly save the book lists to HDD.");
        }

    }

    /**
     * Loads a TreeMap of the book list in the library system, from a file on
     * the HDD, the loaded TreeMap is put into the variable "mapBooks".
     */
    public void loadBookListFromHDD()
    {
        try
        {
            mapBooks = BPM.loadBookListFromHDD();
        }
        catch (IOException | ClassNotFoundException ex)
        {
            mapBooks = new TreeMap<>();
            System.out.println("Unable to correctly load book list TreeMap,"
                    + "\n new TreeMap created.");
        }
    }

    //getters
    /**
     * Returns the TreeMap "mapBooks" which is a list of the books registered in
     * the library system. The TreeMap is loaded in from the HDD before it is
     * returned.
     *
     * @return Map<String, Book>
     */
    public Map<String, Book> getMapBooks()
    {
        loadBookListFromHDD();
        return mapBooks;
    }

    /**
     * Updates the booklist TreeMap held by BooksManager with the one stored on
     * the HDD, then returns a single book.
     *
     * @param ISBN
     * @return a book based on the ISBN number given, if no book is found in the
     * system with the ISBN, null is returned instead.
     */
    public Book getSingleBook(String ISBN)
    {
        loadBookListFromHDD();
        if (mapBooks.containsKey(ISBN))
        {
            return mapBooks.get(ISBN);
        }
        return null;
    }

    /**
     * Method used to create and add a book + the amount of copies of it
     * available. The book is registered based on its ISBN String, if the String
     * is already registered, the a copy is added to the book types list. The
     * updated book list is afterwards saved to the HDD when it reaches the DAL
     * layer. The difference between this method and the method "addBook" is
     * that this method assigns a generated id to the book and is meant for
     * creating new ones, "addBook" must receive a constructed book.
     *
     * @param ISBN
     * @param title
     * @param author
     * @param edition
     * @param year
     * @param publisher
     * @param status
     * @param dueDate
     * @param borrowers
     * @param daysLentOut
     * @param creationDay
     */
    public void createAndAddBook(String ISBN, String title, String author, int edition, int year, String publisher, String status, String dueDate, int borrowers, String daysLentOut, String creationDay)
    {
        int id = -1;
        if (mapBooks.containsKey(ISBN))
        {
            //if the book type is already in the system, get the id.
            id = mapBooks.get(ISBN).getId();
        }
        else
        {
            //if the book type is not found in the system, generate a new id.
            id = generateNewBookId(ISBN);
        }
        //create book
        Book newBook = new Book(ISBN, title, author, edition, year, publisher, id, status, dueDate, borrowers, daysLentOut, creationDay);
        //add book to collection
        addBook(newBook);
    }

    /**
     * Method used to generate a book type Id.
     *
     * @param ISBN
     * @return a unique book type Id.
     */
    private int generateNewBookId(String ISBN)
    {
        int id = 1;
        //Find the largest id in the book list and set the current id to be 1 larger than the largest one.
        for (Book b : getListBooks())
        {
            //If Id is higher and book does not have the same ISBN as the one being created.
            if (b.getId() >= id && !ISBN.equalsIgnoreCase(b.getISBN()))
            {
                id = b.getId() + 1;
            }
        }
        return id;
    }

    /**
     * Method used to generate a book copy Id, the copy Id is based off of the
     * type Id.
     *
     * @param book
     * @return a unique book copy Id.
     */
    private int generateNewBookCopiesId(Book book)
    {
        int id = book.getId();
        int copyId = ((book.getId()) * 1000) + 1;
        for (Book b : mapBooks.get(book.getISBN()).getBookCopies())
        {
            if (b.getId() >= copyId)
            {
                copyId = b.getId() + 1;
            }
        }

        return copyId;
    }

    /**
     * Returns a list of the books registered in the library system. The list is
     * loaded in from the HDD, and converted from a TreeMap to a list, before it
     * is returned.
     *
     * @return List<Book>
     */
    public List<Book> getListBooks()
    {
        loadBookListFromHDD();
        List<Book> list = new ArrayList<>(mapBooks.values());
        return list;
    }
}
