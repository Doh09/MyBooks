/*
 * Book.
 * A class that represents a book that the User class can borrow and return.
 */
package BE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Niels, Dennis & Alex
 */
public class Book implements Serializable
{
    /**
     * Variables
     */          
    private final int id;
    private String ISBN;
    private String title;
    private String author;
    private int edition;
    private int year;
    private String publisher;
    private String status;
    private String dueDate;
    private int borrowers;
    private String daysLentOut;
    private String creationDay;
    private List<Book> bookCopies;

    /**
     * Constructor
     *
     * @param ISBN
     * @param title
     * @param author
     * @param edition
     * @param year
     * @param publisher
     * @param id
     * @param status
     * @param dueDate
     */
    public Book(String ISBN, String title, String author, int edition, int year, String publisher, int id, String status, String dueDate, int borrowers, String daysLentOut, String creationDay)
    {
        //Initialize variables
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.year = year;
        this.publisher = publisher;
        this.id = id;
        this.status = status;
        this.dueDate = dueDate;
        this.borrowers = borrowers;
        this.daysLentOut = daysLentOut;
        this.creationDay = creationDay;
        this.bookCopies = new ArrayList<>();
    }

    /**
     * @return the int variable id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the String variable ISBN
     */
    public String getISBN()
    {
        return ISBN;
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }

    /**
     * @returns the int variable dueDate
     */
    public String getDueDate()
    {
        return dueDate;
    }

    /**
     * sets the variable dueDate
     * @param dueDate
     */
    public void setDueDate(String dueDate)
    {
        this.dueDate = dueDate;
    }

    /**
     * @returns the int variable borrowers
     */
    public int getBorrowers()
    {
        return borrowers;
    }

    /**
     * sets the variable borrowers
     * @param borrowers
     */
    public void setBorrowers(int borrowers)
    {
        this.borrowers = borrowers;
    }

    /**
     * @returns the int variable daysLentOut
     */
    public String getDaysLentOut()
    {
        return daysLentOut;
    }

    /**
     * sets the variable daysLentOut
     * @param daysLentOut
     */
    public void setDaysLentOut(String daysLentOut)
    {
        this.daysLentOut = daysLentOut;
    }

    /**
     * @returns the int variable creationDay 
     */
    public String getCreationDay()
    {
        return creationDay;
    }

    /**
     * sets the variable creationDay
     * @param creationDay
     */
    public void setCreationDay(String creationDay)
    {
        this.creationDay = creationDay;
    }

    /**
     * @return the String variable "title"
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return the String variable "author"
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }

    /**
     * @return the int variable "edition"
     */
    public int getEdition()
    {
        return edition;
    }

    /**
     * @param edition the edition to set
     */
    public void setEdition(int edition)
    {
        this.edition = edition;
    }

    /**
     * @return the int variable "year"
     */
    public int getYear()
    {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * @return the String variable "publisher"
     */
    public String getPublisher()
    {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    /**
     * @return the String variable "status"
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status)
    {
        this.status = status;
    }


    /**
     * Method to compare 2 instances of the Book class
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj)
    {

        if (obj == null)
        {
            return false;
        }

        return ((Book) obj).getId() == id;
    }

    /**
     * @return the bookCopies
     */
    public List<Book> getBookCopies()
    {
        return bookCopies;
    }

    /**
     * Method to set bookCopies variable null. This is to ensure that the books
     * added don't hold additional lists of themselves.
     */
    public void setBookCopiesNull()
    {
        bookCopies = new ArrayList<Book>();
    }

    public void setBookCopies(List<Book> bookCopies)
    {
        this.bookCopies = bookCopies;
    }
}
